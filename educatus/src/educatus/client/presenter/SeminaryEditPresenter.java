package educatus.client.presenter;

import com.gwtplatform.common.client.IndirectProvider;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;

import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class SeminaryEditPresenter extends
		Presenter<SeminaryEditPresenter.MyView, SeminaryEditPresenter.MyProxy>
{
	public interface MyView extends View
	{
		public TextBox getSemTitleBox();

		public Image getAddTextBoxImg();
		
		public Image getAddImageImg();
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
	
	private class DeleteClickHandler implements ClickHandler{
		private SeminarySectionPresenter presenterWidget;
		
		public DeleteClickHandler(SeminarySectionPresenter presenterWidget)
		{
			this.presenterWidget = presenterWidget;
		}
		
		@Override
		public void onClick(ClickEvent event)
		{
			removeFromSlot(SLOT_content, this.presenterWidget);
		}
	}

	private ClickHandler addTextBoxHandler = new ClickHandler()
	{
		@Override
		public void onClick(ClickEvent event)
		{
			seminarySectionFactory.get(new AsyncCallback<SeminarySectionPresenter>()
			{
				@Override
				public void onSuccess(SeminarySectionPresenter result)
				{
					addToSlot(SLOT_content, result);
					result.getView().createTextContent();
					result.getView().getDeleteBtn().addClickHandler(new DeleteClickHandler(result));
				}
				
				@Override
				public void onFailure(Throwable caught)
				{
					Window.alert("Error while creating a text content seminary section");
				}
			});
		}
	};
	
	private ClickHandler addImageHandler = new ClickHandler()
	{
		@Override
		public void onClick(ClickEvent event)
		{
			seminarySectionFactory.get(new AsyncCallback<SeminarySectionPresenter>()
			{
				@Override
				public void onSuccess(SeminarySectionPresenter result)
				{
					addToSlot(SLOT_content, result);
					result.getView().createTextContent();
					result.getView().getDeleteBtn().addClickHandler(new DeleteClickHandler(result));
			        
					//TODO Image navigator
				}
				
				@Override
				public void onFailure(Throwable caught)
				{
					Window.alert("Error while creating a text content seminary section");
					
				}
			});		
		}
	};

	@Inject
	ConfirmChangesPresenter confirmPresenter;

	public static final Object SLOT_confirm = new Object();
	public static final Object SLOT_content = new Object();
	
	private IndirectProvider<SeminarySectionPresenter> seminarySectionFactory;

	@Inject
	public SeminaryEditPresenter(final EventBus eventBus, final MyView view, Provider<SeminarySectionPresenter> seminarySectionFactory,
			final MyProxy proxy)
	{
		super(eventBus, view, proxy);
		this.seminarySectionFactory = new StandardProvider<SeminarySectionPresenter>(seminarySectionFactory);
	}

	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent,
				this);
	}

	@Override
	protected void onBind()
	{
		super.onBind();
		
		getView().getAddTextBoxImg().addClickHandler(addTextBoxHandler);
		getView().getAddImageImg().addClickHandler(addImageHandler);
	}

	@Override
	protected void onReset()
	{
		super.onReset();
		PageChangingEvent.fire(this, NameTokens.getSeminaryEdit());
		
		setInSlot(SLOT_confirm, confirmPresenter);
		setInSlot(SLOT_content, null);
		
		confirmPresenter.getView().getSaveButton().addClickHandler(saveHandler);
		
	}
}
