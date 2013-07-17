package educatus.shared.services.requestservice;

public enum RequestTypeEnum {
	/* Page Content Requests */
	MAIN_PAGE_CONTENT_REQUEST,
	HOME_PAGE_CONTENT_REQUEST,
	PROFIL_PAGE_CONTENT_REQUEST,
	SEMINARY_ADMINISTRATION_PAGE_CONTENT_REQUEST,
	CATEGORY_ADMINISTRATION_PAGE_CONTENT_REQUEST,
	EXERCICE_ADMINISTRATION_PAGE_CONTENT_REQUEST,

	LOGIN_REQUEST,
	USER_LISTING_REQUEST,
	USER_CONTENT_REQUEST,
	
	SEMINARY_HOME_PAGE_CATEGORY_CONTENT_REQUEST,
	SEMINARY_HOME_PAGE_LISTING_CONTENT_REQUEST,	
	EXERCICE_HOME_PAGE_LISTING_CONTENT_REQUEST,
	SEMINARY_CONTENT_REQUEST,
	
	EXERCICE_CONTENT_REQUEST,
	EXERCICE_QUESTION_VALIDATION_REQUEST,
		
	SEMINARY_ADMINISTRATION_ACTION_REQUEST,
	EXERCICE_ADMINISTRATION_ACTION_REQUEST
}
