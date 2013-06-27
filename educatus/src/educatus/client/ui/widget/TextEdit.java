package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;

import educatus.client.ui.RichTextToolbar;

public class TextEdit extends Composite{
	private RichTextArea richTextArea;
	private RichTextToolbar richTextToolbar;
	private FlowPanel panel;
	private Grid grid;
	private HorizontalPanel titlePanel;
	private TextBox titleBox;
	
	public TextEdit() {
		super();
		panel = new FlowPanel();
		initWidget(panel);
		
		createPanel();
	}
	
	private void createTitle() {
		titlePanel = new HorizontalPanel();
		titlePanel.add(new Label("Title : "));
		titleBox = new TextBox();
		titleBox.setStyleName("editTitleTextBox");
		titlePanel.add(titleBox);		
		titlePanel.setStyleName("editTitle");
	}
	
	private void createPanel() {
		createTitle();
		createGrid();
		panel.add(titlePanel);
		panel.add(grid);		
		panel.setStyleName("editSection");
	}	
	
	private void createGrid() {
		richTextArea = new RichTextArea();
		richTextArea.addStyleName("textArea");
		
		richTextToolbar = new RichTextToolbar(richTextArea);
		richTextToolbar.addStyleName("textToolbar");
		
		grid = new Grid(2,1);
		grid.setWidget(0, 0, richTextToolbar);
		grid.setWidget(1, 0, richTextArea);
		grid.setStyleName("textAreaGrid");
	}
	
	public String getText() {
		return richTextArea.getText();
	}
	
	public String getTitle() {
		return titleBox.getText();
	}
}
