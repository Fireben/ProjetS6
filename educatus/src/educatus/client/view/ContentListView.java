package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.Resources;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.NameTokens;
import educatus.client.presenter.ContentListPresenter;
import educatus.client.ui.dataGrids.HyperLinkCell;
import educatus.client.ui.dataGrids.ImageCell;
import educatus.client.ui.dataGrids.ListContent;
import educatus.client.ui.widget.Pager;
import educatus.resources.SeminaryDataGridCssRessource;
import educatus.resources.SimplePagerRessources;

public class ContentListView extends ViewImpl implements
ContentListPresenter.MyView {

	public final Widget widget;
	
	@UiField(provided=true) 
	CellTable<ListContent> dataGrid;	
	@UiField
	Button backButton;	
	@UiField(provided=true) 
	SimplePager pager;
	@UiField
	Label listTitle;
	
	public interface Binder extends UiBinder<Widget, ContentListView> {
	}

	@Inject
	public ContentListView(final Binder binder) {
		CellTable.Resources SeminaryDataGridRessources = GWT.create(SeminaryDataGridCssRessource.class);
		dataGrid = new CellTable<ListContent>(12, SeminaryDataGridRessources);
		dataGrid.addStyleName("dataGridContainer");
		initializeColumns();
		initializePager();
		widget = binder.createAndBindUi(this);	
		backButton.setStyleName("backButton");
		listTitle.setStyleName("listTitle");
	}
	
	public Widget asWidget() {
		return widget;
	}

	public CellTable<ListContent> getDataGrid() {
		return dataGrid;
	}	
	
	public void initializeColumns() {		
		Column<ListContent, Hyperlink> IdColumn = 
			    new Column<ListContent, Hyperlink>(new HyperLinkCell()) { 
			        @Override 
			        public Hyperlink getValue(ListContent listContent) {			    	  
			        	Hyperlink link = new Hyperlink(String.valueOf(listContent.getId()), listContent.getNameToken() + ";id=" + listContent.getId());
			            link.setStyleName("cellTableHyperlink");
			            return link; 
			        }
				};
		dataGrid.addColumn(IdColumn, "Id", "");
		dataGrid.setColumnWidth(IdColumn, 100, Unit.PX);
		
		TextColumn<ListContent> NameColumn = new TextColumn<ListContent>() {
		    @Override
		    public String getValue(ListContent listContent) {
		      return listContent.getName();
		    }
		  };
		dataGrid.addColumn(NameColumn, "Name", "");
		dataGrid.setColumnWidth(NameColumn, 200, Unit.PX);
		
		TextColumn<ListContent> AuthorColumn = new TextColumn<ListContent>() {
		    @Override
		    public String getValue(ListContent listContent) {
		      return listContent.getAuthor();
		    }
		  };
		dataGrid.addColumn(AuthorColumn, "Author", "");
		dataGrid.setColumnWidth(AuthorColumn, 200, Unit.PX);
		
		Column<ListContent, Integer> DifficultyColumn = 
			    new Column<ListContent, Integer>(new ImageCell()) { 
			        @Override 
			        public Integer getValue(ListContent listContent) { 
			        	return listContent.getDifficulty();
			        }
				};
		dataGrid.addColumn(DifficultyColumn, "Difficulty", "");
		dataGrid.setColumnWidth(DifficultyColumn, 120, Unit.PX);
		
		Column<ListContent, Hyperlink> DescriptionColumn = 
		    new Column<ListContent, Hyperlink>(new HyperLinkCell()) { 
		        @Override 
		        public Hyperlink getValue(ListContent listContent) {			    	  
		        	Hyperlink link = new Hyperlink(listContent.getDescription(), listContent.getNameToken() + ";id=" + listContent.getId());
		            link.setStyleName("cellTableHyperlink");
		            return link; 
		        }
			};
		dataGrid.addColumn(DescriptionColumn, "Description", "");
		dataGrid.setColumnWidth(DescriptionColumn, 400, Unit.PX);
	}
	
	public void initializePager() {
		Resources simplePagerRessources = GWT.create(SimplePagerRessources.class);
		pager = new Pager(TextLocation.CENTER, simplePagerRessources , false, 0, true);
        pager.setRangeLimited(true);
        pager.setDisplay(dataGrid);
        pager.setPageSize(12);
        pager.setStyleName("pager");
	}

	public Button getBackButton() {
		return backButton;
	}

	public SimplePager getPager() {
		return pager;
	}	
	
	public Label getTitle() {
		return listTitle;
	}
}