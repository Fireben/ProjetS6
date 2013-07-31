package educatus.client.ui.widget;

import com.google.gwt.event.logical.shared.InitializeEvent;
import com.google.gwt.event.logical.shared.InitializeHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;

import educatus.client.ui.RichTextToolbar;

public class TextEdit extends EditSection{
	private RichTextArea richTextArea;
	private RichTextToolbar richTextToolbar;
	private Grid grid;
	private HorizontalPanel titlePanel;
	private TextBox titleBox;
	
	public TextEdit() {
		super();		
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
		titleLabel.setText("Text");
		createTitle();
		createGrid();
		panel.add(titlePanel);
		panel.add(grid);		
	}	
	
	private void createGrid() {
		richTextArea = new RichTextArea();
		richTextArea.addStyleName("textArea");
		richTextArea.addInitializeHandler(new InitializeHandler() {
	    	public void onInitialize(InitializeEvent event) {
	    		richTextArea.getFormatter().setFontName("calibri");
	        }
	    });
		richTextToolbar = new RichTextToolbar(richTextArea);
		richTextToolbar.addStyleName("textToolbar");
		
		grid = new Grid(2,1);
		grid.setWidget(0, 0, richTextToolbar);
		grid.setWidget(1, 0, richTextArea);
		grid.setStyleName("textAreaGrid");
	}
	
	public String getText() {
		return richTextArea.getHTML();
	}
	
	public String getTitle() {
		return titleBox.getText();
	}
}
