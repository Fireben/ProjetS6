package educatus.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import educatus.client.services.RequestService;
import educatus.shared.request.AbstractRequest;
import educatus.shared.request.FooterContentRequest;
import educatus.shared.request.HomePageContentRequest;
import educatus.shared.request.MainMenuContentRequest;
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
		
		try {
			switch (requestType) {
				case FOOTER_CONTENT_REQUEST:
					response = ProcessFooterContentRequest((FooterContentRequest) request);
					break;				
				case HOME_PAGE_CONTENT_REQUEST:
					response = ProcessHomePageContentRequest((HomePageContentRequest) request);
					break;				
				case MAIN_MENU_CONTENT_REQUEST:
					response = ProcessMainMenuContentRequest((MainMenuContentRequest) request);
					break;	
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return response;
	}
	
	private FooterContentResponse ProcessFooterContentRequest(FooterContentRequest request)
	{
		FooterContentResponse response = new FooterContentResponse();
		
		return response;
	}
	
	private MainMenuContentResponse ProcessMainMenuContentRequest(MainMenuContentRequest request)
	{
		MainMenuContentResponse response = new MainMenuContentResponse();
		
		return response;
	}
	
	private HomePageContentResponse ProcessHomePageContentRequest(HomePageContentRequest request)
	{
		HomePageContentResponse response = new HomePageContentResponse();
		
		return response;
	}	
}
