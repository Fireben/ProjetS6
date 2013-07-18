package educatus.server.businesslogic;

import java.text.SimpleDateFormat;
import java.util.List;

import educatus.server.persist.dao.security.LogUserConnection;
import educatus.server.persist.dao.security.User;
import educatus.shared.dto.user.UserCoreContent;

public class UserAdapter {

	public static UserCoreContent userToUserCoreContent(User user) {
		
		UserCoreContent userCoreContent = new UserCoreContent();
		
		userCoreContent.setCip(user.getCip());
		userCoreContent.setFirstName(user.getFirstName());
		userCoreContent.setLastName(user.getLastName());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM:ss");
		userCoreContent.setJoinedDate(simpleDateFormat.format(user.getDateJoined()));
		
		// Default latest connexion is joined date
		String latestConnexion = simpleDateFormat.format(user.getDateJoined());

		// TODO, create specific query to fetch lasted successfull connexion only
		List<LogUserConnection> logUserConnectionList = user.getLogUserConnections();
		for (int i = logUserConnectionList.size() - 1; i >= 0; i--) {
			LogUserConnection connection = logUserConnectionList.get(i);
			if (connection.getAttemptSuccess() == true) {
				latestConnexion = simpleDateFormat.format(connection.getTimestamp());
				break;
			}
		}
		
		userCoreContent.setLastConnexion(latestConnexion);		
		return userCoreContent;
	}
	
	public static UserCoreContent mergeLDAPUser(UserCoreContent userCoreContent, LDAPUser ldapUser) {
		
		userCoreContent.setCip(ldapUser.getCip());
		userCoreContent.setFirstName(ldapUser.getFirstName());
		userCoreContent.setLastName(ldapUser.getLastName());
		userCoreContent.setEmail(ldapUser.getEmail());
		userCoreContent.setPhone(ldapUser.getPhone());
		userCoreContent.setTitle(ldapUser.getTitle());
		userCoreContent.setUnity(ldapUser.getUnity());
		userCoreContent.setRoom(ldapUser.getRoom());
		userCoreContent.setFax(ldapUser.getFax());
		userCoreContent.setStatus(ldapUser.getStatus());
		userCoreContent.setFullName(ldapUser.getFullName());
				
		return userCoreContent;
	}
	
}
