package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.CategoryAdministrationPresenter;
import educatus.client.ui.CategoryInformation;
import educatus.client.ui.CategoryList;

public class CategoryAdministrationView extends ViewImpl implements
		CategoryAdministrationPresenter.MyView {

	private final Widget widget;

	@UiField HTMLPanel categoryHtmlPanel;
	@UiField VerticalPanel categoryVerticalPanel;
	//@UiField CategoryList categoryList;
	//@UiField CategoryInformation categoryInformation;
	
	public interface Binder extends UiBinder<Widget, CategoryAdministrationView> {
	}

	@Inject
	public CategoryAdministrationView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	public HTMLPanel getHtmlPanel(){
		return categoryHtmlPanel;
	}
	
	public VerticalPanel getCategoryVerticalPanel() {
		return categoryVerticalPanel;
	}
	
	/*public CategoryList getCategoryList() {
		return categoryList;
	}
	
	public CategoryInformation getCategoryInformation() {
		return categoryInformation;
	}*/
}
