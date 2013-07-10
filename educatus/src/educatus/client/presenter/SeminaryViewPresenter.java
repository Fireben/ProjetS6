
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
import educatus.client.ui.PdfViewer;
import educatus.client.ui.widget.DescriptionEntry;
import educatus.client.ui.widget.StarDescriptionEntry;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionPDFContent;
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
		public FlowPanel getContentContainer();
		public FlowPanel getPdfSectionContainer();
		public Label getTitleLabel();
	}

	@Inject
	private EducatusLocale locale;
	
	String id;

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
	}

	@Override
	protected void onReset() {
		super.onReset();
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();
		//Clear the content to prevent having the data of a previous seminary
		getView().getDynamicSectionContainer().clear();
		getView().getDynamicSectionContainer().setVisible(true);
		
		getView().getPdfSectionContainer().clear();
		
		getView().getDescriptionContainer().clear();
		getView().getDescriptionContainer().setVisible(false);
		
		getView().getContentContainer().setVisible(false);
		getView().getContentContainer().setWidth("700px");			
		
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

		FlowPanel dynamicSectionContainer = getView().getDynamicSectionContainer();
		List<AbstractDynamicSection> dynamicSectionList = seminaryContent.getDynamicSectionList();

		for (AbstractDynamicSection abstractDynamicSection : dynamicSectionList) {
			switch (abstractDynamicSection.getSectionType()) {
			case FORMULA_SECTION:
				break;
			case IMAGE_SECTION:
				DynamicSectionImageContent imageContent = (DynamicSectionImageContent) abstractDynamicSection;
				addImageSection(imageContent.getImageUrl());
				break;
			case TEXT_SECTION:
				DynamicSectionTextContent textContent = (DynamicSectionTextContent) abstractDynamicSection;
				addTextSection(textContent.getTitle(), textContent.getText());
				break;
			case VIDEO_SECTION:
				/*
				 * String src =
				 * "http://www.youtube.com/embed/Gm-RO-cmsEQ?list=PL29DDDC847F63AF82";
				 * HTML videoHtml = new HTML("<iframe width=\"560\" height=\"315\" src="
				 * + src + " frameborder=\"0\" allowfullscreen></iframe>");
				 */
				break;
			case PDF_SECTION:
				DynamicSectionPDFContent pdfContent = (DynamicSectionPDFContent) abstractDynamicSection;
				addPDFSection(pdfContent.getPDFUrl());
				break;
			}
			dynamicSectionContainer.add(new HTML("<br/>"));
		}

		getView().getContentContainer().setVisible(true);
		descriptionContainer.setVisible(true);
	}

	private void addImageSection(String imageUrl) {
		FlowPanel dynamicSectionContainer = getView().getDynamicSectionContainer();
		dynamicSectionContainer.add(new Image(imageUrl));
	}

	private void addTextSection(String title, String text) {
		FlowPanel dynamicSectionContainer = getView().getDynamicSectionContainer();
		Label titleLabel = new Label(title);
		titleLabel.setStyleName("title");
		dynamicSectionContainer.add(titleLabel);
		dynamicSectionContainer.add(new HTML(text));		
	}
	
	private void addPDFSection(String pdfUrl) {
		getView().getContentContainer().setWidth("900px");
		getView().getDynamicSectionContainer().setVisible(false);
		
		PdfViewer pdfViewer = new PdfViewer();
		pdfViewer.setPdfSrc(pdfUrl);
		getView().getPdfSectionContainer().add(pdfViewer);
	}

	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);
		id = placeRequest.getParameter("id", "1");
	}
}