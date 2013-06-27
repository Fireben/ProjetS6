package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminaryEditPresenter;

public class SeminaryEditView extends ViewImpl implements
		SeminaryEditPresenter.MyView {

	@UiField
	HTMLPanel confirmPanel;
	@UiField
	FlowPanel contentPanel;
	@UiField
	Label semTitleLabel;
	@UiField
	FlowPanel seminaryDescriptionContainer;
	
	@UiField 
	TextBox semTitleBox;	
	@UiField 
	TextArea semDescBox;	
	@UiField 
	ListBox semDiffBox;

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

		} else
			super.setInSlot(slot, content);
	}
	
	@Override
	public Widget asWidget() {
		return widget;
	}

	public FlowPanel getContentPanel() {
		return contentPanel;
	}

	public FlowPanel getSeminaryDescriptionContainer() {
		return seminaryDescriptionContainer;
	}

	public TextBox getSemTitleBox() {
		return semTitleBox;
	}

	public TextArea getSemDescBox() {
		return semDescBox;
	}

	public ListBox getSemDiffBox() {
		return semDiffBox;
	}	
}