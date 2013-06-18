package educatus.server.businesslogic.profilmanager;

import java.sql.Date;

import com.google.inject.Singleton;

import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.dto.user.UserProfilContent;

@Singleton
public class UserProfilFactory {

	public UserProfilContent createUserProfilContent(String cip){
		
		UserProfilContent content = new UserProfilContent();
		
		UserCoreContent coreContent = new UserCoreContent();
		coreContent.setCip(cip);
		coreContent.setFirstName("Jean-Sébastien");
		coreContent.setLastName("Gravel");
		coreContent.setEmail("Jean-Sebastien.Gravel@usherbrooke.ca");
		coreContent.setPrivacyEnabled(true);
		
		java.util.Date today = new java.util.Date();
		coreContent.setJoinedDate(new Date(today.getTime()));		
		coreContent.setLastConnexion(new Date(today.getTime()));

		content.setUserCoreContent(coreContent);
		content.setProfilImageUrl("images/icons/user_128.png");
		return content;		
	}
}
