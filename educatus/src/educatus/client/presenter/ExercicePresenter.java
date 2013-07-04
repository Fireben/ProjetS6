
package educatus.client.presenter;

import com.google.gwt.user.client.ui.Button;
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
import educatus.client.ui.widget.ChoiceResponse;

public class ExercicePresenter extends
		Presenter<ExercicePresenter.MyView, ExercicePresenter.MyProxy> {

	public interface MyView extends View {
		public FlowPanel getDescriptionContainer();
		public FlowPanel getDynamicSectionContainer();
		public FlowPanel getContentContainer();
		public Label getTitleLabel();
	}

	@Inject
	private EducatusLocale locale;
	
	String id;

	@ProxyCodeSplit
	@NameToken(NameTokens.exercice)
	public interface MyProxy extends ProxyPlace<ExercicePresenter> {
	}

	@Inject
	public ExercicePresenter(final EventBus eventBus, final MyView view,
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

		getView().getTitleLabel().setText("Routage par paquet");
		
		ChoiceResponse choiceResponse = new ChoiceResponse();
		choiceResponse.addChoice("L'ordre d'arrivee des paquets est garantie dans le routage par paquet");
		choiceResponse.addChoice("Le temps d'etablissement d'un circuit virtuel est negligeable dans le processus de routage par circuit");
		choiceResponse.addChoice("Un circuit virtuel est associee a un circuit secondaire dans le cas d'une panne");
		choiceResponse.addChoice("Aucune");

		getView().getDynamicSectionContainer().add(choiceResponse);
		
		Button submitButton = new Button("Submit");
		submitButton.setStyleName("responseSubmit");
		getView().getContentContainer().add(submitButton);	
		getView().getContentContainer().setVisible(true);
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

	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);
		id = placeRequest.getParameter("id", "1");
	}
}