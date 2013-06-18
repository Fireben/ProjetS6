package educatus.server.services.requestservice;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import educatus.server.businesslogic.profilmanager.UserProfilFactory;
import educatus.server.businesslogic.uibuilder.HomePageFactory;
import educatus.server.businesslogic.uibuilder.MainPageFactory;
import educatus.server.businesslogic.uibuilder.SeminaryHomeCategoryFactory;
import educatus.server.businesslogic.uibuilder.SeminaryHomeListingFactory;
import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.shared.dto.HomePageContent;
import educatus.shared.dto.MainPageContent;
import educatus.shared.dto.seminary.SeminaryHomeCategoryContent;
import educatus.shared.dto.seminary.SeminaryHomeListingContent;
import educatus.shared.dto.user.UserProfilContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.RequestTypeEnum;
import educatus.shared.services.requestservice.request.FooterContentRequest;
import educatus.shared.services.requestservice.request.HomePageContentRequest;
import educatus.shared.services.requestservice.request.MainPageContentRequest;
import educatus.shared.services.requestservice.request.SeminaryHomePageCategoryContentRequest;
import educatus.shared.services.requestservice.request.SeminaryHomePageListingContentRequest;
import educatus.shared.services.requestservice.request.UserProfilContentRequest;
import educatus.shared.services.requestservice.response.FooterContentResponse;
import educatus.shared.services.requestservice.response.HomePageContentResponse;
import educatus.shared.services.requestservice.response.MainPageContentResponse;
import educatus.shared.services.requestservice.response.SeminaryHomePageCategoryContentResponse;
import educatus.shared.services.requestservice.response.SeminaryHomePageListingContentResponse;
import educatus.shared.services.requestservice.response.UserProfilContentResponse;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Singleton
public class RequestServiceImpl extends RemoteServiceServlet implements RequestService {

	private MainPageFactory mainPageFactory;
	private HomePageFactory homePageFactory;
	private SeminaryHomeCategoryFactory seminaryHomeCategoryFactory;
	private SeminaryHomeListingFactory seminaryHomeListingFactory;
	private UserProfilFactory userProfilFactory;
	
	@Override
	public void init() throws ServletException {
		super.init();
				
		DaoModule module = new DaoModule("db-manager-localhost");
		Injector dbInjector = Guice.createInjector(module);	 
		dbInjector.getInstance(JpaInitializer.class);
		//dbInjector.getInstance(InternationalizationDao.class);
		//dbInjector.getInstance(SeminaryDao.class);
		mainPageFactory = dbInjector.getInstance(MainPageFactory.class);
		homePageFactory = dbInjector.getInstance(HomePageFactory.class);
		userProfilFactory = dbInjector.getInstance(UserProfilFactory.class);
		seminaryHomeCategoryFactory = dbInjector.getInstance(SeminaryHomeCategoryFactory.class);
		seminaryHomeListingFactory = dbInjector.getInstance(SeminaryHomeListingFactory.class);
	}

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
				case MAIN_PAGE_CONTENT_REQUEST:
					response = ProcessMainMenuContentRequest((MainPageContentRequest) request);
					break;	
				case SEMINARY_HOME_PAGE_CATEGORY_CONTENT_REQUEST:
					response = ProcessSeminaryHomePageCategoryContentRequest((SeminaryHomePageCategoryContentRequest) request);
					break;
				case SEMINARY_HOME_PAGE_LISTING_CONTENT_REQUEST:
					response = ProcessSeminaryHomePageListingContentRequest((SeminaryHomePageListingContentRequest) request);
					break;
				case PROFIL_PAGE_CONTENT_REQUEST:
					response = ProcessUserProfilContentRequest((UserProfilContentRequest) request);
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
	
	private MainPageContentResponse ProcessMainMenuContentRequest(MainPageContentRequest request)
	{
		MainPageContent content = mainPageFactory.createMainPageContent(request.getCulture(), request.getLanguage());
		
		MainPageContentResponse response = new MainPageContentResponse();
		response.setMainPageContent(content);
		
		return response;
	}
	
	private HomePageContentResponse ProcessHomePageContentRequest(HomePageContentRequest request)
	{
		HomePageContent content = homePageFactory.createHomePageContent(request.getCulture(), request.getLanguage());
		
		HomePageContentResponse response = new HomePageContentResponse();
		response.setHomeContent(content);
		
		return response;
	}	
	
	private SeminaryHomePageCategoryContentResponse ProcessSeminaryHomePageCategoryContentRequest(SeminaryHomePageCategoryContentRequest request){
		
		Integer parentCategoryId = null;
		if (request.getParentCategory() != null) {
			parentCategoryId = request.getParentCategory().getId();
		}
		
		SeminaryHomeCategoryContent content = seminaryHomeCategoryFactory.createSeminaryHomeCategoryContent(
			parentCategoryId, 
			"CA", 
			"fr"
		);
		
		SeminaryHomePageCategoryContentResponse response = new SeminaryHomePageCategoryContentResponse();
		response.setContent(content);
		
		return response;
		
	}
	
	private SeminaryHomePageListingContentResponse ProcessSeminaryHomePageListingContentRequest(SeminaryHomePageListingContentRequest request){
		SeminaryHomeListingContent content = seminaryHomeListingFactory.createSeminaryHomeListingContent(
				request.getSelectedCategory().getId(), 
				"CA", 
				"fr"
			);
			
		SeminaryHomePageListingContentResponse response = new SeminaryHomePageListingContentResponse();
		response.setContent(content);
		
		return response;
	}
	
	private UserProfilContentResponse ProcessUserProfilContentRequest(UserProfilContentRequest request){
		UserProfilContent content = userProfilFactory.createUserProfilContent(
				request.getUserCip()
		);
		
		UserProfilContentResponse response = new UserProfilContentResponse();
		response.setUserProfilContent(content);
		
		return response;
	}
}
