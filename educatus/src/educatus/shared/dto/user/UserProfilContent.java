package educatus.shared.dto.user;

import java.io.Serializable;

public class UserProfilContent implements Serializable {

	private static final long serialVersionUID = 7413591483810523323L;
	
	private UserCoreContent userCoreContent;
	
	private String profilImageUrl;

	//TODO Ajouter les listes d'achievements, certificats, etc
	
	public UserProfilContent() {
	}

	public String getProfilImageUrl() {
		return profilImageUrl;
	}

	public void setProfilImageUrl(String profilImageUrl) {
		this.profilImageUrl = profilImageUrl;
	}

	public UserCoreContent getUserCoreContent() {
		return userCoreContent;
	}

	public void setUserCoreContent(UserCoreContent userCoreContent) {
		this.userCoreContent = userCoreContent;
	}	
}
