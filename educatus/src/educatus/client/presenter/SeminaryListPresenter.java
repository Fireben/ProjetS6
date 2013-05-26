package educatus.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

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
	
	public void initializeColumns() {
		DataGrid<Seminary> dataGrid = getView().getDataGrid();
		
		List<Seminary> seminaries = new ArrayList<Seminary>();
		
		TextColumn<Seminary> IdColumn = new TextColumn<Seminary>() {
		    @Override
		    public String getValue(Seminary seminary) {
		      return String.valueOf(seminary.getId());
		    }
		  };
		dataGrid.addColumn(IdColumn, "Id");
		
		TextColumn<Seminary> NameColumn = new TextColumn<Seminary>() {
		    @Override
		    public String getValue(Seminary seminary) {
		      return String.valueOf(seminary.getName());
		    }
		  };
		dataGrid.addColumn(NameColumn, "Name");
		
		TextColumn<Seminary> DescriptionColumn = new TextColumn<Seminary>() {
		    @Override
		    public String getValue(Seminary seminary) {
		      return String.valueOf(seminary.getDescription());
		    }
		  };
		dataGrid.addColumn(DescriptionColumn, "Description");
		
		seminaries.add(new Seminary(0, "Sauce", "Comment faire de la sauce ?"));
		seminaries.add(new Seminary(1, "Sauce", "Comment faire de la sauce ?"));
		seminaries.add(new Seminary(2, "Sauce", "Comment faire de la sauce ?"));
		seminaries.add(new Seminary(3, "Sauce", "Comment faire de la sauce ?"));
		
		dataGrid.setRowCount(seminaries.size(), true);
		dataGrid.setRowData(seminaries);
	}
}
