package educatus.resources;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;

public interface SimplePagerRessources extends SimplePager.Resources{
	@Override 
    @Source( "arrow_right.png" )
    public ImageResource simplePagerFastForward(); 
    
	@Override 
    @Source( "last.png" )
    public ImageResource simplePagerLastPage();
	
	@Override 
    @Source( "arrow_right.png" )
    public ImageResource simplePagerFastForwardDisabled();
	
	@Override 
    @Source( "arrow_right.png" )
    public ImageResource simplePagerLastPageDisabled();
	
	@Override 
    @Source( "first.png" )
    public ImageResource simplePagerFirstPage();
	
	@Override 
    @Source( "arrow_right.png" )
    public ImageResource simplePagerFirstPageDisabled();
	
	@Override 
    @Source( "arrow_left.png" )
    public ImageResource simplePagerPreviousPage();
	
	@Override 
    @Source( "arrow_right.png" )
    public ImageResource simplePagerPreviousPageDisabled();
	
	@Override 
    @Source( "arrow_right.png" )
    public ImageResource simplePagerNextPageDisabled();
	
	@Override 
    @Source( "arrow_right.png" )
    public ImageResource simplePagerNextPage();
	
	@Source({CellTable.Style.DEFAULT_CSS, "seminaryDataGrid.css"})
	SimplePagerStyle simplePagerStyle();	 
	interface SimplePagerStyle extends SimplePager.Style {}
}