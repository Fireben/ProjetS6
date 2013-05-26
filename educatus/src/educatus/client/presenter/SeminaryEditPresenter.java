package educatus.client.presenter;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import educatus.client.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class SeminaryEditPresenter extends
		Presenter<SeminaryEditPresenter.MyView, SeminaryEditPresenter.MyProxy>
{
	public interface MyView extends View
	{
		public TextBox getSemTitleBox();
		public Image getAddTextBoxImg();
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.seminaryEdit)	
	public interface MyProxy extends ProxyPlace<SeminaryEditPresenter>
	{
	}
	
	private ClickHandler saveHandler = new ClickHandler()
	{
		@Override
		public void onClick(ClickEvent event)
		{
			Window.alert(getView().getSemTitleBox().getText());
		}
	};
	
	private ClickHandler addTextBoxHandler = new ClickHandler()
	{
		@Override
		public void onClick(ClickEvent event)
		{
			setInSlot(SLOT_content, seminarySectionPresenter);
		}
	};
	
	@Inject
	ConfirmChangesPresenter confirmPresenter;
	
	@Inject
	SeminarySectionPresenter seminarySectionPresenter;
	
	public static final Object SLOT_confirm = new Object();
	public static final Object SLOT_content = new Object();
	
	@Inject
	public SeminaryEditPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy)
	{
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}

	@Override
	protected void onBind()
	{
		super.onBind();
		
	}

	@Override
	protected void onReset()
	{
		super.onReset();
		setInSlot(SLOT_confirm, confirmPresenter);
		confirmPresenter.getView().getSaveButton().addClickHandler(saveHandler);
		getView().getAddTextBoxImg().addClickHandler(addTextBoxHandler);
	}
}
