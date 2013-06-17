package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.NameTokens;
import educatus.client.presenter.SeminaryListPresenter;
import educatus.client.ui.dataGrids.HyperLinkCell;
import educatus.client.ui.dataGrids.ImageCell;
import educatus.client.ui.dataGrids.Seminary;
import educatus.resources.SeminaryDataGridCssRessource;

public class SeminaryListView extends ViewImpl implements
SeminaryListPresenter.MyView {

	public final Widget widget;
	
	@UiField(provided=true) 
	DataGrid<Seminary> dataGrid;
	
	public interface Binder extends UiBinder<Widget, SeminaryListView> {
	}

	@Inject
	public SeminaryListView(final Binder binder) {
		DataGrid.Resources SeminaryDataGridRessources = GWT.create(SeminaryDataGridCssRessource.class);
		dataGrid = new DataGrid<Seminary>(50, SeminaryDataGridRessources);
		dataGrid.addStyleName("dataGridContainer");
		InitializeColumns();
		widget = binder.createAndBindUi(this);			
	}
	
	public Widget asWidget() {
		return widget;
	}

	public DataGrid<Seminary> getDataGrid() {
		return dataGrid;
	}	
	
	public void InitializeColumns() {		
		TextColumn<Seminary> IdColumn = new TextColumn<Seminary>() {
		    @Override
		    public String getValue(Seminary seminary) {
		      return String.valueOf(seminary.getId());
		    }
		  };
		dataGrid.addColumn(IdColumn, "Id", "");
		dataGrid.setColumnWidth(IdColumn, 100, Unit.PX);
		
		TextColumn<Seminary> NameColumn = new TextColumn<Seminary>() {
		    @Override
		    public String getValue(Seminary seminary) {
		      return seminary.getName();
		    }
		  };
		dataGrid.addColumn(NameColumn, "Name", "");
		dataGrid.setColumnWidth(NameColumn, 200, Unit.PX);
		
		TextColumn<Seminary> AuthorColumn = new TextColumn<Seminary>() {
		    @Override
		    public String getValue(Seminary seminary) {
		      return seminary.getAuthor();
		    }
		  };
		dataGrid.addColumn(AuthorColumn, "Author", "");
		dataGrid.setColumnWidth(AuthorColumn, 200, Unit.PX);
		
		Column<Seminary, Integer> DifficultyColumn = 
			    new Column<Seminary, Integer>(new ImageCell()) { 
			        @Override 
			        public Integer getValue(Seminary seminary) { 
			        	return seminary.getDifficulty();
			        }
				};
		dataGrid.addColumn(DifficultyColumn, "Difficulty", "");
		dataGrid.setColumnWidth(DifficultyColumn, 150, Unit.PX);
		
		Column<Seminary, Hyperlink> DescriptionColumn = 
		    new Column<Seminary, Hyperlink>(new HyperLinkCell()) { 
		        @Override 
		        public Hyperlink getValue(Seminary seminary) {			    	  
		        	Hyperlink link = new Hyperlink(seminary.getDescription(), NameTokens.getViewSeminary() + "?id=" + String.valueOf(5));
		            link.setStyleName("dataGridHyperlink");
		            return link; 
		        }
			};
		dataGrid.addColumn(DescriptionColumn, "Description", "");
		dataGrid.setColumnWidth(DescriptionColumn, 400, Unit.PX);
	}
}