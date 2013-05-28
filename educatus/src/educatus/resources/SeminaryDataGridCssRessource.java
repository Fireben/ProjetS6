package educatus.resources;
import com.google.gwt.user.cellview.client.DataGrid;

public interface SeminaryDataGridCssRessource extends DataGrid.Resources {
@Source({DataGrid.Style.DEFAULT_CSS, "seminaryDataGrid.css"})
DataGridStyle dataGridStyle();
 
interface DataGridStyle extends DataGrid.Style {}
}