<<<<<<< HEAD
package educatus.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import educatus.client.presenter.SeminaryViewPresenter;
import educatus.client.presenter.SeminaryViewPresenter.MyView;

public class SeminaryViewView extends ViewImpl implements MyView {
	  
	interface SeminaryViewViewUiBinder extends UiBinder<Widget, SeminaryViewView> {
	  }

	  private static SeminaryViewViewUiBinder uiBinder = GWT.create(SeminaryViewViewUiBinder.class);

	  private final Widget widget;
	  
	  @UiField FlowPanel textAreaContainer;
	  @UiField Element seminaryTitle;
	  @UiField Element writtenBy;
	  @UiField Element modifyBy;
	  @UiField Element author;
	  @UiField Element authorEmail;
	  @UiField Element modif;
	  @UiField Element modifEmail;
	  @UiField Element createdDate;
	  @UiField Element modifiedDate;


	public Element getSeminaryTitle() {
		return seminaryTitle;
	}

	public Element getWrittenBy() {
		return writtenBy;
	}

	public Element getModifyBy() {
		return modifyBy;
	}

	public Element getAuthor() {
		return author;
	}
	
	public Element getAuthorEmail() {
		return authorEmail;
	}

	public Element getModif() {
		return modif;
	}
	
	public Element getModifEmail() {
		return modifEmail;
	}

	public Element getCreatedDate() {
		return createdDate;
	}

	public Element getModifiedDate() {
		return modifiedDate;
	}
	
	public void insertContent(Widget w)
	{
		textAreaContainer.add(w);
	}
	  
	public SeminaryViewView() {
	    widget = uiBinder.createAndBindUi(this);
	  }

	  public Widget asWidget() {
	    return widget;
	  }
	 
}
=======
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
>>>>>>> remotes/origin/Version_0_1
