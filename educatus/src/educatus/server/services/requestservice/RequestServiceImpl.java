package educatus.server.services.requestservice;

import java.util.List;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import educatus.server.businesslogic.LDAPManager;
import educatus.server.businesslogic.PermissionManager;
import educatus.server.businesslogic.SessionManager;
import educatus.server.businesslogic.exercicemanager.ExerciceAdministrationManager;
import educatus.server.businesslogic.exercicemanager.ExerciceContentBuilder;
import educatus.server.businesslogic.exercicemanager.ExerciceHomeListingBuilder;
import educatus.server.businesslogic.profilmanager.UserProfilBuilder;
import educatus.server.businesslogic.seminarymanager.SeminaryAdministrationManager;
import educatus.server.businesslogic.seminarymanager.SeminaryContentBuilder;
import educatus.server.businesslogic.seminarymanager.SeminaryHomeCategoryBuilder;
import educatus.server.businesslogic.seminarymanager.SeminaryHomeListingBuilder;
import educatus.server.businesslogic.uibuilder.HomePageContentBuilder;
import educatus.server.businesslogic.uibuilder.MainPageContentBuilder;
import educatus.server.businesslogic.uibuilder.SeminaryEditorContentBuilder;
import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.shared.dto.exercice.ExerciceContent;
import educatus.shared.dto.pagecontent.ExerciceHomePageListingContent;
import educatus.shared.dto.pagecontent.HomePageContent;
import educatus.shared.dto.pagecontent.MainPageContent;
import educatus.shared.dto.pagecontent.SeminaryAdministrationPageContent;
import educatus.shared.dto.pagecontent.SeminaryHomePageCategoryContent;
import educatus.shared.dto.pagecontent.SeminaryHomePageListingContent;
import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.dto.seminary.DifficultyContent;
import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.dto.user.UserProfilContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.RequestTypeEnum;
import educatus.shared.services.requestservice.request.ExerciceAdministrationActionRequest;
import educatus.shared.services.requestservice.request.ExerciceAdministrationActionRequest.ExerciceAdministractionAction;
import educatus.shared.services.requestservice.request.ExerciceContentRequest;
import educatus.shared.services.requestservice.request.ExerciceHomePageListingContentRequest;
import educatus.shared.services.requestservice.request.ExerciceQuestionValidationRequest;
import educatus.shared.services.requestservice.request.HomePageContentRequest;
import educatus.shared.services.requestservice.request.LoginRequest;
import educatus.shared.services.requestservice.request.MainPageContentRequest;
import educatus.shared.services.requestservice.request.SeminaryAdministrationActionRequest;
import educatus.shared.services.requestservice.request.SeminaryAdministrationActionRequest.SeminaryAdministractionAction;
import educatus.shared.services.requestservice.request.SeminaryAdministrationPageContentRequest;
import educatus.shared.services.requestservice.request.SeminaryContentRequest;
import educatus.shared.services.requestservice.request.SeminaryHomePageCategoryContentRequest;
import educatus.shared.services.requestservice.request.SeminaryHomePageListingContentRequest;
import educatus.shared.services.requestservice.request.UserContentRequest;
import educatus.shared.services.requestservice.request.UserListingRequest;
import educatus.shared.services.requestservice.response.ExerciceAdministrationActionResponse;
import educatus.shared.services.requestservice.response.ExerciceContentResponse;
import educatus.shared.services.requestservice.response.ExerciceQuestionValidationResponse;
import educatus.shared.services.requestservice.response.HomePageContentResponse;
import educatus.shared.services.requestservice.response.LoginResponse;
import educatus.shared.services.requestservice.response.LoginResponse.LoginStatus;
import educatus.shared.services.requestservice.response.ExerciceHomePageListingContentResponse;
import educatus.shared.services.requestservice.response.MainPageContentResponse;
import educatus.shared.services.requestservice.response.SeminaryAdministrationActionResponse;
import educatus.shared.services.requestservice.response.SeminaryAdministrationPageContentResponse;
import educatus.shared.services.requestservice.response.SeminaryContentResponse;
import educatus.shared.services.requestservice.response.SeminaryHomePageCategoryContentResponse;
import educatus.shared.services.requestservice.response.SeminaryHomePageListingContentResponse;
import educatus.shared.services.requestservice.response.UserContentResponse;
import educatus.shared.services.requestservice.response.UserListingResponse;

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
	private ExerciceHomeListingBuilder exerciceHomeListingBuilder;
	private SeminaryEditorContentBuilder seminaryEditorContentBuilder;
	private SeminaryContentBuilder seminaryContentBuilder;
	private UserProfilBuilder userProfilBuilder;
	private SeminaryAdministrationManager seminaryAdministrationManager;
	private SessionManager sessionManager;
	private PermissionManager permissionManager;
	private ExerciceContentBuilder exerciceContentBuilder;
	private ExerciceAdministrationManager exerciceAdministrationManager;

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
		
		seminaryAdministrationManager = dbInjector.getInstance(SeminaryAdministrationManager.class);		
		sessionManager = dbInjector.getInstance(SessionManager.class);
		permissionManager = dbInjector.getInstance(PermissionManager.class);
		exerciceContentBuilder = dbInjector.getInstance(ExerciceContentBuilder.class);
		exerciceAdministrationManager = dbInjector.getInstance(ExerciceAdministrationManager.class);
		exerciceHomeListingBuilder = dbInjector.getInstance(ExerciceHomeListingBuilder.class);
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
				case EXERCICE_HOME_PAGE_LISTING_CONTENT_REQUEST:
					response = ProcessExerciceHomePageListingContentRequest((ExerciceHomePageListingContentRequest) request);
					break;
				case USER_CONTENT_REQUEST:
					response = ProcessUserContentRequest((UserContentRequest) request);
					break;
				case USER_LISTING_REQUEST:
					response = ProcessUserListingRequest((UserListingRequest) request);
					break;
				case SEMINARY_ADMINISTRATION_PAGE_CONTENT_REQUEST:
					response = ProcessSeminaryAdministrationPageContentRequest((SeminaryAdministrationPageContentRequest) request);
					break;
				case SEMINARY_ADMINISTRATION_ACTION_REQUEST:
					response = ProcessSeminaryAdministrationActionRequest((SeminaryAdministrationActionRequest) request);
					break;
				case SEMINARY_CONTENT_REQUEST:
					response = ProcessSeminaryContentRequest((SeminaryContentRequest) request);
					break;		
				case EXERCICE_CONTENT_REQUEST:
					response = ProcessExerciceContentRequest((ExerciceContentRequest) request);
					break;
				case EXERCICE_QUESTION_VALIDATION_REQUEST:
					response = ProcessExerciceQuestionValidationRequest((ExerciceQuestionValidationRequest) request);
					break;
				case EXERCICE_ADMINISTRATION_ACTION_REQUEST:
					response = ProcessExerciceAdministrationActionRequest((ExerciceAdministrationActionRequest) request);
					break;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	private ExerciceHomePageListingContentResponse ProcessExerciceHomePageListingContentRequest(ExerciceHomePageListingContentRequest request) {
		
		ExerciceHomePageListingContent content = new ExerciceHomePageListingContent();
		
		if(request.getSelectedCategory() == null)
			content = exerciceHomeListingBuilder.buildExerciceHomePageListingContent(request.getCulture(), request.getLanguage());
		else
			content = exerciceHomeListingBuilder.buildExerciceHomePageListingContent(request.getSelectedCategory().getId(), request.getCulture(), request.getLanguage());
			
		ExerciceHomePageListingContentResponse response = new ExerciceHomePageListingContentResponse();
		response.setContent(content);

		return response;
	}

	private UserListingResponse ProcessUserListingRequest(UserListingRequest request) {
		
		UserListingResponse response = new UserListingResponse();
		
		try {
			List<UserCoreContent> userCoreContentList = userProfilBuilder.buildAllUserProfilCoreContent(request.getCulture(), request.getLanguage());
			response.setUserCoreContentList(userCoreContentList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return response;
	}

	private ExerciceAdministrationActionResponse ProcessExerciceAdministrationActionRequest(ExerciceAdministrationActionRequest request) {
		
		ExerciceAdministrationActionResponse response = new ExerciceAdministrationActionResponse();
		
		if (request.getAction() == ExerciceAdministractionAction.INSERT) {
			try {
				exerciceAdministrationManager.insertExercice(request.getExerciceContent());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return response;
	}

	private ExerciceQuestionValidationResponse ProcessExerciceQuestionValidationRequest(ExerciceQuestionValidationRequest request) {
		
		ExerciceQuestionValidationResponse response = new ExerciceQuestionValidationResponse();
		response.setExerciceQuestion(request.getExerciceQuestion());
		// TODO, Check validation
		response.setValid(true);
		return response;
	}

	private ExerciceContentResponse ProcessExerciceContentRequest(ExerciceContentRequest request) {
		ExerciceContentResponse response = new ExerciceContentResponse();
				
		ExerciceContent exerciceContent = exerciceContentBuilder.buildExerciceContent(request.getSelectedExerciceId(), request.getCulture(), request.getLanguage());		
		response.setExerciceContent(exerciceContent);
		
		return response;
	}

	private SeminaryAdministrationActionResponse ProcessSeminaryAdministrationActionRequest(SeminaryAdministrationActionRequest request) {
		SeminaryAdministrationActionResponse reponse = new SeminaryAdministrationActionResponse();

		if (request.getAction() == SeminaryAdministractionAction.INSERT) {
			try {
				
				String cip = sessionManager.getSessionAssociatedCip(request.getSessionID());
				
				if (cip != null){
					// TODO Verify if user has permission
					boolean userHasPermission = true;
					if (userHasPermission) {
						seminaryAdministrationManager.insertSeminary(request.getSeminaryContent(), cip);							
					} else {
						// TODO, doesn't have permission for the action
					}					
				} else {
					// TODO, invalid username, session expired, or user not logged
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return reponse;
	}

	private LoginResponse ProcessLoginRequest(LoginRequest request) {
		
		String providedUsername = request.getUserName();
		String providedPassword = request.getPassword();
		String providedIp = "";
		boolean passwordIsValid = false;
		
		try
		{
			LoginResponse response = new LoginResponse();
			
			// Attempt to validate the credential
			passwordIsValid = LDAPManager.getInstance().authenticate(providedUsername, providedPassword);
			
			// The user cannot connect to the application --> NbFailedConnectionAttempt exceed
			if(!sessionManager.userCanLog(providedUsername))
			{
				response.setLoginStatus(LoginStatus.LOCKED);
				// Force the password invalid even if it is valid since the NbFailedConnectionAttempt have been reach.
				passwordIsValid = false;
				return response;
			}			
					
			// Attempt to retrieve user's information.
			UserProfilContent user = null;
			try {
				user = userProfilBuilder.buildUserProfilContent(providedUsername, request.getCulture(), request.getLanguage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (passwordIsValid && user != null) {
				// Login successfull, generate sessionID
				response.setLoginStatus(LoginStatus.SUCCESS);
				response.setSessionID(sessionManager.generateSessionUUID(providedUsername,providedIp).toString());
				response.setUserCoreContent(user.getUserCoreContent());
			} else {			
				response.setLoginStatus(LoginStatus.FAILURE);
			}
			
			return response;
		}
		finally
		{
			// Log each Login attempt, even if the user is locked.
			sessionManager.logConnectionAttempt(providedUsername, passwordIsValid);
		}
	}

	private SeminaryAdministrationPageContentResponse ProcessSeminaryAdministrationPageContentRequest(SeminaryAdministrationPageContentRequest request) {
		
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
		SeminaryHomePageListingContent content = new SeminaryHomePageListingContent();
		
		if(request.getSelectedCategory() == null)
			content = seminaryHomeListingBuilder.buildSeminaryHomeListingContent(request.getCulture(), request.getLanguage());
		else
			content = seminaryHomeListingBuilder.buildSeminaryHomeListingContent(request.getSelectedCategory().getId(), request.getCulture(), request.getLanguage());
			
		SeminaryHomePageListingContentResponse response = new SeminaryHomePageListingContentResponse();
		response.setContent(content);

		return response;
	}

	private UserContentResponse ProcessUserContentRequest(UserContentRequest request) {
		UserProfilContent content = null;
		try {
			
			// TODO IF requestedUser fits with SessionID, we need full access to content
			content = userProfilBuilder.buildUserProfilContent(request.getRequestedUser(), request.getCulture(), request.getLanguage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserContentResponse response = new UserContentResponse();
		response.setUserProfilContent(content);
		return response;
	}
}
