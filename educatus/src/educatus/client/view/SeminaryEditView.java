package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminaryEditPresenter;

public class SeminaryEditView extends ViewImpl implements
		SeminaryEditPresenter.MyView {

	@UiField
	HTMLPanel confirmPanel;
	@UiField
	HTMLPanel contentPanel;
	@UiField
	Label semTitleLabel;
	@UiField
	FlowPanel seminaryDescriptionContainer;

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, SeminaryEditView> {

	}

	@Inject
	public SeminaryEditView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == SeminaryEditPresenter.SLOT_confirm) {
			confirmPanel.clear();
			if (content != null)
				confirmPanel.add(content);

		} else if (slot == SeminaryEditPresenter.SLOT_content) {
			contentPanel.clear();
			if (content != null)
				contentPanel.add(content);
		} else
			super.setInSlot(slot, content);
	}

	@Override
	public void addToSlot(Object slot, Widget content) {
		if (slot == SeminaryEditPresenter.SLOT_content) {
			if (content != null)
				contentPanel.add(content);
		} else
			super.addToSlot(slot, content);
	}

	@Override
	public void removeFromSlot(Object slot, Widget content) {
		if (content == null)
			return;

		if (slot == SeminaryEditPresenter.SLOT_content) {
			contentPanel.remove(content);
		} else
			super.removeFromSlot(slot, content);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public FlowPanel getSeminaryDescriptionContainer() {
		return seminaryDescriptionContainer;
	}	
}