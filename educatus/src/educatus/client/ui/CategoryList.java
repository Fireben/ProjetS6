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
import educatus.client.ui.dataGrids.Category;
import educatus.client.ui.dataGrids.HyperLinkCell;
import educatus.client.ui.dataGrids.ImageCell;
import educatus.resources.SeminaryDataGridCssRessource;

public class CategoryList extends Composite{
	
	private static CategoryListUiBinder uiBinder = GWT.create(CategoryListUiBinder.class);

	@UiField
	VerticalPanel categoryVerticalPanel;
	@UiField
	Label categoryLabel;
	@UiField(provided=true) 
	CellTable<Category> categoryTable;
	
	interface CategoryListUiBinder extends UiBinder<Widget, CategoryList> {
	}

	public CategoryList() {
		CellTable.Resources CategoryDataGridRessources = GWT.create(SeminaryDataGridCssRessource.class);
		categoryTable = new CellTable<Category>(12, CategoryDataGridRessources);
		categoryTable.addStyleName("categoryTable");
		initializeColumns();
		initWidget(uiBinder.createAndBindUi(this));
	}
	

	public CellTable<Category> getDataGrid() {
		return categoryTable;
	}	
	
	private void initializeColumns() {		
		Column<Category, Hyperlink> IdColumn = 
			    new Column<Category, Hyperlink>(new HyperLinkCell()) { 
			        @Override 
			        public Hyperlink getValue(Category category) {			    	  
			        	Hyperlink link = new Hyperlink(String.valueOf(category.getId()), NameTokens.getCategoryAdministration() + ";id=" + category.getId());
			            link.setStyleName("cellTableHyperlink");
			            return link; 
			        }
				};
		categoryTable.addColumn(IdColumn, "Id", "");
		categoryTable.setColumnWidth(IdColumn, 100, Unit.PX);
		
		TextColumn<Category> NameColumn = new TextColumn<Category>() {
		    @Override
		    public String getValue(Category category) {
		      return category.getName();
		    }
		  };
		categoryTable.addColumn(NameColumn, "Name", "");
		categoryTable.setColumnWidth(NameColumn, 200, Unit.PX);
		
		TextColumn<Category > ImageColumn = new TextColumn<Category>() { 
			        @Override 
			        public String getValue(Category category) { 
			        	return category.getImageUrl();
			        }
				};
		categoryTable.addColumn(ImageColumn, "Image", "");
		categoryTable.setColumnWidth(ImageColumn, 200, Unit.PX);
		
		Column<Category, Hyperlink> DescriptionColumn = 
		    new Column<Category, Hyperlink>(new HyperLinkCell()) { 
		        @Override 
		        public Hyperlink getValue(Category category) {			    	  
		        	Hyperlink link = new Hyperlink(category.getDescription(), NameTokens.getCategoryAdministration() + ";id=" + category.getId());
		            link.setStyleName("cellTableHyperlink");
		            return link; 
		        }
			};
		categoryTable.addColumn(DescriptionColumn, "Description", "");
		categoryTable.setColumnWidth(DescriptionColumn, 400, Unit.PX);
		
		Column<Category, Hyperlink> EditColumn = 
			    new Column<Category, Hyperlink>(new HyperLinkCell()) { 
			        @Override 
			        public Hyperlink getValue(Category category) {			    	  
			        	Hyperlink link = new Hyperlink("Edit", NameTokens.getCategoryAdministration() + ";action=Edit;id=" + category.getId());
			            link.setStyleName("cellTableHyperlink");
			            return link; 
			        }
				};
				
		categoryTable.addColumn(EditColumn, "", "");
		categoryTable.setColumnWidth(EditColumn,50, Unit.PX);
		
		Column<Category, Hyperlink> DeleteColumn = 
			    new Column<Category, Hyperlink>(new HyperLinkCell()) { 
			        @Override 
			        public Hyperlink getValue(Category category) {			    	  
			        	Hyperlink link = new Hyperlink("Delete", NameTokens.getCategoryAdministration() + ";action=Delete;id=" + category.getId());
			            link.setStyleName("cellTableHyperlink");
			            return link; 
			        }
				};
				
		categoryTable.addColumn(DeleteColumn, "", "");
		categoryTable.setColumnWidth(EditColumn,50, Unit.PX);
	}
}
