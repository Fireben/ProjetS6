package educatus.client.presenter;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
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
import educatus.client.events.PageChangingEvent;
import educatus.client.ui.widget.DescriptionEntry;
import educatus.client.ui.widget.StarDescriptionEntry;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
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
		public FlowPanel getDynamicSectionContainer();
		public Label getTitleLabel();
	}
    
	@Inject
	private EducatusLocale locale;

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

		/*
		Label label = new Label();
		label.setText("Label Text");
		getView().insertContent(label);

		Image img = new Image();
		img.setUrl("images/earth_puzzle_3.png");
		getView().insertContent(img);

		String src = "http://www.youtube.com/embed/Gm-RO-cmsEQ?list=PL29DDDC847F63AF82";
		HTML videoHtml = new HTML("<iframe width=\"560\" height=\"315\" src="
				+ src + " frameborder=\"0\" allowfullscreen></iframe>");
		getView().insertContent(videoHtml);

		String srcVideo = "http://www.dailymotion.com/embed/video/xzfw9p";
		HTML videoDailyMotion = new HTML(
				"<iframe width=\"560\" height=\"315\" src=" + srcVideo
						+ " frameborder=\"0\" allowfullscreen></iframe>");
		getView().insertContent(videoDailyMotion);
		*/
		/*
		 * if(type == "text") { Label label = new Label();
		 * label.setText(content); getView().insertContent(label); } else
		 * if(type == "image") { Image img = new Image(); img.setUrl(url);
		 * getView().insertContent(img); } else if(type == "video") { HTML
		 * videoHtml = new HTML("<iframe width=\"560\" height=\"315\" src=" +
		 * videoSrc + " frameborder=\"0\" allowfullscreen></iframe>");
		 * getView().insertContent(videoHtml); }
		 */
	}

	@Override
	protected void onReset() {
		super.onReset();
		
		getView().getDynamicSectionContainer().clear();
		getView().getDescriptionContainer().clear();
		
		/*
		getView().getWrittenBy().setInnerHTML("written by");
		getView().getAuthor().setInnerHTML("Author");
		getView().getAuthorEmail().setPropertyString("href",
				"mailto:Prof@usherbrooke.qc.ca");
		getView().getCreatedDate().setInnerHTML("23/03/13");
		getView().getModifyBy().setInnerHTML("modified by");
		getView().getModif().setInnerHTML("Modif");
		getView().getModifEmail().setPropertyString("href",
				"mailto:Modif@usherbrooke.qc.ca");
		getView().getModifiedDate().setInnerHTML("25/03/13");
		*/
	}

	private void populateSeminaryView(SeminaryContent seminaryContent) {
		FlowPanel descriptionContainer = getView().getDescriptionContainer();
		SeminaryCoreContent coreContent = seminaryContent.getCoreContent();
		
		getView().getTitleLabel().setText(coreContent.getTitle());
		
		descriptionContainer.add(new DescriptionEntry("Author", coreContent.getAuthor()));
		descriptionContainer.add(new DescriptionEntry("Description", coreContent.getDescription()));
		descriptionContainer.add(new StarDescriptionEntry("Difficulty", 4));
		descriptionContainer.add(new DescriptionEntry("Created Date", coreContent.getCreatedDate()));		
		
		List<AbstractDynamicSection> dynamicSectionList = seminaryContent
				.getDynamicSectionList();
		
		for (AbstractDynamicSection abstractDynamicSection : dynamicSectionList) {
			switch (abstractDynamicSection.getSectionType()) {			
				case FORMULA_SECTION:
					//DynamicSectionFormulaContent content1 = (DynamicSectionFormulaContent) abstractDynamicSection;					
					break;
				case IMAGE_SECTION:
					DynamicSectionImageContent imageContent = (DynamicSectionImageContent) abstractDynamicSection;
					getView().getDynamicSectionContainer().add(new Image(imageContent.getImageUrl()));
					break;
				case TEXT_SECTION:
					DynamicSectionTextContent textContent = (DynamicSectionTextContent) abstractDynamicSection;					
					Label titleLabel = new Label(textContent.getTitle());
					titleLabel.setStyleName("title");
					getView().getDynamicSectionContainer().add(titleLabel);
					getView().getDynamicSectionContainer().add(new Label(textContent.getText()));
					break;
				case VIDEO_SECTION:
					//DynamicSectionVideoContent content4 = (DynamicSectionVideoContent) abstractDynamicSection;
					break;
			}
			getView().getDynamicSectionContainer().add(new HTML("<br/>"));
		}			
	}

	@Override
	protected void onReveal() {
		PageChangingEvent.fire(this, NameTokens.getViewSeminary());
	}

	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);

		String id = placeRequest.getParameter("id", "1");
		System.out.println("Mon id est : " + id);
		
		SeminaryContentRequest request = new SeminaryContentRequest();
		request.setCulture(locale.getCulture());
		request.setLanguage(locale.getLanguage());
		request.setSelectedSeminaryId(Integer.parseInt(id));
		requestService.sendRequest(request, new AsyncCallback<AbstractResponse>() {

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
				// TODO Auto-generated method stub

			}
		});
	}
}