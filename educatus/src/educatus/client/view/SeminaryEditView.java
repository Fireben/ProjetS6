package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
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
	FlowPanel seminaryDescriptionContainer;
	
	TextBox titleBox = new TextBox();	
	TextArea descriptionBox = new TextArea();	
	ListBox difficultyBox = new ListBox();
	ListBox categoryBox = new ListBox();

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, SeminaryEditView> {

	}

	@Inject
	public SeminaryEditView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		seminaryDescriptionContainer.setStyleName("editCore");
		titleBox.setStyleName("editTitleBox");
		descriptionBox.setStyleName("editDescriptionBox");
		difficultyBox.setStyleName("editDifficultyBox");
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

	public TextBox getTitleBox() {
		return titleBox;
	}

	public TextArea getDescriptionBox() {
		return descriptionBox;
	}

	public ListBox getDifficultyBox() {
		return difficultyBox;
	}
	
	public ListBox getCategoryBox() {
		return categoryBox;
	}
}