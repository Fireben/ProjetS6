package educatus.server.services.requestservice;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import educatus.server.businesslogic.uibuilder.HomePageFactory;
import educatus.server.businesslogic.uibuilder.SeminaryHomeCategoryFactory;
import educatus.server.businesslogic.uibuilder.SeminaryHomeListingFactory;
import educatus.shared.dto.HomePageContent;
import educatus.shared.dto.seminary.SeminaryHomeCategoryContent;
import educatus.shared.dto.seminary.SeminaryHomeListingContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.RequestTypeEnum;
import educatus.shared.services.requestservice.request.FooterContentRequest;
import educatus.shared.services.requestservice.request.HomePageContentRequest;
import educatus.shared.services.requestservice.request.MainMenuContentRequest;
import educatus.shared.services.requestservice.request.SeminaryHomePageCategoryContentRequest;
import educatus.shared.services.requestservice.request.SeminaryHomePageListingContentRequest;
import educatus.shared.services.requestservice.response.FooterContentResponse;
import educatus.shared.services.requestservice.response.HomePageContentResponse;
import educatus.shared.services.requestservice.response.MainMenuContentResponse;
import educatus.shared.services.requestservice.response.SeminaryHomePageCategoryContentResponse;
import educatus.shared.services.requestservice.response.SeminaryHomePageListingContentResponse;

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
				case SEMINARY_HOME_PAGE_CATEGORY_CONTENT_REQUEST:
					response = ProcessSeminaryHomePageCategoryContentRequest((SeminaryHomePageCategoryContentRequest) request);
					break;
				case SEMINARY_HOME_PAGE_LISTING_CONTENT_REQUEST:
					response = ProcessSeminaryHomePageListingContentRequest((SeminaryHomePageListingContentRequest) request);
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
		HomePageContent content = HomePageFactory.createHomePageContent("CA", "fr");
		
		HomePageContentResponse response = new HomePageContentResponse();
		response.setHomeContent(content);
		
		return response;
	}	
	
	private SeminaryHomePageCategoryContentResponse ProcessSeminaryHomePageCategoryContentRequest(SeminaryHomePageCategoryContentRequest request){
		
		SeminaryHomeCategoryContent content = SeminaryHomeCategoryFactory.createSeminaryHomeCategoryContent(
			request.getParentCategory().getId(), 
			"CA", 
			"fr"
		);
		
		SeminaryHomePageCategoryContentResponse response = new SeminaryHomePageCategoryContentResponse();
		response.setContent(content);
		
		return response;
		
	}
	
	private SeminaryHomePageListingContentResponse ProcessSeminaryHomePageListingContentRequest(SeminaryHomePageListingContentRequest request){
		SeminaryHomeListingContent content = SeminaryHomeListingFactory.createSeminaryHomeListingContent(
				request.getSelectedCategory().getId(), 
				"CA", 
				"fr"
			);
			
		SeminaryHomePageListingContentResponse response = new SeminaryHomePageListingContentResponse();
		response.setContent(content);
		
		return response;
	}
}
