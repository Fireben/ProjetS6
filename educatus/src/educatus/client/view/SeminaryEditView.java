package educatus.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import educatus.client.presenter.SeminaryEditPresenter;

public class SeminaryEditView extends ViewImpl implements
		SeminaryEditPresenter.MyView
{

	@UiField
	HTMLPanel confirmPanel;
	@UiField
	Label semTitleLabel;
	@UiField
	TextBox semTitleBox;

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, SeminaryEditView>
	{
		
	}

	@Inject
	public SeminaryEditView(final Binder binder)
	{
		widget = binder.createAndBindUi(this);
	}

	@Override
	public void setInSlot(Object slot, Widget content)
	{
		if (slot == SeminaryEditPresenter.SLOT_confirm)
		{
			confirmPanel.clear();
			if(content != null)
				confirmPanel.add(content);
			
		} else
			super.setInSlot(slot, content);
	}

	@Override
	public Widget asWidget()
	{
		return widget;
	}
	
	public TextBox getSemTitleBox()
	{
		return semTitleBox;
	}

}
