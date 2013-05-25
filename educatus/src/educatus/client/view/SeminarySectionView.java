package educatus.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import educatus.client.presenter.SeminarySectionPresenter;

public class SeminarySectionView extends ViewImpl implements
		SeminarySectionPresenter.MyView
{
	public interface Binder extends UiBinder<Widget, SeminarySectionView>
	{
	}

	public interface ContentStyle extends CssResource
	{
		String textArea();
	}
	
	@UiField
	PushButton deleteBtn;
	@UiField
	ContentStyle style;
	@UiField
	DivElement contentAlignBlock;
	@UiField
	SimplePanel contentArea;

	private final Widget widget;
	private Widget content;

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

	@UiHandler("alignLeftBtn")
	void onAlignLeft(ClickEvent event)
	{
		contentAlignBlock.getStyle().setTextAlign(Style.TextAlign.LEFT);
		if(content instanceof TextArea)
			((TextArea)content).setAlignment(TextAlignment.LEFT);
	}

	@UiHandler("alignCenterBtn")
	void onAlignCenter(ClickEvent event)
	{
		contentAlignBlock.getStyle().setTextAlign(Style.TextAlign.CENTER);
		if(content instanceof TextArea)
			((TextArea)content).setAlignment(TextAlignment.CENTER);
	}

	@UiHandler("alignRightBtn")
	void onAlignRight(ClickEvent event)
	{
		contentAlignBlock.getStyle().setTextAlign(Style.TextAlign.RIGHT);
		if(content instanceof TextArea)
			((TextArea)content).setAlignment(TextAlignment.RIGHT);
	}

	public PushButton getDeleteBtn()
	{
		return deleteBtn;
	}

	public void createTextContent()
	{
		content = new TextArea();
		content.setStyleName(style.textArea());
		contentArea.add(content);	
	}
}
