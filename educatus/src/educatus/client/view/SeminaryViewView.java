package educatus.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import educatus.client.presenter.SeminaryViewPresenter.MyView;

public class SeminaryViewView extends ViewImpl implements MyView {

	interface SeminaryViewViewUiBinder extends
			UiBinder<Widget, SeminaryViewView> {
	}

	private static SeminaryViewViewUiBinder uiBinder = GWT
			.create(SeminaryViewViewUiBinder.class);

	private final Widget widget;

	@UiField
	FlowPanel dynamicSectionContainer;
	@UiField
	Element writtenBy;
	@UiField
	Element modifyBy;
	@UiField
	Element author;
	@UiField
	Element authorEmail;
	@UiField
	Element modif;
	@UiField
	Element modifEmail;
	@UiField
	Element createdDate;
	@UiField
	Element modifiedDate;

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

	public FlowPanel getDynamicSectionContainer() {
		return dynamicSectionContainer;
	}

	public SeminaryViewView() {
		widget = uiBinder.createAndBindUi(this);
	}

	public Widget asWidget() {
		return widget;
	}

}
