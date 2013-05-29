package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.ProfilPresenter;

public class ProfilView extends ViewImpl implements ProfilPresenter.MyView {
	
	private final Widget widget;
	
	@UiField Image userImage;
	@UiField FlowPanel chartContainer;
	
	public interface Binder extends UiBinder<Widget, ProfilView> {
	}
	
	@Inject
	public ProfilView(final Binder binder) {
	    widget = binder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget() {
		return widget;
	}
	
	//Getter and Setter

	public Image getUserImage() {
		return userImage;
	}

	public void setUserImage(Image userImage) {
		this.userImage = userImage;
	}
	
	public void insertChart(Widget w)
	{
		chartContainer.add(w);
	}
	
	public void removeChart(Widget w)
	{
		chartContainer.remove(w);
	}

	public FlowPanel getChartContainer() {
		return chartContainer;
	}

	public void setChartContainer(FlowPanel chartContainer) {
		this.chartContainer = chartContainer;
	}
}
