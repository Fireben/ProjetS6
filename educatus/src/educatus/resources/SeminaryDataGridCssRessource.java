package educatus.resources;
import com.google.gwt.user.cellview.client.CellTable;

public interface SeminaryDataGridCssRessource extends CellTable.Resources {
@Source({CellTable.Style.DEFAULT_CSS, "seminaryDataGrid.css"})
CellTableStyle cellTableStyle();
 
interface CellTableStyle extends CellTable.Style {}
}