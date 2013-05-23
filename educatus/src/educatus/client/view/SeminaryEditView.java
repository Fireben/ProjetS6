package educatus.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import educatus.client.presenter.SeminaryEditPresenter;

public class SeminaryEditView extends ViewImpl implements
		SeminaryEditPresenter.MyView
{

	@UiField
	HTMLPanel confirmPanel;
	@UiField
	LabelElement semTitleLabel;

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

}