package educatus.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.EducatusLocale;
import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;
import educatus.client.ui.CategoryInformation;
import educatus.client.ui.CategoryList;
import educatus.client.ui.dataGrids.Category;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.request.CategoryAdministrationPageContentRequest;

public class CategoryAdministrationPresenter extends Presenter<CategoryAdministrationPresenter.MyView, CategoryAdministrationPresenter.MyProxy> {

	HandlerRegistration DeletedCategoryHandler = null;

	public interface MyView extends View {

		public VerticalPanel getCategoryVerticalPanel();

		public HTMLPanel getHtmlPanel();
	}

	String action = null;
	String id = null;

	@ProxyCodeSplit
	@NameToken(NameTokens.categoryAdministration)
	public interface MyProxy extends ProxyPlace<CategoryAdministrationPresenter> {
	}

	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT.create(RequestService.class);

	@Inject
	private EducatusLocale locale;

	public static final Object SLOT_content = new Object();

	@Inject
	public CategoryAdministrationPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReset() {
		super.onReset();
		PageChangingEvent.fire(this, NameTokens.getCategoryAdministration());

		getView().getCategoryVerticalPanel().clear();

		if ("Edit".equals(action) && id != null) {

			CategoryInformation categoryInformation = new CategoryInformation();

			TextBox software = new TextBox();
			software.setText("Software");
			categoryInformation.setCategoryName(software);

			VerticalPanel verticalPanel = new VerticalPanel();
			verticalPanel.add(new RichTextArea());
			categoryInformation.setCategoryDescriptionVerticalPanel(verticalPanel);

			Image categoryImage = new Image("images/categories/software.png");
			categoryInformation.setCategoryImage(categoryImage);

			getView().getCategoryVerticalPanel().add(categoryInformation);
			action = null;
			id = null;
		}

		else if (action == "delete" && id != null) {
			// Supprimer la catégorie sélectionner
			action = null;
			id = null;
		}

		else if (action == null && id == null) {

			CategoryAdministrationPageContentRequest request = new CategoryAdministrationPageContentRequest();
			// request.setCulture(locale.getCulture());
			// request.setLanguage(locale.getLanguage());
			// requestService.sendRequest(request, new
			// AsyncCallback<AbstractResponse>() {

			// @Override
			// public void onSuccess(AbstractResponse result) {
			/*
			 * if (result.GetResponseType() ==
			 * ResponseTypeEnum.CATEGORY_ADMINISTRATION_CONTENT_RESPONSE) {
			 * CategoryAdministrationPageContentResponse response =
			 * (CategoryAdministrationPageContentResponse) result;
			 * 
			 * List<CategoryCoreContent> categoryCoreContentList =
			 * response.getCategoryCoreContentList();
			 * 
			 * getView().getCategoryVerticalPanel().clear();
			 * 
			 * List<Category> categories = new ArrayList<Category>();
			 * 
			 * for (CategoryCoreContent categoryCoreContent :
			 * categoryCoreContentList) { Category category = new Category(
			 * categoryCoreContent.getId(), categoryCoreContent.getName(),
			 * categoryCoreContent.getDescription(),
			 * categoryCoreContent.getImageUrl() ); categories.add(category); }
			 * CellTable<Category> dataGrid =
			 * getView().getCategoryList().getDataGrid();
			 * ListDataProvider<Category> dataProvider = new
			 * ListDataProvider<Category>();
			 * dataProvider.addDataDisplay(dataGrid);
			 * dataProvider.setList(categories); }
			 */

			Category category1 = new Category(1, "Hardware", "Seminars related to the hardware", "C:/Url/Path");
			Category category2 = new Category(2, "Software", "Seminars related to the software", "C:/Url/Path/Software");
			Category category3 = new Category(3, "Algorithm", "Seminars related to the algorithm", "C:/Url/Path/Algorithm");

			List<Category> categories = new ArrayList<Category>();
			categories.add(category1);
			categories.add(category2);
			categories.add(category3);

			CategoryList categoryList = new CategoryList();
			CellTable<Category> dataGrid = categoryList.getDataGrid();
			ListDataProvider<Category> dataProvider = new ListDataProvider<Category>();
			dataProvider.addDataDisplay(dataGrid);
			dataProvider.setList(categories);

			getView().getCategoryVerticalPanel().add(categoryList);
		}

		else {
			// ERROR not a expected token name
		}
		// }

		/*
		 * @Override public void onFailure(Throwable caught) { // TODO
		 * Auto-generated method
		 * 
		 * } });
		 */
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}

	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);
		action = placeRequest.getParameter("action", null);
		id = placeRequest.getParameter("id", null);
	}
}
