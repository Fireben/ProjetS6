/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package educatus.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.EducatusLocale;
import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;
import educatus.client.ui.CustomButton;
import educatus.client.ui.dataGrids.ListContent;
import educatus.shared.dto.exercice.ExerciceCoreContent;
import educatus.shared.dto.pagecontent.SeminaryHomePageCategoryContent;
import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.ExerciceHomePageListingContentRequest;
import educatus.shared.services.requestservice.request.SeminaryHomePageCategoryContentRequest;
import educatus.shared.services.requestservice.response.ExerciceHomePageListingContentResponse;
import educatus.shared.services.requestservice.response.SeminaryHomePageCategoryContentResponse;

/**
 * @author Nicolas Michaud
 */
public class ExerciceHomePresenter extends Presenter<ExerciceHomePresenter.MyView, ExerciceHomePresenter.MyProxy> {
    /**
     * {@link ExerciceHomePresenter}'s proxy.
     */
	
    @ProxyCodeSplit
    @NameToken(NameTokens.exerciceHomePage)
    public interface MyProxy extends ProxyPlace<ExerciceHomePresenter> {
    }
    
	@Inject
	private EducatusLocale locale;
    
	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT.create(RequestService.class);
	
	// Response handler
	private AbstractResponseHandler responseHandler = null;
    
    public static final Object SLOT_content = new Object();

    
	@Inject
	CategoryPresenter categoryPresenter;
	
	@Inject
	ContentListPresenter listPresenter;

    /**
     * {@link ExerciceHomePresenter}'s view.
     */
    public interface MyView extends View {	  
    }

    @Inject
    public ExerciceHomePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
      super(eventBus, view, proxy);
    }

  	@Override
	protected void revealInParent() {
	  RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
  	
  	@Override
  	protected void onBind() {
  		super.onBind();
  		responseHandler = new AbstractResponseHandler();
  	}
  
	@Override
	protected void onReset() {
	  super.onReset();      
	  PageChangingEvent.fire(this, NameTokens.getExerciceHomePage());
	  categoryPresenter.setRootParent("Exercices");
	  categoryPresenter.registerBackButton(backClickHandler);
	  categoryPresenter.registerSeeAllButton(seeAllClickHandler);	
	  categoryPresenter.registerSearchButton(searchHandler);
	}
	
	@Override
	protected void onReveal() {  	
		setInSlot(SLOT_content, null);
		categoryPresenter.clear(); 
		createAndSendCategoryRequest(null);
	}
	
	private ClickHandler backClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {	 
			createAndSendCategoryRequest(null);
		}
	};
	
	private ClickHandler seeAllClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {	 
			CategoryCoreContent parentCategory = categoryPresenter.getParent();		
	  		createAndSendListingRequest(parentCategory);
		}
	};
	
	private ClickHandler categoryClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			CustomButton buttonClicked = (CustomButton) event.getSource();
			changeCategoryPanel(Integer.parseInt(buttonClicked.getElement().getId()));
		}
	};
	
	private ClickHandler searchHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {	 				
	  		createAndSendSearchListingRequest(categoryPresenter.getView().getSearchBox().getValue());
		}
	};
	
	private void changeCategoryPanel(int id) {  		
  		CategoryCoreContent parentCategory = new CategoryCoreContent();
  		parentCategory.setId(id);  		
  		createAndSendCategoryRequest(parentCategory);
	}
	
	private class AbstractResponseHandler implements AsyncCallback<AbstractResponse> {
		
		@Override
		public void onSuccess(AbstractResponse result) {			
			if (result.GetResponseType() == ResponseTypeEnum.SEMINARY_HOME_PAGE_CATEGORY_CONTENT_RESPONSE){	
				setInSlot(SLOT_content, categoryPresenter);
				SeminaryHomePageCategoryContentResponse response = (SeminaryHomePageCategoryContentResponse) result;
				SeminaryHomePageCategoryContent content = response.getContent();
				
				// Children, ask for categories
				if(content.getCategoryChildren().size() != 0) {
					categoryPresenter.setAndAnimateCategoryPanel(categoryClickHandler, content);
					CategoryCoreContent parent = content.getCommonParent();
					if(parent != null) {
						categoryPresenter.animateBackButtonIn();
					}					
				} 				
				// No child, ask for list of seminaries
				else {					
					CategoryCoreContent parentCategory = response.getContent().getCommonParent();		
			  		createAndSendListingRequest(parentCategory);
				}				
			} 
			
			else if (result.GetResponseType() == ResponseTypeEnum.EXERCICE_HOME_PAGE_LISTING_CONTENT_RESPONSE) {
				setInSlot(SLOT_content, listPresenter);
				ExerciceHomePageListingContentResponse response = (ExerciceHomePageListingContentResponse) result;
				List<ExerciceCoreContent> exerciceCoreContentList = response.getContent().getExercicesChildren();
				String name = response.getContent().getCommonParent() != null ? response.getContent().getCommonParent().getName() : "Exercices";
				setExerciceList(exerciceCoreContentList, name);
			}
			
			else {
				// ERROR, not the response type we expected
			}			
		}
		
		@Override
		public void onFailure(Throwable caught) {
		}
	};
	
	public void createAndSendCategoryRequest(CategoryCoreContent parentCategory) {
		SeminaryHomePageCategoryContentRequest request = new SeminaryHomePageCategoryContentRequest();
  		request.setParentCategory(parentCategory);
  		request.setCulture(locale.getCulture());
  		request.setLanguage(locale.getLanguage());
  		requestService.sendRequest(request, responseHandler);	
	}
	
	public void setExerciceList(List<ExerciceCoreContent> exerciceCoreContentList, String title) {
		List<ListContent> list = new ArrayList<ListContent>();
		
		for (ExerciceCoreContent exerciceCoreContent : exerciceCoreContentList) {
			ListContent listContent = new ListContent(
					exerciceCoreContent.getId(), 
					exerciceCoreContent.getTitle(), 
					exerciceCoreContent.getDescription(), 
					exerciceCoreContent.getAuthor().getFirstName() + " " + exerciceCoreContent.getAuthor().getLastName(), 
					exerciceCoreContent.getCreatedDate(), 
					exerciceCoreContent.getDifficultyValue(),
					NameTokens.getExercice()
			);
			list.add(listContent);
		}
		listPresenter.setTitle(title);
		listPresenter.setData(list);
		listPresenter.setBackButtonHandler(backClickHandler);		
	}
	

	public void createAndSendListingRequest(CategoryCoreContent parentCategory) {
  		ExerciceHomePageListingContentRequest request = new ExerciceHomePageListingContentRequest();
  		request.setSelectedCategory(parentCategory);
  		request.setCulture(locale.getCulture());
  		request.setLanguage(locale.getLanguage());
  		requestService.sendRequest(request, responseHandler);	
	}
	
	private void createAndSendSearchListingRequest(String value) {
  		ExerciceHomePageListingContentRequest request = new ExerciceHomePageListingContentRequest();
  		request.setSearchTerm(value);
  		request.setCulture(locale.getCulture());
  		request.setLanguage(locale.getLanguage());
  		requestService.sendRequest(request, responseHandler);			
	}
}