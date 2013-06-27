package educatus.server.businesslogic.profilmanager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.LDAPManager;
import educatus.server.businesslogic.LDAPUser;
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
			LDAPUser ldapUser = LDAPManager.getInstance().findUser(cip);	
			
			UserCoreContent coreContent = new UserCoreContent();
			coreContent.setCip(ldapUser.getCip());
			coreContent.setFirstName(ldapUser.getFirstName());
			coreContent.setLastName(ldapUser.getLastName());
			coreContent.setEmail(ldapUser.getEmail());
			coreContent.setPhone(ldapUser.getPhone());
			coreContent.setTitle(ldapUser.getTitle());
			coreContent.setUnity(ldapUser.getUnity());
			coreContent.setRoom(ldapUser.getRoom());
			coreContent.setFax(ldapUser.getFax());
			coreContent.setStatus(ldapUser.getStatus());
			coreContent.setFullName(ldapUser.getFullName());
			
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
