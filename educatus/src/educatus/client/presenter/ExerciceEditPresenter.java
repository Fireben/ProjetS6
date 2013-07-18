package educatus.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.CookiesConst;
import educatus.client.EducatusLocale;
import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;
import educatus.client.ui.CustomButton;
import educatus.client.ui.widget.ChoiceEdit;
import educatus.client.ui.widget.ImageEdit;
import educatus.client.ui.widget.PdfEdit;
import educatus.client.ui.widget.QuestionEdit;
import educatus.client.ui.widget.TextAnswerEdit;
import educatus.client.ui.widget.TextEdit;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
import educatus.shared.dto.exercice.AnswerChoiceContent;
import educatus.shared.dto.exercice.AnswerTextContent;
import educatus.shared.dto.exercice.ExerciceContent;
import educatus.shared.dto.exercice.ExerciceCoreContent;
import educatus.shared.dto.exercice.ExerciceQuestionContent;
import educatus.shared.dto.exercice.ExerciceQuestionType;
import educatus.shared.dto.pagecontent.SeminaryAdministrationPageContent;
import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.dto.seminary.DifficultyContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.ExerciceAdministrationActionRequest;
import educatus.shared.services.requestservice.request.SeminaryAdministrationPageContentRequest;
import educatus.shared.services.requestservice.request.ExerciceAdministrationActionRequest.ExerciceAdministractionAction;
import educatus.shared.services.requestservice.response.SeminaryAdministrationPageContentResponse;

public class ExerciceEditPresenter extends Presenter<ExerciceEditPresenter.MyView, ExerciceEditPresenter.MyProxy> {
	public interface MyView extends View {
		public FlowPanel getSeminaryDescriptionContainer();

		public FlowPanel getContentPanel();

		public TextBox getTitleBox();

		public TextArea getDescriptionBox();

		public ListBox getDifficultyBox();

		public ListBox getCategoryBox();
	}

	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT.create(RequestService.class);

	// Response handler
	private AbstractResponseHandler responseHandler = null;

	@Inject
	private EducatusLocale locale;

	@ProxyCodeSplit
	@NameToken(NameTokens.exerciceEdit)
	public interface MyProxy extends ProxyPlace<ExerciceEditPresenter> {
	}

