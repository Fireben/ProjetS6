package educatus.client.presenter;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.NameTokens;

public class SeminaryViewPresenter extends
		Presenter<SeminaryViewPresenter.MyView, SeminaryViewPresenter.MyProxy> {

	public interface MyView extends View {
		//public Label getSeminaryTitle();
		//public Label getWrittenBy(); 
		//public Label getModifyBy(); 
		//public Label getAuthor();
		//public Label getModif(); 
		//public Label getCreatedDate(); 
		//public Label getModifiedDate();
		//public Label getSeminaryContent();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.viewSeminary)
	public interface MyProxy extends ProxyPlace<SeminaryViewPresenter> {
	}

	@Inject
	public SeminaryViewPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
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
		
		final Element seminaryTitle = Document.get().getElementById("seminaryTitle");
		seminaryTitle.setInnerHTML("Seminary Title");
		
		final Element writtenBy = Document.get().getElementById("writtenBy");
		writtenBy.setInnerHTML("written by");
		
		final Element author = Document.get().getElementById("author");
		author.setInnerHTML("Author");
		
		final Element authorEmail = Document.get().getElementById("authorEmail");
		authorEmail.setPropertyString("href", "Prof@usherbrooke.qc.ca");
		
		final Element createdDate = Document.get().getElementById("createdDate");
		createdDate.setInnerHTML("23/03/13");
		
		final Element modifyBy = Document.get().getElementById("modifyBy");
		modifyBy.setInnerHTML("modifed by");
		
		final Element modif = Document.get().getElementById("modif");
		modif.setInnerHTML("Modifier");
		
		final Element modifEmail = Document.get().getElementById("modifEmail");
		modifEmail.setPropertyString("href", "Modif@usherbrooke.qc.ca");
		
		final Element modifiedDate = Document.get().getElementById("modifiedDate");
		modifiedDate.setInnerHTML("25/03/13");
		
		final Element seminaryContent = Document.get().getElementById("seminaryContent");
		seminaryContent.setInnerHTML("Seminary Content");
		
		//getView().getSeminaryTitle().setText("Seminary Title");
		//getView().getWrittenBy().setText("written by");
		//getView().getAuthor().setText("Author");
		//getView().getCreatedDate().setText("23/03/13");
		//getView().getModifyBy().setText("modified by");
		//getView().getModif().setText("Modif");
		//getView().getModifiedDate().setText("25/03/13");
		//getView().getSeminaryContent().setText("Seminary Content");
	}
}
