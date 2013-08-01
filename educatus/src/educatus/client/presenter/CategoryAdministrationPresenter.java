package educatus.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.CookiesConst;
import educatus.client.EducatusLocale;
import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;
import educatus.client.ui.CategoryInformation;
import educatus.client.ui.CategoryList;
import educatus.client.ui.dataGrids.Category;
import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.CategoryAdministrationPageContentRequest;
import educatus.shared.services.requestservice.response.CategoryAdministrationPageContentResponse;

public class CategoryAdministrationPresenter
		extends
		Presenter<CategoryAdministrationPresenter.MyView, CategoryAdministrationPresenter.MyProxy> {

	HandlerRegistration DeletedCategoryHandler = null;

	public interface MyView extends View {

		public VerticalPanel getCategoryVerticalPanel();
	}

	// Response handler
	private AbstractResponseHandler responseHandler = null;
	
	public static final Object SLOT_content = new Object();

	String action = null;
	String id = null;

	@Inject
	CategoryList categoryListPresenter;
	
	@ProxyCodeSplit
	@NameToken(NameTokens.categoryAdministration)
	public interface MyProxy extends
			ProxyPlace<CategoryAdministrationPresenter> {
	}

	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT
			.create(RequestService.class);

	@Inject
	private EducatusLocale locale;

	@Inject
	public CategoryAdministrationPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent,
				this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		responseHandler = new AbstractResponseHandler();
	}

	@Override
	protected void onReset() {
		super.onReset();
		PageChangingEvent.fire(this, NameTokens.getCategoryAdministration());
		
		getView().getCategoryVerticalPanel().clear();
		
		if ("Edit".equals(action) && id != null) {

			CategoryInformation categoryInformation = new CategoryInformation();

			TextBox software = new TextBox();
			software.setText("Software");
			categoryInformation.setCategoryName(software);

			VerticalPanel verticalPanel = new VerticalPanel();
			verticalPanel.add(new RichTextArea());
			categoryInformation
					.setCategoryDescriptionVerticalPanel(verticalPanel);

			Image categoryImage = new Image("images/categories/software.png");
			categoryInformation.setCategoryImage(categoryImage);

			getView().getCategoryVerticalPanel().add(categoryInformation);
			action = null;
			id = null;
		}

		else if (action == "delete" && id != null) {
			// Supprimer la catégorie sélectionner
			action = null;
			id = null;
		}

		else if (action == null && id == null) {
			createAndSendCategoryListRequest();
		}

		else {
			// ERROR not a expected token name
		}
		// }

		/*
		 * @Override public void onFailure(Throwable caught) { // TODO //
		 * Auto-generated method } });
		 */

	}

	@Override
	protected void onReveal() {
		super.onReveal();
		if ("Edit".equals(action) && id != null) {

			CategoryInformation categoryInformation = new CategoryInformation();

			TextBox software = new TextBox();
			software.setText("Software");
			categoryInformation.setCategoryName(software);

			VerticalPanel verticalPanel = new VerticalPanel();
			verticalPanel.add(new RichTextArea());
			categoryInformation
					.setCategoryDescriptionVerticalPanel(verticalPanel);

			Image categoryImage = new Image("images/categories/software.png");
			categoryInformation.setCategoryImage(categoryImage);

			getView().getCategoryVerticalPanel().add(categoryInformation);
			action = null;
			id = null;
		}

		else if (action == "delete" && id != null) {
			// Supprimer la catégorie sélectionner
			action = null;
			id = null;
		}

		else if (action == null && id == null) {
			createAndSendCategoryListRequest();
		}

		else {
			// ERROR not a expected token name
		}
		// }
	}

	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);
		action = placeRequest.getParameter("action", null);
		id = placeRequest.getParameter("id", null);
	}

	public void createAndSendCategoryListRequest() {
		CategoryAdministrationPageContentRequest request = new CategoryAdministrationPageContentRequest();
		request.setCulture(locale.getCulture());
		request.setLanguage(locale.getLanguage());
		request.setSessionID(Cookies.getCookie(CookiesConst.SESSION_ID));
		requestService.sendRequest(request, responseHandler);
	}

	private class AbstractResponseHandler implements
			AsyncCallback<AbstractResponse> {

		@Override
		public void onSuccess(AbstractResponse result) {
			if (result.GetResponseType() == ResponseTypeEnum.CATEGORY_ADMINISTRATION_PAGE_CONTENT_RESPONSE) {		
					CategoryAdministrationPageContentResponse response = (CategoryAdministrationPageContentResponse) result;

					List<CategoryCoreContent> categoryCoreContentList = response
							.getCategoryCoreContentList();

					getView().getCategoryVerticalPanel().clear();

					List<Category> categories = new ArrayList<Category>();

					for (CategoryCoreContent categoryCoreContent : categoryCoreContentList) {
						Category category = new Category(
								categoryCoreContent.getId(),
								categoryCoreContent.getName(),
								categoryCoreContent.getDescription(),
								categoryCoreContent.getImageUrl());
						categories.add(category);
					}
					CategoryList categoryList = new CategoryList();
					CellTable<Category> dataGrid = categoryList.getDataGrid();
					ListDataProvider<Category> dataProvider = new ListDataProvider<Category>();
					dataProvider.addDataDisplay(dataGrid);
					dataProvider.setList(categories);
					dataProvider.refresh();
					
					categoryListPresenter.setAddButtonHandler(addClickHandler);

					getView().getCategoryVerticalPanel().add(categoryList);
			} 
			
			else if (result.GetResponseType() == ResponseTypeEnum.CATEGORY_ADMINISTRATION_PAGE_CONTENT_RESPONSE){
				
				
			}
			else {
				// ERROR, not the response type we expected
			}
		}

		@Override
		public void onFailure(Throwable caught) {
		}
	};
	
	private ClickHandler addClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {	 
			sendAddPageRequest();
		}

		private void sendAddPageRequest() {
			CategoryInformation categoryInformation = new CategoryInformation();

			getView().getCategoryVerticalPanel().clear();
			
			TextBox software = new TextBox();
			software.setText("Software");
			categoryInformation.setCategoryName(software);

			VerticalPanel verticalPanel = new VerticalPanel();
			verticalPanel.add(new RichTextArea());
			categoryInformation
					.setCategoryDescriptionVerticalPanel(verticalPanel);

			Image categoryImage = new Image("images/categories/software.png");
			categoryInformation.setCategoryImage(categoryImage);

			getView().getCategoryVerticalPanel().add(categoryInformation);
			action = null;
			id = null;
		}
	};
}
