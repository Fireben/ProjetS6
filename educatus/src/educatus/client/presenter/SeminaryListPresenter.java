package educatus.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import educatus.client.ui.dataGrids.Seminary;

public class SeminaryListPresenter extends
		PresenterWidget<SeminaryListPresenter.MyView>
{
	
	private final static int MIN_ELEMENTS = 12;
	
	public interface MyView extends View {
		CellTable<Seminary> getDataGrid();
		Button getBackButton();
		SimplePager getPager();
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
	
	@Override
	protected void onReveal() {
		super.onReveal();
		getView().getPager().setPage(0);
	}
	
	public void setBackButtonHandler(ClickHandler backClickHandler) {
		getView().getBackButton().addClickHandler(backClickHandler);
	}
	
	public void setData(List<Seminary> seminaries) {		
		/*if(seminaries.size() < MIN_ELEMENTS) {
			int nbEmptyRows= MIN_ELEMENTS - seminaries.size();
			for(int i=0; i < nbEmptyRows; i++) {
				seminaries.add(new Seminary(-1, null, null, null, null, -1));
			}
		}*/
		CellTable<Seminary> dataGrid = getView().getDataGrid();	
		ListDataProvider<Seminary> dataProvider = new ListDataProvider<Seminary>();
		dataProvider.addDataDisplay(dataGrid);
		dataProvider.setList(seminaries);	
	}
}