	private ClickHandler closeTextHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			CustomButton closeButton = (CustomButton) event.getSource();
			FlowPanel panelParent = (FlowPanel) closeButton.getParent();
			TextEdit parent = (TextEdit) panelParent.getParent();
			getView().getContentPanel().remove(parent);
		}
	};

	private ClickHandler closeImageHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			CustomButton closeButton = (CustomButton) event.getSource();
			FlowPanel panelParent = (FlowPanel) closeButton.getParent();
			ImageEdit parent = (ImageEdit) panelParent.getParent();
			getView().getContentPanel().remove(parent);
		}
	};

	private ClickHandler closePdfHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			CustomButton closeButton = (CustomButton) event.getSource();
			FlowPanel panelParent = (FlowPanel) closeButton.getParent();
			PdfEdit parent = (PdfEdit) panelParent.getParent();
			getView().getContentPanel().remove(parent);
		}
	};

	private ClickHandler closeQuestionHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			CustomButton closeButton = (CustomButton) event.getSource();
			FlowPanel panelParent = (FlowPanel) closeButton.getParent();
			QuestionEdit parent = (QuestionEdit) panelParent.getParent();
			getView().getContentPanel().remove(parent);
		}
	};

	private ClickHandler addTextHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			TextEdit textEdit = new TextEdit();
			textEdit.getCloseButton().addClickHandler(closeTextHandler);
			getView().getContentPanel().add(textEdit);
		}
	};

	private ClickHandler addImageHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			ImageEdit imageEdit = new ImageEdit();
			imageEdit.getCloseButton().addClickHandler(closeImageHandler);
			getView().getContentPanel().add(imageEdit);
		}
	};

	private ClickHandler addPdfHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			PdfEdit pdfEdit = new PdfEdit();
			pdfEdit.getCloseButton().addClickHandler(closePdfHandler);
			getView().getContentPanel().add(pdfEdit);
		}
	};

	private ClickHandler addQuestionHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			QuestionEdit questionEdit = new QuestionEdit();
			questionEdit.getCloseButton().addClickHandler(closeQuestionHandler);
			getView().getContentPanel().add(questionEdit);
		}
	};

	private ClickHandler cancelHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			resetAll();
		}
	};

	private ClickHandler confirmHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			ExerciceContent exerciceContent = getExerciceContent();
			ExerciceAdministrationActionRequest request = new ExerciceAdministrationActionRequest();
			request.setExerciceContent(exerciceContent);
			request.setAction(ExerciceAdministractionAction.INSERT);
			request.setSessionID(Cookies.getCookie(CookiesConst.SESSION_ID));
			
			requestService.sendRequest(request, new AsyncCallback<AbstractResponse>() {

				@Override
				public void onSuccess(AbstractResponse result) {
					resetAll();
				}

				@Override
				public void onFailure(Throwable caught) {
				}
			});
		}
	};

	@Inject
	EditButtonPanelPresenter editButtonPanelPresenter;

	public static final Object SLOT_confirm = new Object();

	@Inject
	public ExerciceEditPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
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

		editButtonPanelPresenter.getView().getAddSectionPanel().clear();

		editButtonPanelPresenter.addSectionButton("images/addText.png", addTextHandler);
		editButtonPanelPresenter.addSectionButton("images/addImage.png", addImageHandler);
		editButtonPanelPresenter.addSectionButton("images/addPdf.png", addPdfHandler);
		editButtonPanelPresenter.addSectionButton("images/addVideo.png", addPdfHandler);
		editButtonPanelPresenter.addSectionButton("images/addQuestion.png", addQuestionHandler);

		editButtonPanelPresenter.setSaveButtonHandler(confirmHandler);
		editButtonPanelPresenter.setCancelHandler(cancelHandler);

		getView().getSeminaryDescriptionContainer().clear();

		SeminaryAdministrationPageContentRequest pageContentRequest = new SeminaryAdministrationPageContentRequest();
		pageContentRequest.setCulture(locale.getCulture());
		pageContentRequest.setLanguage(locale.getLanguage());
		requestService.sendRequest(pageContentRequest, responseHandler);
		PageChangingEvent.fire(this, NameTokens.getExerciceEdit());

	}

	@Override
	protected void onReveal() {
		super.onReveal();
		PageChangingEvent.fire(this, NameTokens.getSeminaryEdit());
	}

	private ExerciceContent getExerciceContent() {
		List<ExerciceQuestionContent> questionList = getQuestionList();
		ExerciceCoreContent coreContent = getCoreContent();

		ExerciceContent exerciceContent = new ExerciceContent();
		exerciceContent.setCoreContent(coreContent);
		exerciceContent.setQuestionList(questionList);

		return exerciceContent;
	}

	private ExerciceCoreContent getCoreContent() {
		ExerciceCoreContent coreContent = new ExerciceCoreContent();
		coreContent.setDescription(getView().getDescriptionBox().getText());
		coreContent.setTitle(getView().getTitleBox().getText());

		ListBox difficultyBox = getView().getDifficultyBox();
		int index = difficultyBox.getSelectedIndex();
		coreContent.setDifficulty(difficultyBox.getValue(index));

		return coreContent;
	}

	public List<ExerciceQuestionContent> getQuestionList() {
		List<ExerciceQuestionContent> questionList = new ArrayList<ExerciceQuestionContent>();

		List<AbstractDynamicSection> dynamicSectionList = new ArrayList<AbstractDynamicSection>();
		FlowPanel contentPanel = getView().getContentPanel();
		Widget currentWidget;
		for (int i = 0; i < contentPanel.getWidgetCount(); i++) {
			currentWidget = contentPanel.getWidget(i);
			
			if (currentWidget instanceof TextEdit) {
				TextEdit textEdit = ((TextEdit) currentWidget);
				DynamicSectionTextContent dynamicSectionTextContent = new DynamicSectionTextContent();
				dynamicSectionTextContent.setText(textEdit.getText());
				dynamicSectionTextContent.setTitle(textEdit.getTitle());
				dynamicSectionList.add(dynamicSectionTextContent);
			} 
			
			else if (currentWidget instanceof ImageEdit) {
				ImageEdit imageEdit = ((ImageEdit) currentWidget);
				String id = imageEdit.getImageId();
				DynamicSectionImageContent dynamicSectionImageContent = new DynamicSectionImageContent();
				dynamicSectionImageContent.setImageUrl(id);
				dynamicSectionImageContent.setImageDescription(imageEdit.getTitle());
				dynamicSectionList.add(dynamicSectionImageContent);
			} 
			
			else if (currentWidget instanceof QuestionEdit) {
				ExerciceQuestionContent exerciceQuestion = new ExerciceQuestionContent();

				QuestionEdit questionEdit = ((QuestionEdit) currentWidget);
				Widget answer = questionEdit.getAnswer();
				if (answer instanceof ChoiceEdit) {
					ChoiceEdit choiceEdit = (ChoiceEdit) answer;
					AnswerChoiceContent answerChoiceContent = choiceEdit.getAnswerChoiceContent();
					exerciceQuestion.setAnswer(answerChoiceContent);
					exerciceQuestion.setQuestionType(ExerciceQuestionType.ANSWER_CHOICE);
				} 
				
				else if (answer instanceof TextAnswerEdit) {
					TextAnswerEdit textAnswerEdit = (TextAnswerEdit) answer;
					AnswerTextContent answerTextContent = textAnswerEdit.getAnswerTextContent();
					exerciceQuestion.setAnswer(answerTextContent);
					exerciceQuestion.setQuestionType(ExerciceQuestionType.ANSWER_TEXT);
				}
				
				exerciceQuestion.setQuestion(questionEdit.getQuestion());
				exerciceQuestion.setQuestionContext(dynamicSectionList);
				dynamicSectionList = new ArrayList<AbstractDynamicSection>();

				questionList.add(exerciceQuestion);
			}
		}
		return questionList;
	}

	private class AbstractResponseHandler implements AsyncCallback<AbstractResponse> {
		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(AbstractResponse result) {
			if (result.GetResponseType() == ResponseTypeEnum.SEMINARY_ADMINISTRATION_PAGE_CONTENT_RESPONSE) {
				SeminaryAdministrationPageContentResponse response = (SeminaryAdministrationPageContentResponse) result;
				populateCoreContent(response.getSeminaryEditorContent(), response.getDifficultyContentList(), response.getCategoryCoreContentList());
				setInSlot(SLOT_confirm, editButtonPanelPresenter);
			}
		}
	}

	private void populateCoreContent(SeminaryAdministrationPageContent pageContent, List<DifficultyContent> difficultyList, List<CategoryCoreContent> categoryList) {
		FlowPanel descriptionContainer = getView().getSeminaryDescriptionContainer();
		descriptionContainer.add(new Label(pageContent.getTitleText()));
		descriptionContainer.add(getView().getTitleBox());
		descriptionContainer.add(new Label(pageContent.getDescriptionText()));
		descriptionContainer.add(getView().getDescriptionBox());

		descriptionContainer.add(new Label(pageContent.getDifficultyText()));
		ListBox difficultyBox = getView().getDifficultyBox();
		difficultyBox.clear();
		for (DifficultyContent difficulty : difficultyList) {
			difficultyBox.addItem(difficulty.getName());
		}
		descriptionContainer.add(difficultyBox);

		descriptionContainer.add(new Label(pageContent.getCategoryText()));
		ListBox categoryBox = getView().getCategoryBox();
		categoryBox.clear();
		for (CategoryCoreContent category : categoryList) {
			categoryBox.addItem(category.getName());
		}
		descriptionContainer.add(categoryBox);
	}

	private void resetAll() {
		getView().getContentPanel().clear();
		getView().getSeminaryDescriptionContainer().clear();

		getView().getCategoryBox().clear();
		getView().getDifficultyBox().clear();

		getView().getDescriptionBox().setValue("");
		getView().getTitleBox().setValue("");

		SeminaryAdministrationPageContentRequest pageContentRequest = new SeminaryAdministrationPageContentRequest();
		pageContentRequest.setCulture(locale.getCulture());
		pageContentRequest.setLanguage(locale.getLanguage());
		requestService.sendRequest(pageContentRequest, responseHandler);
	}
}
