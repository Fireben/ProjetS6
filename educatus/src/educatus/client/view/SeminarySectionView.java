package educatus.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import educatus.client.presenter.SeminarySectionPresenter;

public class SeminarySectionView extends ViewImpl implements
		SeminarySectionPresenter.MyView
{

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, SeminarySectionView>
	{
	}

	@Inject
	public SeminarySectionView(final Binder binder)
	{
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget()
	{
		return widget;
	}
}
