package educatus.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
import educatus.shared.dto.seminary.SeminaryContent;

public class SeminaryEditPresenter extends
		Presenter<SeminaryEditPresenter.MyView, SeminaryEditPresenter.MyProxy> {
	public interface MyView extends View {
		public FlowPanel getSeminaryDescriptionContainer();
		public HTMLPanel getContentPanel();
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.seminaryEdit)
	public interface MyProxy extends ProxyPlace<SeminaryEditPresenter> {
	}

	private ClickHandler addTextHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			textEditFactory
					.get(new AsyncCallback<TextEditPresenter>() {
						@Override
						public void onSuccess(TextEditPresenter result) {
							addToSlot(SLOT_content, result);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Error while creating a text content seminary section");
						}
					});
		}
	};

	private ClickHandler addImageHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			imageUploadFactory
					.get(new AsyncCallback<ImageUploadPresenter>() {
						@Override
						public void onSuccess(ImageUploadPresenter result) {
							addToSlot(SLOT_content, result);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Error while creating a text content seminary section");
						}
					});
		}
	};
	
	private ClickHandler saveHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
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

	@Inject
	TextEditPresenter textEditPresenter;
	private IndirectProvider<TextEditPresenter> textEditFactory;
	
	@Inject
	ImageUploadPresenter imageUploadPresenter;
	private IndirectProvider<ImageUploadPresenter> imageUploadFactory;

	public static final Object SLOT_confirm = new Object();
	public static final Object SLOT_content = new Object();	

	@Inject
	public SeminaryEditPresenter(final EventBus eventBus, final MyView view,
			Provider<TextEditPresenter> textEditFactory, Provider<ImageUploadPresenter> imageUploadFactory,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
		
		this.textEditFactory = new StandardProvider<TextEditPresenter>(
				textEditFactory);
		
		this.imageUploadFactory = new StandardProvider<ImageUploadPresenter>(
				imageUploadFactory);
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
		setInSlot(SLOT_content, null);
		confirmPresenter.setSaveButtonHandler(saveHandler);
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
		List<AbstractDynamicSection> dynamicSectionList = new ArrayList<AbstractDynamicSection>();
		List<DynamicSectionTextContent> dynamicSectionTextContents = getTextContents();
		SeminaryContent seminaryContent= new SeminaryContent();
		seminaryContent.setDynamicSectionList(dynamicSectionList);
	}
	
	public List<DynamicSectionTextContent> getTextContents() {
		HTMLPanel contentPanel = getView().getContentPanel();
		HTMLPanel rootPanel;
		Grid grid;
		RichTextArea richTextArea;
		TextBox textBox;
		
		List<DynamicSectionTextContent> textContents = new ArrayList<DynamicSectionTextContent>(); 
					
		for(int i=0; i<contentPanel.getWidgetCount();i++) {
			DynamicSectionTextContent dynamicSectionTextContent= new DynamicSectionTextContent();	
			rootPanel = (HTMLPanel)contentPanel.getWidget(i);
			for(int j=0; j<rootPanel.getWidgetCount(); j++) {			
				if(rootPanel.getWidget(j) instanceof HorizontalPanel) {
					HorizontalPanel horizontalPanel = (HorizontalPanel)rootPanel.getWidget(j);
					for(int k=0; k < horizontalPanel.getWidgetCount(); k++) {
						if(horizontalPanel.getWidget(k) instanceof TextBox) {
							textBox = (TextBox)horizontalPanel.getWidget(k);
							dynamicSectionTextContent.setTitle(textBox.getText());
						}
					}
				}
				if(rootPanel.getWidget(j) instanceof Grid) {
					grid = (Grid)rootPanel.getWidget(j);
					richTextArea = (RichTextArea) grid.getWidget(1, 0);
					dynamicSectionTextContent.setText(richTextArea.getText());
				}
			}
			textContents.add(dynamicSectionTextContent);
		}
		return textContents;
	}
}
