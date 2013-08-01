package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import educatus.client.NameTokens;
import educatus.client.ui.dataGrids.HyperLinkCell;
import educatus.client.ui.dataGrids.ImageCell;
import educatus.client.ui.dataGrids.ListContent;
import educatus.resources.SeminaryDataGridCssRessource;

public class ProfilNewsfeed extends Composite {
	
	private static ProfilNewsfeedUiBinder uiBinder = GWT.create(ProfilNewsfeedUiBinder.class);

	@UiField
	VerticalPanel newsfeedVerticalPanel;
	@UiField
	Label newsfeedTitleLabel;
	@UiField(provided=true) 
	CellTable<ListContent> newsfeedTable;
	
	interface ProfilNewsfeedUiBinder extends UiBinder<Widget, ProfilNewsfeed> {
	}

	public ProfilNewsfeed() {
		
		CellTable.Resources SeminaryDataGridRessources = GWT.create(SeminaryDataGridCssRessource.class);
		newsfeedTable = new CellTable<ListContent>(12, SeminaryDataGridRessources);
		newsfeedTable.addStyleName("newsfeedTable");
		initializeColumns();
		
		FlowPanel emptyPanel = new FlowPanel();
		Label emptyLabel = new Label("List is empty !");
		emptyLabel.setStyleName("emptyListLabel");
		Image image = new Image("images/Sheep.png");
		image.setStyleName("sheep");
		emptyPanel.add(image);
		emptyPanel.add(emptyLabel);

		newsfeedTable.setEmptyTableWidget(emptyPanel);
		
		initWidget(uiBinder.createAndBindUi(this));
		newsfeedTitleLabel.setStyleName("newsfeedTitleLabel");
		newsfeedVerticalPanel.setStyleName("newsfeedVerticalPanel");
	}
	

	public CellTable<ListContent> getDataGrid() {
		return newsfeedTable;
	}	
	
	private void initializeColumns() {		
		Column<ListContent, Hyperlink> IdColumn = 
			    new Column<ListContent, Hyperlink>(new HyperLinkCell()) { 
			        @Override 
			        public Hyperlink getValue(ListContent seminary) {			    	  
			        	Hyperlink link = new Hyperlink(String.valueOf(seminary.getId()), NameTokens.getViewSeminary() + ";id=" + seminary.getId());
			            link.setStyleName("cellTableHyperlink");
			            return link; 
			        }
				};
		newsfeedTable.addColumn(IdColumn, "Id", "");
		newsfeedTable.setColumnWidth(IdColumn, 100, Unit.PX);
		
		TextColumn<ListContent> NameColumn = new TextColumn<ListContent>() {
		    @Override
		    public String getValue(ListContent seminary) {
		      return seminary.getName();
		    }
		  };
		newsfeedTable.addColumn(NameColumn, "Name", "");
		newsfeedTable.setColumnWidth(NameColumn, 200, Unit.PX);
		
		TextColumn<ListContent> AuthorColumn = new TextColumn<ListContent>() {
		    @Override
		    public String getValue(ListContent seminary) {
		      return seminary.getAuthor();
		    }
		  };
		newsfeedTable.addColumn(AuthorColumn, "Author", "");
		newsfeedTable.setColumnWidth(AuthorColumn, 200, Unit.PX);
		
		Column<ListContent, Integer> DifficultyColumn = 
			    new Column<ListContent, Integer>(new ImageCell()) { 
			        @Override 
			        public Integer getValue(ListContent seminary) { 
			        	return seminary.getDifficulty();
			        }
				};
		newsfeedTable.addColumn(DifficultyColumn, "Difficulty", "");
		newsfeedTable.setColumnWidth(DifficultyColumn, 120, Unit.PX);
		
		Column<ListContent, Hyperlink> DescriptionColumn = 
		    new Column<ListContent, Hyperlink>(new HyperLinkCell()) { 
		        @Override 
		        public Hyperlink getValue(ListContent seminary) {			    	  
		        	Hyperlink link = new Hyperlink(seminary.getDescription(), NameTokens.getViewSeminary() + ";id=" + seminary.getId());
		            link.setStyleName("cellTableHyperlink");
		            return link; 
		        }
			};
		newsfeedTable.addColumn(DescriptionColumn, "Description", "");
		newsfeedTable.setColumnWidth(DescriptionColumn, 400, Unit.PX);
	}
}
