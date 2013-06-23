package educatus.server.businesslogic.profilmanager;

import java.sql.Date;

import com.google.inject.Singleton;

import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.dto.user.UserProfilContent;


@Singleton
public class UserProfilBuilder {

	public UserProfilContent buildUserProfilContent(String cip){
		
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

		content.setProfilImageUrl("images/user_128.png");
		content.setUserCoreContent(coreContent);
		return content;		
	}
	
}
