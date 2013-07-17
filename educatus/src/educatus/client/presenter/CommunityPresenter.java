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
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.CookiesConst;
import educatus.client.EducatusLocale;
import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;
import educatus.client.ui.CommunityUsersFeed;
import educatus.client.ui.dataGrids.User;
import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.UserListingRequest;
import educatus.shared.services.requestservice.response.UserListingResponse;

public class CommunityPresenter extends
		Presenter<CommunityPresenter.MyView, CommunityPresenter.MyProxy> {

	public interface MyView extends View {
		public VerticalPanel getCommunityVerticalPanel();

		public CommunityUsersFeed getCommunityUsersFeed();
	}
	
	@Inject
	private EducatusLocale locale;
	
//	private String requestedUser = null;

	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT
			.create(RequestService.class);

	@ProxyCodeSplit
	@NameToken(NameTokens.community)
	public interface MyProxy extends ProxyPlace<CommunityPresenter> {
	}

	@Inject
	public CommunityPresenter(final EventBus eventBus, final MyView view,
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
		UserListingRequest usersRequest = new UserListingRequest();
		usersRequest.setCulture(locale.getCulture());
		usersRequest.setLanguage(locale.getLanguage());
		usersRequest.setSessionID(Cookies.getCookie(CookiesConst.SESSION_ID));
		requestService.sendRequest(usersRequest, new AsyncCallback<AbstractResponse>() {

			@Override
			public void onSuccess(AbstractResponse result) {
				if (result.GetResponseType() == ResponseTypeEnum.USER_LISTING_RESPONSE) {
					UserListingResponse response = (UserListingResponse) result;
					
					List<UserCoreContent> content = response.getUserCoreContentList();

					List<User> users = new ArrayList<User>();
					
					for (UserCoreContent userCoreContent : content) {
						User user = new User(
								userCoreContent.getCip(),
								userCoreContent.getFirstName(),
								userCoreContent.getLastName(),
								userCoreContent.getEmail(),
								userCoreContent.getPhone(),
								userCoreContent.getTitle(),
								userCoreContent.getUnity(),
								userCoreContent.getRoom(),
								userCoreContent.getFax(),
								userCoreContent.getStatus(),
								userCoreContent.getFullName(),
								userCoreContent.getJoinedDate(),
								userCoreContent.getLastConnexion()
								);
						users.add(user);
					}
					CellTable<User> dataGrid = getView().getCommunityUsersFeed().getDataGrid();
					ListDataProvider<User> dataProvider = new ListDataProvider<User>();
					dataProvider.addDataDisplay(dataGrid);
					dataProvider.setList(users);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		PageChangingEvent.fire(this, NameTokens.getCommunity());
	}
	
//	@Override
//	public void prepareFromRequest(PlaceRequest placeRequest) {
//		super.prepareFromRequest(placeRequest);
//		requestedUser = placeRequest.getParameter("cip", null);
//	}
}
