package educatus.server.businesslogic.profilmanager;

import java.sql.Date;

import com.google.inject.Singleton;

import educatus.shared.dto.user.UserProfilContent;

@Singleton
public class UserProfilFactory {

	public UserProfilContent createUserProfilContent(String cip){
		
		UserProfilContent content = new UserProfilContent();
		
		content.setCip(cip);
		content.setFirstName("Jean-Sébastien");
		content.setLastName("Gravel");
		content.setEmail("Jean-Sebastien.Gravel@usherbrooke.ca");
		content.setPrivacyEnabled(true);
		content.setProfilImageUrl("images/user_128.png");
		content.setJoinedDate(new Date(2013, 6, 6));
		
		java.util.Date today = new java.util.Date();
		content.setLastConnexion(new Date(today.getTime()));
				
		return content;		
	}
	
}
