
package educatus.client.presenter;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.EducatusLocale;
import educatus.client.NameTokens;
import educatus.client.ui.widget.DescriptionEntry;
import educatus.client.ui.widget.DynamicSection;
import educatus.client.ui.widget.SeenMessage;
import educatus.client.ui.widget.StarDescriptionEntry;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.dto.seminary.SeminaryCoreContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.SeminaryContentRequest;
import educatus.shared.services.requestservice.response.SeminaryContentResponse;

public class SeminaryViewPresenter extends
		Presenter<SeminaryViewPresenter.MyView, SeminaryViewPresenter.MyProxy> {

	public interface MyView extends View {
		public FlowPanel getDescriptionContainer();
		public DynamicSection getDynamicSection();
		public FlowPanel getContentContainer();
		public Label getTitleLabel();
		public Button getSeenButton();
		public FlowPanel getStatsContainer();
	}

	@Inject
	private EducatusLocale locale;	
	String id;
	
	private ClickHandler seenButtonHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			getView().getSeenButton().removeFromParent();
			getView().getStatsContainer().add(new SeenMessage());
		}
	};

	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT
			.create(RequestService.class);

	@ProxyCodeSplit
	@NameToken(NameTokens.viewSeminary)
	public interface MyProxy extends ProxyPlace<SeminaryViewPresenter> {
	}

	@Inject
	public SeminaryViewPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
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
		getView().getSeenButton().addClickHandler(seenButtonHandler);
	}

	@Override
	protected void onReset() {
		super.onReset();
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();
		//Clear the content to prevent having the data of a previous seminary
		getView().getDynamicSection().clear();
		getView().getDynamicSection().setVisible(true);
		
		getView().getDescriptionContainer().clear();
		getView().getDescriptionContainer().setVisible(false);
		
		getView().getContentContainer().setVisible(false);		
		
		SeminaryContentRequest request = new SeminaryContentRequest();
		request.setCulture(locale.getCulture());
		request.setLanguage(locale.getLanguage());
		request.setSelectedSeminaryId(Integer.parseInt(id));
		requestService.sendRequest(request,
				new AsyncCallback<AbstractResponse>() {
					@Override
					public void onSuccess(AbstractResponse result) {
						if (result.GetResponseType() == ResponseTypeEnum.SEMINARY_CONTENT_RESPONSE) {
							SeminaryContentResponse response = (SeminaryContentResponse) result;
							SeminaryContent seminaryContent = response
									.getSeminaryContent();
							populateSeminaryView(seminaryContent);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
	}


	private void populateSeminaryView(SeminaryContent seminaryContent) {		
		FlowPanel descriptionContainer = getView().getDescriptionContainer();
		SeminaryCoreContent coreContent = seminaryContent.getCoreContent();

		getView().getTitleLabel().setText(coreContent.getTitle());

		descriptionContainer.add(new DescriptionEntry("Author", coreContent.getAuthor().getFirstName() + " " + coreContent.getAuthor().getLastName()));
		descriptionContainer.add(new DescriptionEntry("Description", coreContent.getDescription()));
		descriptionContainer.add(new StarDescriptionEntry("Difficulty", 4));
		descriptionContainer.add(new DescriptionEntry("Created Date", coreContent.getCreatedDate()));

		DynamicSection dynamicSection = getView().getDynamicSection();
		List<AbstractDynamicSection> dynamicSectionList = seminaryContent.getDynamicSectionList();
		dynamicSection.setList(dynamicSectionList, getView().getContentContainer());
		getView().getContentContainer().add(dynamicSection);

		getView().getContentContainer().setVisible(true);
		descriptionContainer.setVisible(true);
		getView().getSeenButton().setVisible(true);
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);
		id = placeRequest.getParameter("id", "1");
	}
}