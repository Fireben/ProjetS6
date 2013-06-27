package educatus.server.services.requestservice;

import java.util.List;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import educatus.server.businesslogic.profilmanager.UserProfilBuilder;
import educatus.server.businesslogic.seminarymanager.SeminaryContentBuilder;
import educatus.server.businesslogic.seminarymanager.SeminaryHomeCategoryBuilder;
import educatus.server.businesslogic.seminarymanager.SeminaryHomeListingBuilder;
import educatus.server.businesslogic.uibuilder.HomePageContentBuilder;
import educatus.server.businesslogic.uibuilder.MainPageContentBuilder;
import educatus.server.businesslogic.uibuilder.SeminaryEditorContentBuilder;
import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.shared.dto.pagecontent.HomePageContent;
import educatus.shared.dto.pagecontent.MainPageContent;
import educatus.shared.dto.pagecontent.SeminaryAdministrationPageContent;
import educatus.shared.dto.pagecontent.SeminaryHomePageCategoryContent;
import educatus.shared.dto.pagecontent.SeminaryHomePageListingContent;
import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.dto.seminary.DifficultyContent;
import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.dto.user.UserProfilContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.RequestTypeEnum;
import educatus.shared.services.requestservice.request.HomePageContentRequest;
import educatus.shared.services.requestservice.request.LoginRequest;
import educatus.shared.services.requestservice.request.MainPageContentRequest;
import educatus.shared.services.requestservice.request.SeminaryContentRequest;
import educatus.shared.services.requestservice.request.SeminaryAdministrationPageContentRequest;
import educatus.shared.services.requestservice.request.SeminaryHomePageCategoryContentRequest;
import educatus.shared.services.requestservice.request.SeminaryHomePageListingContentRequest;
import educatus.shared.services.requestservice.request.UserProfilPageContentRequest;
import educatus.shared.services.requestservice.response.HomePageContentResponse;
import educatus.shared.services.requestservice.response.LoginResponse;
import educatus.shared.services.requestservice.response.LoginResponse.LoginStatus;
import educatus.shared.services.requestservice.response.MainPageContentResponse;
import educatus.shared.services.requestservice.response.SeminaryContentResponse;
import educatus.shared.services.requestservice.response.SeminaryAdministrationPageContentResponse;
import educatus.shared.services.requestservice.response.SeminaryHomePageCategoryContentResponse;
import educatus.shared.services.requestservice.response.SeminaryHomePageListingContentResponse;
import educatus.shared.services.requestservice.response.UserProfilPageContentResponse;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Singleton
public class RequestServiceImpl extends RemoteServiceServlet implements RequestService {

	private MainPageContentBuilder mainPageContentBuilder;
	private HomePageContentBuilder homePageContentBuilder;
	private SeminaryHomeCategoryBuilder seminaryHomeCategoryBuilder;
	private SeminaryHomeListingBuilder seminaryHomeListingBuilder;
	private SeminaryEditorContentBuilder seminaryEditorContentBuilder;
	private SeminaryContentBuilder seminaryContentBuilder;
	private UserProfilBuilder userProfilBuilder;

	@Override
	public void init() throws ServletException {
		super.init();

		DaoModule module = new DaoModule("db-manager-localhost");
		Injector dbInjector = Guice.createInjector(module);
		dbInjector.getInstance(JpaInitializer.class);
		// dbInjector.getInstance(InternationalizationDao.class);
		// dbInjector.getInstance(SeminaryDao.class);
		mainPageContentBuilder = dbInjector.getInstance(MainPageContentBuilder.class);
		homePageContentBuilder = dbInjector.getInstance(HomePageContentBuilder.class);
		userProfilBuilder = dbInjector.getInstance(UserProfilBuilder.class);
		seminaryHomeCategoryBuilder = dbInjector.getInstance(SeminaryHomeCategoryBuilder.class);
		seminaryHomeListingBuilder = dbInjector.getInstance(SeminaryHomeListingBuilder.class);
		seminaryContentBuilder = dbInjector.getInstance(SeminaryContentBuilder.class);
		seminaryEditorContentBuilder = dbInjector.getInstance(SeminaryEditorContentBuilder.class); 
	}

