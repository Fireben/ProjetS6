package educatus.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import educatus.client.services.RequestService;
import educatus.shared.request.AbstractRequest;
import educatus.shared.request.RequestTypeEnum;
import educatus.shared.response.AbstractResponse;
import educatus.shared.response.FooterContentResponse;
import educatus.shared.response.HomePageContentResponse;
import educatus.shared.response.MainMenuContentResponse;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RequestServiceImpl extends RemoteServiceServlet implements RequestService {

	@Override
	public AbstractResponse sendRequest(AbstractRequest request) throws IllegalArgumentException {
		
		AbstractResponse response = null;
		RequestTypeEnum requestType = request.GetRequestType();
		
		switch (requestType) {
			case FOOTER_CONTENT_REQUEST:
				response = new FooterContentResponse();
				break;				
			case HOME_PAGE_CONTENT_REQUEST:
				response = new HomePageContentResponse();
				break;				
			case MAIN_MENU_CONTENT_REQUEST:
				response = new MainMenuContentResponse();
				break;	
			default:
				break;
		}
				
		return response;
	}
}
