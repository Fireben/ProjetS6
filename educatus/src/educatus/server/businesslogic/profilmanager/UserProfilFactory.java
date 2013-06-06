package educatus.server.businesslogic.profilmanager;

import java.sql.Date;

import educatus.shared.dto.user.UserProfilContent;


public class UserProfilFactory {

	public UserProfilContent createUserProfilContent(String cip){
		
		UserProfilContent content = new UserProfilContent();
		
		content.setCip(cip);
		content.setFirstName("Jean-Sébastien");
		content.setLastName("Gravel");
		content.setEmail("Jean-Sebastien.Gravel@usherbrooke.ca");
		content.setPrivacyEnabled(true);
		content.setProfilImageUrl("images/icons/user_128.png");
		content.setJoinedDate(new Date(2013, 6, 6));
		
		java.util.Date today = new java.util.Date();
		content.setLastConnexion(new Date(today.getTime()));
				
		return content;		
	}
	
}
