package educatus.server.businesslogic.profilmanager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.security.User;
import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.dto.user.UserProfilContent;


@Singleton
public class UserProfilBuilder {
	
	@Inject
	SecurityDao securityDao;

	public UserProfilContent buildUserProfilContent(String cip){
		
		UserProfilContent content = new UserProfilContent();
				
		try {
			User user = securityDao.findUserByCip(cip);
			
			UserCoreContent coreContent = new UserCoreContent();
			coreContent.setCip(user.getCip());
			coreContent.setFirstName(user.getFirstName());
			coreContent.setLastName(user.getLastName());
			
			// TODO, fetch e-mail from LDAP or add column to database
			coreContent.setEmail("");
			coreContent.setPrivacyEnabled(true);
			
			coreContent.setJoinedDate(user.getDateJoined().toString());
			
			// TODO, fetch from log connexion
			coreContent.setLastConnexion(user.getDateJoined().toString());

			content.setProfilImageUrl("images/user_128.png");
			content.setUserCoreContent(coreContent);
			
		} catch (Exception e) {
			// TODO , manage exception
			e.printStackTrace();
			return null;
		}
		
		return content;		
	}	
}
