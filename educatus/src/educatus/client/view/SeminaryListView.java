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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.NameTokens;
import educatus.client.presenter.SeminaryListPresenter;
import educatus.client.ui.dataGrids.HyperLinkCell;
import educatus.client.ui.dataGrids.ImageCell;
import educatus.client.ui.dataGrids.Seminary;
import educatus.resources.SeminaryDataGridCssRessource;
import educatus.resources.SimplePagerRessources;

public class SeminaryListView extends ViewImpl implements
SeminaryListPresenter.MyView {

	public final Widget widget;
	
	@UiField(provided=true) 
	CellTable<Seminary> dataGrid;	
	@UiField
	Button backButton;	
	@UiField(provided=true) 
	SimplePager pager;
	
	public interface Binder extends UiBinder<Widget, SeminaryListView> {
	}

	@Inject
	public SeminaryListView(final Binder binder) {
		CellTable.Resources SeminaryDataGridRessources = GWT.create(SeminaryDataGridCssRessource.class);
		dataGrid = new CellTable<Seminary>(12, SeminaryDataGridRessources);
		dataGrid.addStyleName("dataGridContainer");
		initializeColumns();
		initializePager();
		widget = binder.createAndBindUi(this);	
		backButton.setStyleName("backButton");
	}
	
	public Widget asWidget() {
		return widget;
	}

	public CellTable<Seminary> getDataGrid() {
		return dataGrid;
	}	
	
	public void initializeColumns() {		
		TextColumn<Seminary> IdColumn = new TextColumn<Seminary>() {
		    @Override
		    public String getValue(Seminary seminary) {
		    	if(seminary.getId() != -1) {
		    		return String.valueOf(seminary.getId());
		    	}
		    	else {
		    		return null;
		    	}
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
		dataGrid.setColumnWidth(DifficultyColumn, 120, Unit.PX);
		
		Column<Seminary, Hyperlink> DescriptionColumn = 
		    new Column<Seminary, Hyperlink>(new HyperLinkCell()) { 
		        @Override 
		        public Hyperlink getValue(Seminary seminary) {			    	  
		        	Hyperlink link = new Hyperlink(seminary.getDescription(), NameTokens.getViewSeminary() + ";id=" + seminary.getId());
		            link.setStyleName("cellTableHyperlink");
		            return link; 
		        }
			};
		dataGrid.addColumn(DescriptionColumn, "Description", "");
		dataGrid.setColumnWidth(DescriptionColumn, 400, Unit.PX);
	}
	
	public void initializePager() {
		Resources simplePagerRessources = GWT.create(SimplePagerRessources.class);
		pager = new SimplePager(TextLocation.CENTER, simplePagerRessources , false, 0, true) {
            private int pageSize = 12;

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public void previousPage() {
                if (getDisplay() != null) {
                    Range range = getDisplay().getVisibleRange();
                    setPageStart(range.getStart() - getPageSize());
                }
            }

            @Override
            public void setPageStart(int index) {
                if (getDisplay() != null) {
                    Range range = getDisplay().getVisibleRange();
                    int displayPageSize = getPageSize();
                    if (isRangeLimited() && getDisplay().isRowCountExact()) {
                        displayPageSize = Math.min(getPageSize(), getDisplay()
                                .getRowCount() - index);
                    }
                    index = Math.max(0, index);
                    if (index != range.getStart()) {
                        getDisplay().setVisibleRange(index, displayPageSize);
                    }
                }
            }

            @Override
            public void nextPage() {
                if (getDisplay() != null) {
                    Range range = getDisplay().getVisibleRange();
                    setPageStart(range.getStart() + getPageSize());
                }
            }
        };

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
}