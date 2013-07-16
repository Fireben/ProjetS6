package educatus.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.CookiesConst;
import educatus.client.EducatusLocale;
import educatus.client.NameTokens;
import educatus.client.ui.ProfilNewsfeed;
import educatus.client.ui.ProfilSummary;
import educatus.client.ui.dataGrids.Seminary;
import educatus.client.ui.widget.DescriptionEntry;
import educatus.shared.dto.seminary.SeminaryCoreContent;
import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.dto.user.UserProfilContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.UserContentRequest;
import educatus.shared.services.requestservice.response.UserContentResponse;

public class ProfilPresenter extends
		Presenter<ProfilPresenter.MyView, ProfilPresenter.MyProxy> {

	public interface MyView extends View {
		public VerticalPanel getProfilVerticalPanel();

		public ProfilSummary getProfilSummary();

		public ProfilNewsfeed getProfilNewsfeed();
	}
	
	@Inject
	private EducatusLocale locale;
	
	private String requestedUser = null;

	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT
			.create(RequestService.class);

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

	@Override
	protected void onReset() {
		super.onReset();
		
		String user = null;
		if (requestedUser != null) {
			user = requestedUser;
		} else {
			user = Cookies.getCookie(CookiesConst.CURRENT_USER);
		}
		
		UserContentRequest request = new UserContentRequest();
		request.setRequestedUser(user);
		request.setCulture(locale.getCulture());
		request.setLanguage(locale.getLanguage());
		request.setSessionID(Cookies.getCookie(CookiesConst.SESSION_ID));
		requestService.sendRequest(request, new AsyncCallback<AbstractResponse>() {

			@Override
			public void onSuccess(AbstractResponse result) {
				if (result.GetResponseType() == ResponseTypeEnum.USER_CONTENT_REQUEST) {
					UserContentResponse response = (UserContentResponse) result;

					UserProfilContent content = response.getUserProfilContent();
					UserCoreContent coreContent = content.getUserCoreContent();

					getView().getProfilSummary().getUserDescriptionVerticalPanel().clear();
					getView().getProfilSummary().getUserDescriptionVerticalPanel().add(new DescriptionEntry(
							"Fullname",
							coreContent.getFullName())
							);
					getView().getProfilSummary().getUserDescriptionVerticalPanel().add(new DescriptionEntry(
							"CIP",
							coreContent.getCip())
							);
					getView().getProfilSummary().getUserDescriptionVerticalPanel().add(new DescriptionEntry(
							"Email",
							coreContent.getEmail())
							);
					getView().getProfilSummary().getUserDescriptionVerticalPanel().add(new DescriptionEntry(
							"Unity",
							coreContent.getUnity())
							);
					getView().getProfilSummary().getUserDescriptionVerticalPanel().add(new DescriptionEntry(
							"Joined Date",
							coreContent.getJoinedDate())
							);
					getView().getProfilSummary().getUserDescriptionVerticalPanel().add(new DescriptionEntry(
							"Last Connexion",
							coreContent.getLastConnexion())
							);

					List<Seminary> seminaries = new ArrayList<Seminary>();
					List<SeminaryCoreContent> seminaryCoreContentList = content.getCompletedSeminaryList();

					for (SeminaryCoreContent seminaryCoreContent : seminaryCoreContentList) {
						Seminary seminary = new Seminary(
								seminaryCoreContent.getId(),
								seminaryCoreContent.getTitle(),
								seminaryCoreContent.getDescription(),
								seminaryCoreContent.getAuthor().getFirstName() + " " + seminaryCoreContent.getAuthor().getLastName(),
								seminaryCoreContent.getCreatedDate(),
								2
								);
						seminaries.add(seminary);
					}
					CellTable<Seminary> dataGrid = getView().getProfilNewsfeed().getDataGrid();
					ListDataProvider<Seminary> dataProvider = new ListDataProvider<Seminary>();
					dataProvider.addDataDisplay(dataGrid);
					dataProvider.setList(seminaries);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);
		requestedUser = placeRequest.getParameter("cip", null);
	}

	/*
	 * private PieChart pie;
	 * 
	 * @Override protected void onReset() { super.onReset();
	 * PageChangingEvent.fire(this, NameTokens.getProfil());
	 * 
	 * if(getView().getChartContainer().getWidgetCount() == 0) { Runnable
	 * onLoadCallback = new Runnable() { public void run() { pie = new
	 * PieChart(createTable(), createOptions());
	 * pie.addSelectHandler(createSelectHandler(pie));
	 * getView().insertChart(pie); } }; // Load the visualization api, passing
	 * the onLoadCallback to be called // when loading is done.
	 * VisualizationUtils.loadVisualizationApi(onLoadCallback,
	 * PieChart.PACKAGE); } }
	 * 
	 * private Options createOptions() { Options options = Options.create();
	 * options.setWidth(400); options.setHeight(240); // options.set3D(true);
	 * options.setTitle("My Daily Activities"); return options; }
	 * 
	 * private SelectHandler createSelectHandler(final PieChart chart) { return
	 * new SelectHandler() {
	 * 
	 * @Override public void onSelect(SelectEvent event) {
	 * 
	 * @SuppressWarnings("unused") String message = "";
	 * 
	 * // May be multiple selections. JsArray<Selection> selections =
	 * chart.getSelections();
	 * 
	 * for (int i = 0; i < selections.length(); i++) { // add a new line for
	 * each selection message += i == 0 ? "" : "\n";
	 * 
	 * Selection selection = selections.get(i);
	 * 
	 * if (selection.isCell()) { // isCell() returns true if a cell has been
	 * selected.
	 * 
	 * // getRow() returns the row number of the selected cell. int row =
	 * selection.getRow(); // getColumn() returns the column number of the
	 * selected cell. int column = selection.getColumn(); message += "cell " +
	 * row + ":" + column + " selected"; } else if (selection.isRow()) { //
	 * isRow() returns true if an entire row has been selected.
	 * 
	 * // getRow() returns the row number of the selected row. int row =
	 * selection.getRow(); message += "row " + row + " selected"; } else { //
	 * unreachable message +=
	 * "Pie chart selections should be either row selections or cell selections."
	 * ; message += "  Other visualizations support column selections as well.";
	 * } }
	 * 
	 * // Window.alert(message); } }; }
	 * 
	 * private AbstractDataTable createTable() { DataTable data =
	 * DataTable.create(); data.addColumn(ColumnType.STRING, "Task");
	 * data.addColumn(ColumnType.NUMBER, "Hours per Day"); data.addRows(2);
	 * data.setValue(0, 0, "Work"); data.setValue(0, 1, 14); data.setValue(1, 0,
	 * "Sleep"); data.setValue(1, 1, 10); return data; }
	 */
}
