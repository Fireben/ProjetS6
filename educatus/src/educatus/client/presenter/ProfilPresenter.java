package educatus.client.presenter;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;


public class ProfilPresenter extends
		Presenter<ProfilPresenter.MyView, ProfilPresenter.MyProxy> {

	public interface MyView extends View {
		public Image getUserImage();
		public FlowPanel getChartContainer();
		public void insertChart(Widget w);
		public void removeChart(Widget w);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.profil)
	public interface MyProxy extends ProxyPlace<ProfilPresenter> {
	}
	
	@Inject
	public ProfilPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
	    RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent,
	            this);
	}
	
	@Override
	protected void onBind() {
		super.onBind();
	}
	
	private PieChart pie;
	@Override
	protected void onReset() {
		super.onReset();
		PageChangingEvent.fire(this, NameTokens.getProfil());
		
		if(getView().getChartContainer().getWidgetCount() == 0) {
			Runnable onLoadCallback = new Runnable() {
			      public void run() {
			          pie = new PieChart(createTable(), createOptions());
			          pie.addSelectHandler(createSelectHandler(pie));
			          getView().insertChart(pie);
			      }
			    };
				// Load the visualization api, passing the onLoadCallback to be called
			    // when loading is done.
			VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
		} else {
			getView().removeChart(pie);
			Runnable onLoadCallback = new Runnable() {
			      public void run() {
			          pie = new PieChart(createTable(), createOptions());
			          pie.addSelectHandler(createSelectHandler(pie));
			          getView().insertChart(pie);
			      }
			    };
				// Load the visualization api, passing the onLoadCallback to be called
			    // when loading is done.
			VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
		}
		
		//getView().removeChart(pie);
	}
	
	private Options createOptions() {
        Options options = Options.create();
        options.setWidth(400);
        options.setHeight(240);
        options.set3D(true);
        options.setTitle("My Daily Activities");
        return options;
      }

      private SelectHandler createSelectHandler(final PieChart chart) {
        return new SelectHandler() {
          @Override
          public void onSelect(SelectEvent event) {
            String message = "";
            
            // May be multiple selections.
            JsArray<Selection> selections = chart.getSelections();

            for (int i = 0; i < selections.length(); i++) {
              // add a new line for each selection
              message += i == 0 ? "" : "\n";
              
              Selection selection = selections.get(i);

              if (selection.isCell()) {
                // isCell() returns true if a cell has been selected.
                
                // getRow() returns the row number of the selected cell.
                int row = selection.getRow();
                // getColumn() returns the column number of the selected cell.
                int column = selection.getColumn();
                message += "cell " + row + ":" + column + " selected";
              } else if (selection.isRow()) {
                // isRow() returns true if an entire row has been selected.
                
                // getRow() returns the row number of the selected row.
                int row = selection.getRow();
                message += "row " + row + " selected";
              } else {
                // unreachable
                message += "Pie chart selections should be either row selections or cell selections.";
                message += "  Other visualizations support column selections as well.";
              }
            }
            
//            Window.alert(message);
          }
        };
      }

      private AbstractDataTable createTable() {
        DataTable data = DataTable.create();
        data.addColumn(ColumnType.STRING, "Task");
        data.addColumn(ColumnType.NUMBER, "Hours per Day");
        data.addRows(2);
        data.setValue(0, 0, "Work");
        data.setValue(0, 1, 14);
        data.setValue(1, 0, "Sleep");
        data.setValue(1, 1, 10);
        return data;
      }
}
