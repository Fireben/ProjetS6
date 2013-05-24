package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminaryViewPresenter.MyView;

public class SeminaryViewView extends ViewImpl implements MyView {
	  
	interface SeminaryViewViewUiBinder extends UiBinder<Widget, SeminaryViewView> {
	  }

	  private static SeminaryViewViewUiBinder uiBinder = GWT.create(SeminaryViewViewUiBinder.class);

	  private final Widget widget;
	  
	  //@UiField Label seminaryTitle;
	  //@UiField Label writtenBy;
	  //@UiField Label modifyBy;
	  //@UiField Label author;
	  //@UiField Label modif;
	  //@UiField Label createdDate;
	  //@UiField Label modifiedDate;
	  //@UiField Label seminaryContent;


	//public Label getSeminaryTitle() {
	//	return seminaryTitle;
	//}

	//public Label getWrittenBy() {
	//	return writtenBy;
	//}

	/*public Label getModifyBy() {
		return modifyBy;
	}

	public Label getAuthor() {
		return author;
	}

	public Label getModif() {
		return modif;
	}

	public Label getCreatedDate() {
		return createdDate;
	}

	public Label getModifiedDate() {
		return modifiedDate;
	}

	public Label getSeminaryContent() {
		return seminaryContent;
	}*/

	public SeminaryViewView() {
	    widget = uiBinder.createAndBindUi(this);
	  }

	  public Widget asWidget() {
	    return widget;
	  }
}