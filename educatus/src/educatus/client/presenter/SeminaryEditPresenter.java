package educatus.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
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

import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;
import educatus.client.ui.widget.ImageEdit;
import educatus.client.ui.widget.TextEdit;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.dto.seminary.SeminaryCoreContent;

public class SeminaryEditPresenter extends
		Presenter<SeminaryEditPresenter.MyView, SeminaryEditPresenter.MyProxy> {
	public interface MyView extends View {
		public FlowPanel getSeminaryDescriptionContainer();
		public FlowPanel getContentPanel();
		public TextBox getSemTitleBox();
		public TextArea getSemDescBox();
		public ListBox getSemDiffBox();
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.seminaryEdit)
	public interface MyProxy extends ProxyPlace<SeminaryEditPresenter> {
	}

	private ClickHandler addTextHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			getView().getContentPanel().add(new TextEdit());
		}
	};

	private ClickHandler addImageHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			getView().getContentPanel().add(new ImageEdit());
		}
	};
	
	private ClickHandler confirmHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			submitSeminary();
		}	
	};

	@Inject
	ConfirmChangesPresenter confirmPresenter;

	public static final Object SLOT_confirm = new Object();

	@Inject
	public SeminaryEditPresenter(final EventBus eventBus, final MyView view,
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
		setInSlot(SLOT_confirm, confirmPresenter);
		confirmPresenter.setAddTextHandler(addTextHandler);
		confirmPresenter.setAddImageHandler(addImageHandler);
		confirmPresenter.setConfirmHandler(confirmHandler);
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();
		PageChangingEvent.fire(this, NameTokens.getSeminaryEdit());
	}
	
	public void submitSeminary() {
		List<AbstractDynamicSection> dynamicSectionList = getDynamicContentList();
		SeminaryCoreContent coreContent = getCoreContent();
		
		SeminaryContent seminaryContent= new SeminaryContent();
		seminaryContent.setCoreContent(coreContent);
		seminaryContent.setDynamicSectionList(dynamicSectionList);
	}
	
	private SeminaryCoreContent getCoreContent() {
		SeminaryCoreContent coreContent = new SeminaryCoreContent();
		coreContent.setDescription(getView().getSemDescBox().getText());
		coreContent.setTitle(getView().getSemTitleBox().getText());
		
		ListBox difficultyBox = getView().getSemDiffBox();
		int index = difficultyBox.getSelectedIndex();
		coreContent.setDifficulty(difficultyBox.getValue(index));
		
		return coreContent;
	}

	public List<AbstractDynamicSection> getDynamicContentList() {
		List<AbstractDynamicSection> dynamicSectionList = new ArrayList<AbstractDynamicSection>();
		FlowPanel contentPanel = getView().getContentPanel();
		Widget currentWidget;
		for(int i=0; i<contentPanel.getWidgetCount(); i++) {
			currentWidget = contentPanel.getWidget(i);
			if(currentWidget instanceof TextEdit) {
				TextEdit textEdit = ((TextEdit)currentWidget);
				DynamicSectionTextContent dynamicSectionTextContent = new DynamicSectionTextContent();
				dynamicSectionTextContent.setText(textEdit.getText());
				dynamicSectionTextContent.setTitle(textEdit.getTitle());
				dynamicSectionList.add(dynamicSectionTextContent);
			}
			else if(currentWidget instanceof ImageEdit) {
				ImageEdit imageEdit = ((ImageEdit)currentWidget);
				String id = imageEdit.getImageId();
				DynamicSectionImageContent dynamicSectionImageContent = new DynamicSectionImageContent();
				dynamicSectionImageContent.setImageUrl(id);
				dynamicSectionImageContent.setImageDescription(imageEdit.getTitle());
				dynamicSectionList.add(dynamicSectionImageContent);
			}
		}
		return dynamicSectionList;
	}
}
