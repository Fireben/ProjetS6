package educatus.client.presenter;

import java.util.List;

import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import educatus.client.ui.dataGrids.Seminary;

public class SeminaryListPresenter extends
		PresenterWidget<SeminaryListPresenter.MyView>
{
	public interface MyView extends View {
		DataGrid<Seminary> getDataGrid();
	}

	@Inject
	public SeminaryListPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReset() {
		super.onReset();
	}
	
	public void setData(List<Seminary> seminaries) {			
		DataGrid<Seminary> dataGrid = getView().getDataGrid();	
		ListDataProvider<Seminary> dataProvider = new ListDataProvider<Seminary>();
		dataProvider.addDataDisplay(dataGrid);
		dataProvider.setList(seminaries);		
	}
}
