package educatus.server.businesslogic.profilmanager;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.LDAPManager;
import educatus.server.businesslogic.LDAPUser;
import educatus.server.businesslogic.SeminaryAdapter;
import educatus.server.businesslogic.UserAdapter;
import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Seminary;
import educatus.shared.dto.seminary.SeminaryCoreContent;
import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.dto.user.UserProfilContent;

@Singleton
public class UserProfilBuilder {
	
	@Inject
	SecurityDao securityDao;
	
	@Inject 
	SeminaryDao seminaryDao;

	public UserProfilContent buildUserProfilContent(String cip, String culture, String language) throws Exception {
		
		UserProfilContent content = new UserProfilContent();
				
		User requestedUser = securityDao.findUserByCip(cip);
		UserCoreContent requestedUserCoreContent = UserAdapter.userToUserCoreContent(requestedUser);
		LDAPUser requestedLDAPUser = LDAPManager.getInstance().findUser(cip);	
		requestedUserCoreContent = UserAdapter.mergeLDAPUser(requestedUserCoreContent, requestedLDAPUser);

		// TODO, request correct image, fallback to default image
		content.setProfilImageUrl("images/user_128.png");
		content.setUserCoreContent(requestedUserCoreContent);
		
		// TODO, replace with real completed seminary list			
		List<Seminary> completedSeminaryList = seminaryDao.findAllSeminary();	
		int count = 0;
		for (Seminary seminary : completedSeminaryList) {
			if (count > 5) {
				break;
			}
			SeminaryCoreContent seminaryCoreContent = SeminaryAdapter.seminaryToSeminaryCoreContent(seminary, culture, language);			
			content.getCompletedSeminaryList().add(seminaryCoreContent);
			count++;
		}
		
		return content;		
	}
	
	public List<UserCoreContent> buildAllUserProfilCoreContent(String culture, String language) throws Exception {
		
		List<UserCoreContent> userCoreContentList = new ArrayList<UserCoreContent>();
		
		List<User> userList = securityDao.findAllUsers();
		for (User user : userList) {
			UserCoreContent userCoreContent = UserAdapter.userToUserCoreContent(user);
			LDAPUser requestedLDAPUser = LDAPManager.getInstance().findUser(user.getCip());	
			userCoreContent = UserAdapter.mergeLDAPUser(userCoreContent, requestedLDAPUser);
			userCoreContentList.add(userCoreContent);
		}
		
		return userCoreContentList;
	}
}
