package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import educatus.client.presenter.SeminaryListPresenter;
import educatus.client.ui.dataGrids.Seminary;

public class SeminaryListView extends ViewImpl implements
SeminaryListPresenter.MyView {

	public final Widget widget;
	
	@UiField DataGrid<Seminary> dataGrid;
	
	public interface Binder extends UiBinder<Widget, SeminaryListView> {
	}

	@Inject
	public SeminaryListView(final Binder binder) {		
	  widget = binder.createAndBindUi(this);	  
	}
	
	public Widget asWidget() {
		return widget;
	}

	public DataGrid<Seminary> getDataGrid() {
		return dataGrid;
	}	
}