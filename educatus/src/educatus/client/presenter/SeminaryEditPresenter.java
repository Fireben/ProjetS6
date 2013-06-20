package educatus.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
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

public class SeminaryEditPresenter extends
		Presenter<SeminaryEditPresenter.MyView, SeminaryEditPresenter.MyProxy> {
	public interface MyView extends View {
		public FlowPanel getSeminaryDescriptionContainer();
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
	}
}
