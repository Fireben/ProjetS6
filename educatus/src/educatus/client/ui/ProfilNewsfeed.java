package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import educatus.client.NameTokens;
import educatus.client.ui.dataGrids.HyperLinkCell;
import educatus.client.ui.dataGrids.ImageCell;
import educatus.client.ui.dataGrids.Seminary;
import educatus.resources.SeminaryDataGridCssRessource;

public class ProfilNewsfeed extends Composite {
	
	private static ProfilNewsfeedUiBinder uiBinder = GWT.create(ProfilNewsfeedUiBinder.class);

	@UiField
	VerticalPanel newsfeedVerticalPanel;
	@UiField
	Label newsfeedTitleLabel;
	@UiField(provided=true) 
	CellTable<Seminary> newsfeedTable;
	
	interface ProfilNewsfeedUiBinder extends UiBinder<Widget, ProfilNewsfeed> {
	}

	public ProfilNewsfeed() {
		CellTable.Resources SeminaryDataGridRessources = GWT.create(SeminaryDataGridCssRessource.class);
		newsfeedTable = new CellTable<Seminary>(12, SeminaryDataGridRessources);
		newsfeedTable.addStyleName("newsfeedTable");
		initializeColumns();
		initWidget(uiBinder.createAndBindUi(this));
	}
	

	public CellTable<Seminary> getDataGrid() {
		return newsfeedTable;
	}	
	
	private void initializeColumns() {		
		Column<Seminary, Hyperlink> IdColumn = 
			    new Column<Seminary, Hyperlink>(new HyperLinkCell()) { 
			        @Override 
			        public Hyperlink getValue(Seminary seminary) {			    	  
			        	Hyperlink link = new Hyperlink(String.valueOf(seminary.getId()), NameTokens.getViewSeminary() + ";id=" + seminary.getId());
			            link.setStyleName("cellTableHyperlink");
			            return link; 
			        }
				};
		newsfeedTable.addColumn(IdColumn, "Id", "");
		newsfeedTable.setColumnWidth(IdColumn, 100, Unit.PX);
		
		TextColumn<Seminary> NameColumn = new TextColumn<Seminary>() {
		    @Override
		    public String getValue(Seminary seminary) {
		      return seminary.getName();
		    }
		  };
		newsfeedTable.addColumn(NameColumn, "Name", "");
		newsfeedTable.setColumnWidth(NameColumn, 200, Unit.PX);
		
		TextColumn<Seminary> AuthorColumn = new TextColumn<Seminary>() {
		    @Override
		    public String getValue(Seminary seminary) {
		      return seminary.getAuthor();
		    }
		  };
		newsfeedTable.addColumn(AuthorColumn, "Author", "");
		newsfeedTable.setColumnWidth(AuthorColumn, 200, Unit.PX);
		
		Column<Seminary, Integer> DifficultyColumn = 
			    new Column<Seminary, Integer>(new ImageCell()) { 
			        @Override 
			        public Integer getValue(Seminary seminary) { 
			        	return seminary.getDifficulty();
			        }
				};
		newsfeedTable.addColumn(DifficultyColumn, "Difficulty", "");
		newsfeedTable.setColumnWidth(DifficultyColumn, 120, Unit.PX);
		
		Column<Seminary, Hyperlink> DescriptionColumn = 
		    new Column<Seminary, Hyperlink>(new HyperLinkCell()) { 
		        @Override 
		        public Hyperlink getValue(Seminary seminary) {			    	  
		        	Hyperlink link = new Hyperlink(seminary.getDescription(), NameTokens.getViewSeminary() + ";id=" + seminary.getId());
		            link.setStyleName("cellTableHyperlink");
		            return link; 
		        }
			};
		newsfeedTable.addColumn(DescriptionColumn, "Description", "");
		newsfeedTable.setColumnWidth(DescriptionColumn, 400, Unit.PX);
	}
}
