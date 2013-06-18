package educatus.client.presenter;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;

public class SeminaryViewPresenter extends
		Presenter<SeminaryViewPresenter.MyView, SeminaryViewPresenter.MyProxy>{

	public interface MyView extends View {
		public Element getWrittenBy(); 
		public Element getModifyBy(); 
		public Element getAuthor();
		public Element getAuthorEmail();
		public Element getModif(); 
		public Element getModifEmail();
		public Element getCreatedDate(); 
		public Element getModifiedDate();
		public void insertContent(Widget w);
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
		
		Label label = new Label();
		label.setText("Label Text");
		getView().insertContent(label);
		
		Image img = new Image();
		img.setUrl("images/earth_puzzle_3.png");
		getView().insertContent(img);
		
		String src = "http://www.youtube.com/embed/Gm-RO-cmsEQ?list=PL29DDDC847F63AF82";
		HTML videoHtml = new HTML("<iframe width=\"560\" height=\"315\" src=" + src + " frameborder=\"0\" allowfullscreen></iframe>");
		getView().insertContent(videoHtml);
		
		String srcVideo = "http://www.dailymotion.com/embed/video/xzfw9p";
		HTML videoDailyMotion = new HTML("<iframe width=\"560\" height=\"315\" src=" + srcVideo + " frameborder=\"0\" allowfullscreen></iframe>");
		getView().insertContent(videoDailyMotion);
		
		/*if(type == "text")
		{
			Label label = new Label();
			label.setText(content);
			getView().insertContent(label);
		}
		else if(type == "image")
		{
			Image img = new Image();
			img.setUrl(url);
			getView().insertContent(img);
		}
		else if(type == "video")
		{
			HTML videoHtml = new HTML("<iframe width=\"560\" height=\"315\" src=" + videoSrc + " frameborder=\"0\" allowfullscreen></iframe>");
			getView().insertContent(videoHtml);
		}*/
	}

	@Override
	protected void onReset() {
		super.onReset();
		
		getView().getWrittenBy().setInnerHTML("written by");
		getView().getAuthor().setInnerHTML("Author");
		getView().getAuthorEmail().setPropertyString("href", "mailto:Prof@usherbrooke.qc.ca");
		getView().getCreatedDate().setInnerHTML("23/03/13");
		getView().getModifyBy().setInnerHTML("modified by");
		getView().getModif().setInnerHTML("Modif");
		getView().getModifEmail().setPropertyString("href", "mailto:Modif@usherbrooke.qc.ca");
		getView().getModifiedDate().setInnerHTML("25/03/13");		
		
	}
	
	@Override
	protected void onReveal() {
		PageChangingEvent.fire(this, NameTokens.getViewSeminary());
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest placeRequest) {
		super.prepareFromRequest(placeRequest);
		String id = placeRequest.getParameter("id", "1");
		System.out.println("Mon id est : " + id);
	}
}