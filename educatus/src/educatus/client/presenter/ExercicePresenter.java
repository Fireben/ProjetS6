
package educatus.client.presenter;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
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
import educatus.client.ui.widget.ChoiceQuestion;
import educatus.client.ui.widget.MultipleChoiceQuestion;
import educatus.client.ui.widget.Question;
import educatus.client.ui.widget.ResponseFeedback;
import educatus.client.ui.widget.SingleChoiceQuestion;
import educatus.client.ui.widget.TextQuestion;

public class ExercicePresenter extends
		Presenter<ExercicePresenter.MyView, ExercicePresenter.MyProxy> {

	public interface MyView extends View {
		public FlowPanel getDescriptionContainer();
		public FlowPanel getDynamicSectionContainer();
		public FlowPanel getContentContainer();
		public FlowPanel getQuestionContainer();
		public Label getTitleLabel();
		public Button getNextButton();
		public Button getSubmitButton();
		public HTMLPanel getRootPanel();
	}

	@Inject
	private EducatusLocale locale;
	
	private Question currentQuestion;
	private ResponseFeedback responseFeedback = null;
	private int state = 0;
	@SuppressWarnings("unused")
	private String id;

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
	
	private ClickHandler backClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {	 
			handleBackClick();
		}
	};
	
	private ClickHandler submitResponseClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {	 
			verifyResponse();
		}
	};
	
	private ClickHandler nextClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {	 
			if(responseFeedback != null) {
				getView().getRootPanel().remove(responseFeedback);
				responseFeedback = null;
			}
			if(state == 2) {
				state = 0;
			}
			else {
				state++;
			}
			populateQuestion();
		}
	};

	@Override
	protected void onBind() {
		super.onBind();
		getView().getSubmitButton().addClickHandler(submitResponseClickHandler);
		getView().getNextButton().addClickHandler(nextClickHandler);
	}

	@Override
	protected void onReset() {
		super.onReset();
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();
		populateQuestion();
	}
	
	private void populateQuestion() {
		getView().getQuestionContainer().clear();					
		getView().getDynamicSectionContainer().clear();
		
		if(state == 0) {
			getView().getDynamicSectionContainer().add(new Image("images/routing.png"));
			getView().getDynamicSectionContainer().setVisible(true);
			
			getView().getTitleLabel().setText("Routage par paquet");
			
			MultipleChoiceQuestion choiceQuestion = new MultipleChoiceQuestion("Laquelle de ces affirmations est vraie ?");
			choiceQuestion.addChoice("L'ordre d'arrivee des paquets est garantie dans le routage par paquet","1");
			choiceQuestion.addChoice("Le temps d'etablissement d'un circuit virtuel est negligeable dans le processus de routage par circuit","2");
			choiceQuestion.addChoice("Un circuit virtuel est associee a un circuit secondaire dans le cas d'une panne","3");
			choiceQuestion.addChoice("Aucune","4");	
			currentQuestion = choiceQuestion;			
			getView().getQuestionContainer().add(choiceQuestion);		
		}
		else if(state == 1) {
			/*
			PdfViewer pdf = new PdfViewer();
			pdf.setPdfSrc("formatif.pdf");
			getView().getDynamicSectionContainer().add(pdf);
			getView().getDynamicSectionContainer().setVisible(true);
			*/
			getView().getTitleLabel().setText("Routage par paquet");
			
			SingleChoiceQuestion choiceQuestion = new SingleChoiceQuestion("Laquelle de ces affirmations est vraie ?");
			choiceQuestion.addChoice("L'ordre d'arrivee des paquets est garantie dans le routage par paquet","1");
			choiceQuestion.addChoice("Le temps d'etablissement d'un circuit virtuel est negligeable dans le processus de routage par circuit","2");
			choiceQuestion.addChoice("Un circuit virtuel est associee a un circuit secondaire dans le cas d'une panne","3");
			choiceQuestion.addChoice("Aucune","4");	
			currentQuestion = choiceQuestion;
			getView().getQuestionContainer().add(choiceQuestion);		
		}
		else if(state == 2) {
			getView().getDynamicSectionContainer().add(new Image("images/canal.png"));
			getView().getDynamicSectionContainer().setVisible(true);
			
			getView().getTitleLabel().setText("Signal sur bruit");
			TextQuestion textQuestion = new TextQuestion("Quel est le rapport signal sur bruit ? (en dB)");
			currentQuestion = textQuestion;
			getView().getQuestionContainer().add(textQuestion);
		}		
		getView().getContentContainer().setVisible(true);
	}

	private void verifyResponse() {		
		boolean validAnswer = false;
		if(currentQuestion instanceof TextQuestion) {
			validAnswer = ((TextQuestion)currentQuestion).getResponse().equals("54");
		}
		else if(currentQuestion instanceof ChoiceQuestion) {
			validAnswer = verifyChoiceResponse();
		}
		
		if(validAnswer) {
			responseFeedback = new ResponseFeedback("Congrats, you have the right answer", "images/Good.png", "Next", nextClickHandler);
		}
		else {
			responseFeedback = new ResponseFeedback("You don't have the right answer, try again", "images/error.png", "Back", backClickHandler);
		}
		
		getView().getContentContainer().setVisible(false);
		getView().getRootPanel().add(responseFeedback);
	}
	

	private boolean verifyChoiceResponse() {
		boolean validAnswer = false;
		if(currentQuestion instanceof MultipleChoiceQuestion) {
			ArrayList<String> rightResponses = new ArrayList<String>();
			rightResponses.add("1");
			rightResponses.add("3");		
			
			ArrayList<String> checkedResponses = ((MultipleChoiceQuestion) currentQuestion).getValues();
			validAnswer = rightResponses.equals(checkedResponses);
		}
		else if(currentQuestion instanceof SingleChoiceQuestion) {
			String id = ((SingleChoiceQuestion)currentQuestion).getValue();
			if(id != null) {
				validAnswer = id.equals("4");			
			}
		}		
		return validAnswer;
	}

	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);
		id = placeRequest.getParameter("id", "1");
	}
	
	private void handleBackClick() {
		getView().getRootPanel().remove(responseFeedback);
		responseFeedback = null;
		if(currentQuestion instanceof ChoiceQuestion) {
			((ChoiceQuestion)currentQuestion).uncheckAll();
		}
		else if(currentQuestion instanceof TextQuestion) {
			((TextQuestion)currentQuestion).clearResponse();
		}
		getView().getContentContainer().setVisible(true);
	}
}