	@Override
	public AbstractResponse sendRequest(AbstractRequest request) throws IllegalArgumentException {

		AbstractResponse response = null;
		RequestTypeEnum requestType = request.GetRequestType();
		String sessionID = request.getSessionID();
		
		System.out.println("SESSION ID : " + sessionID);

		try {
			switch (requestType) {
			case LOGIN_REQUEST:
				response = ProcessLoginRequest((LoginRequest) request);			
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
			case SEMINARY_ADMINISTRATION_CONTENT_REQUEST:
				response = ProcessSeminaryEditorContentRequest((SeminaryAdministrationPageContentRequest) request);
				break;
			case SEMINARY_CONTENT_REQUEST:
				response = ProcessSeminaryContentRequest((SeminaryContentRequest) request);
				break;
			case PROFIL_PAGE_CONTENT_REQUEST:
				response = ProcessUserProfilContentRequest((UserProfilPageContentRequest) request);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	private LoginResponse ProcessLoginRequest(LoginRequest request) {
		LoginResponse response = new LoginResponse();
		
		// TODO, check password with database
		String providedPassword = request.getPassword();

		boolean passwordValid = false;
		if (providedPassword.equalsIgnoreCase("ADMIN")){
			passwordValid = true;
		}
		
		String userName = request.getUserName();
		UserProfilContent user = userProfilBuilder.buildUserProfilContent(userName);
		
		if (passwordValid && user != null) {
			// Login successfull, generate sessionID
			// TODO, store sessionID on server-side too
			String generatedSessionID = "SAUCE";
			response.setLoginStatus(LoginStatus.SUCCESS);
			response.setSessionID(generatedSessionID);
			response.setUserCoreContent(user.getUserCoreContent());
		} else {			
			response.setLoginStatus(LoginStatus.FAILURE);
		}
		
		return response;
	}

	private SeminaryAdministrationPageContentResponse ProcessSeminaryEditorContentRequest(SeminaryAdministrationPageContentRequest request) {
		
		SeminaryAdministrationPageContentResponse response = new SeminaryAdministrationPageContentResponse();
		
		SeminaryAdministrationPageContent seminary = seminaryEditorContentBuilder.buildSeminaryEditorContent(request.getCulture(), request.getLanguage());
		
		try {
			List<CategoryCoreContent> categoryCoreContentList = seminaryEditorContentBuilder.buildCategoryCoreContentList(request.getCulture(), request.getLanguage());
			List<DifficultyContent> difficultyContentList = seminaryEditorContentBuilder.buildDifficultyContentList(request.getCulture(), request.getLanguage());

			response.setCategoryCoreContentList(categoryCoreContentList);
			response.setDifficultyContentList(difficultyContentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setSeminaryEditorContent(seminary);
		
		return response;
	}

	private SeminaryContentResponse ProcessSeminaryContentRequest(SeminaryContentRequest request) {

		SeminaryContentResponse response = new SeminaryContentResponse();

		SeminaryContent content = seminaryContentBuilder.buildSeminaryContent(request.getSelectedSeminaryId(), request.getCulture(), request.getLanguage());

		response.setSeminaryContent(content);

		return response;
	}

	private MainPageContentResponse ProcessMainMenuContentRequest(MainPageContentRequest request) {
		// TODO, validate if requested view mode is allowed
		MainPageContent content = mainPageContentBuilder.buildMainPageContent(request.getCulture(), request.getLanguage(), request.getViewMode());

		MainPageContentResponse response = new MainPageContentResponse();
		response.setMainPageContent(content);
		response.setViewMode(request.getViewMode());

		return response;
	}

	private HomePageContentResponse ProcessHomePageContentRequest(HomePageContentRequest request) {
		HomePageContent content = homePageContentBuilder.buildHomePageContent(request.getCulture(), request.getLanguage());

		HomePageContentResponse response = new HomePageContentResponse();
		response.setHomeContent(content);

		return response;
	}

	private SeminaryHomePageCategoryContentResponse ProcessSeminaryHomePageCategoryContentRequest(SeminaryHomePageCategoryContentRequest request) {

		Integer parentCategoryId = null;
		if (request.getParentCategory() != null) {
			parentCategoryId = request.getParentCategory().getId();
		}

		SeminaryHomePageCategoryContent content = seminaryHomeCategoryBuilder.buildSeminaryHomeCategoryContent(parentCategoryId, request.getCulture(), request.getLanguage());

		SeminaryHomePageCategoryContentResponse response = new SeminaryHomePageCategoryContentResponse();
		response.setContent(content);

		return response;

	}

	private SeminaryHomePageListingContentResponse ProcessSeminaryHomePageListingContentRequest(SeminaryHomePageListingContentRequest request) {
		SeminaryHomePageListingContent content = seminaryHomeListingBuilder.buildSeminaryHomeListingContent(request.getSelectedCategory().getId(), request.getCulture(), request.getLanguage());

		SeminaryHomePageListingContentResponse response = new SeminaryHomePageListingContentResponse();
		response.setContent(content);

		return response;
	}

	private UserProfilPageContentResponse ProcessUserProfilContentRequest(UserProfilPageContentRequest request) {
		UserProfilContent content = userProfilBuilder.buildUserProfilContent(request.getUserCip());

		UserProfilPageContentResponse response = new UserProfilPageContentResponse();
		response.setUserProfilContent(content);

		return response;
	}
}
