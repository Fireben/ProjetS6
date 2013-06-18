package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.TextEditPresenter;
import educatus.client.ui.RichTextToolbar;

public class TextEditView extends ViewImpl implements TextEditPresenter.MyView {
	public interface Binder extends UiBinder<Widget, TextEditView> {
	}

	@UiField
	PushButton deleteBtn;
	
	@UiField(provided=true)
	Grid textAreaGrid;
	

	private final Widget widget;

	@Inject
	public TextEditView(final Binder binder) {
		textAreaGrid = new Grid(2,1);
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public PushButton getDeleteBtn() {
		return deleteBtn;
	}

	public void createTextContent() {
		deleteBtn.setVisible(false);
		RichTextArea textArea = new RichTextArea();
		textArea.ensureDebugId("cwRichText-area");
		textArea.addStyleName("textArea");
		
		RichTextToolbar textToolbar = new RichTextToolbar(textArea);
		textToolbar.ensureDebugId("cwRichText-toolbar");
		textToolbar.setStyleName("textToolbar");
		
	    textAreaGrid.setWidget(0, 0, textToolbar);
	    textAreaGrid.setWidget(1, 0, textArea);
	}
}
