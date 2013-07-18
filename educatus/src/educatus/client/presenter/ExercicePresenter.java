
package educatus.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
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

import educatus.client.CookiesConst;
import educatus.client.EducatusLocale;
import educatus.client.NameTokens;
import educatus.client.ui.widget.ChoiceQuestion;
import educatus.client.ui.widget.DynamicSection;
import educatus.client.ui.widget.MultipleChoiceQuestion;
import educatus.client.ui.widget.Question;
import educatus.client.ui.widget.ResponseFeedback;
import educatus.client.ui.widget.SingleChoiceQuestion;
import educatus.client.ui.widget.TextQuestion;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.exercice.AnswerChoiceContent;
import educatus.shared.dto.exercice.AnswerChoiceContent.AnswerChoiceType;
import educatus.shared.dto.exercice.AnswerTextContent;
import educatus.shared.dto.exercice.ExerciceContent;
import educatus.shared.dto.exercice.ExerciceCoreContent;
import educatus.shared.dto.exercice.ExerciceQuestionContent;
import educatus.shared.dto.exercice.ExerciceQuestionType;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.ExerciceContentRequest;
import educatus.shared.services.requestservice.request.ExerciceQuestionValidationRequest;
import educatus.shared.services.requestservice.response.ExerciceContentResponse;
import educatus.shared.services.requestservice.response.ExerciceQuestionValidationResponse;

public class ExercicePresenter extends
		Presenter<ExercicePresenter.MyView, ExercicePresenter.MyProxy> {

	public interface MyView extends View {
		public FlowPanel getDescriptionContainer();
		public DynamicSection getDynamicSection();
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
	
	private int questionIndex = 0;
	private List<ExerciceQuestionContent> questionList;
	private ExerciceCoreContent questionCore;
	
	private String id;
	
	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT
			.create(RequestService.class);

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
			reset();
			if(responseFeedback != null) {
				getView().getRootPanel().remove(responseFeedback);
				responseFeedback = null;
			}
			
			if(questionIndex < (questionList.size()-1)) {
				questionIndex++;				
			}		
			else {
				questionIndex = 0;
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
		reset();
		ExerciceContentRequest request = new ExerciceContentRequest();
		request.setCulture(locale.getCulture());
		request.setLanguage(locale.getLanguage());
		request.setSelectedExerciceId(Integer.parseInt(id));
		requestService.sendRequest(request,
				new AsyncCallback<AbstractResponse>() {
					@Override
					public void onSuccess(AbstractResponse result) {
						if (result.GetResponseType() == ResponseTypeEnum.EXERCICE_CONTENT_RESPONSE) {
							ExerciceContentResponse response = (ExerciceContentResponse) result;
							ExerciceContent exerciceContent = response.getExerciceContent();
							populateExercice(exerciceContent);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
	}
	
	private void populateExercice(ExerciceContent exerciceContent) {
		questionIndex = 0;
		questionList = exerciceContent.getQuestionList();
		questionCore = exerciceContent.getCoreContent();
		populateQuestion();
	}

	private void populateQuestion() {		
		getView().getTitleLabel().setText(questionCore.getTitle());
		
		ExerciceQuestionContent question = questionList.get(questionIndex);			
		if(question.getQuestionType() == ExerciceQuestionType.ANSWER_TEXT) {
			TextQuestion textQuestion = new TextQuestion(question.getQuestion());
			currentQuestion = textQuestion;
			getView().getQuestionContainer().add(textQuestion);	
		}	
		else if(question.getQuestionType() == ExerciceQuestionType.ANSWER_CHOICE) {
			AnswerChoiceContent answer = (AnswerChoiceContent)question.getAnswer();
			ChoiceQuestion choiceQuestion;
			if(answer.getType() == AnswerChoiceType.MULTIPLE_CHOICE) {
				choiceQuestion = new MultipleChoiceQuestion(question.getQuestion());
			}
			else {
				choiceQuestion = new SingleChoiceQuestion(question.getQuestion());
			}
			List<String> choiceList = answer.getAvailableChoiceList();
			for(int i=0; i < choiceList.size(); i++) {
				choiceQuestion.addChoice(choiceList.get(i), String.valueOf(i));
			}
			currentQuestion = choiceQuestion;
			getView().getQuestionContainer().add(choiceQuestion);
		}
		List<AbstractDynamicSection> dynamicSectionList = question.getQuestionContext();
		DynamicSection dynamicSection = getView().getDynamicSection();
		dynamicSection.setList(dynamicSectionList, getView().getContentContainer());
		
		if(questionList.size() == (questionIndex +1)) {
			getView().getNextButton().setVisible(false);
		}
		getView().getContentContainer().setVisible(true);
	}

	private void verifyResponse() {		
		ExerciceQuestionContent questionContent = questionList.get(questionIndex);
		int answerId = questionContent.getAnswer().getId();
		
		if(currentQuestion instanceof MultipleChoiceQuestion) {			
			ArrayList<String> checkedResponses = ((MultipleChoiceQuestion) currentQuestion).getValues();
			AnswerChoiceContent answer = new AnswerChoiceContent();
			answer.setAnswerList(checkedResponses);
			answer.setType(AnswerChoiceType.MULTIPLE_CHOICE);
			answer.setId(answerId);
			questionContent.setAnswer(answer);
		}
		else if(currentQuestion instanceof SingleChoiceQuestion) {
			ArrayList<String> checkedResponses = new ArrayList<String>();
			String id = ((SingleChoiceQuestion)currentQuestion).getValue();
			checkedResponses.add(id);
			AnswerChoiceContent answer = new AnswerChoiceContent();
			answer.setAnswerList(checkedResponses);
			answer.setType(AnswerChoiceType.SINGLE_CHOICE);
			answer.setId(answerId);
			questionContent.setAnswer(answer);
		}		
		else if(currentQuestion instanceof TextQuestion) {
			String response = ((TextQuestion)currentQuestion).getResponse();
			AnswerTextContent answer = new AnswerTextContent();
			answer.setId(answerId);
			answer.setTextAnswer(response);
			questionContent.setAnswer(answer);
		}
		
		ExerciceQuestionValidationRequest validationRequest = new ExerciceQuestionValidationRequest();
		validationRequest.setExerciceQuestion(questionContent);
		validationRequest.setSessionID(Cookies.getCookie(CookiesConst.SESSION_ID));
		validationRequest.setCulture(locale.getCulture());
		validationRequest.setLanguage(locale.getLanguage());
		requestService.sendRequest(validationRequest, 
			new AsyncCallback<AbstractResponse>() {
				@Override
				public void onSuccess(AbstractResponse result) {
					if (result.GetResponseType() == ResponseTypeEnum.EXERCICE_QUESTION_VALIDATION_RESPONSE) {
						ExerciceQuestionValidationResponse response = (ExerciceQuestionValidationResponse) result;
						boolean validAnswer = response.isValid();
						if(validAnswer) {
							responseFeedback = new ResponseFeedback("Congrats, you have the right answer", "images/Good.png", "Next", nextClickHandler);
						}
						else {
							responseFeedback = new ResponseFeedback("You don't have the right answer, try again", "images/error.png", "Back", backClickHandler);
						}
						getView().getContentContainer().setVisible(false);
						getView().getRootPanel().add(responseFeedback);
					}
				}
	
				@Override
				public void onFailure(Throwable caught) {
				}
		});
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
	
	protected void reset() {
		getView().getNextButton().setVisible(true);
		getView().getQuestionContainer().clear();					
		getView().getDynamicSection().clear();	
	}
}