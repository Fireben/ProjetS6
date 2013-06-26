package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.CategoryAdministrationPresenter;

public class CategoryAdministrationView extends ViewImpl implements
		CategoryAdministrationPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, CategoryAdministrationView> {
	}

	@Inject
	public CategoryAdministrationView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}
