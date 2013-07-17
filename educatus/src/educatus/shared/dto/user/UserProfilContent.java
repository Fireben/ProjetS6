package educatus.shared.dto.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import educatus.shared.dto.seminary.SeminaryCoreContent;

public class UserProfilContent implements Serializable {

	private static final long serialVersionUID = 7413591483810523323L;
	
	private UserCoreContent userCoreContent;
	private List<SeminaryCoreContent> completedSeminaryList = new ArrayList<SeminaryCoreContent>();
	private List<UserCoreContent> completedUserList = new ArrayList<UserCoreContent>();

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

	public List<SeminaryCoreContent> getCompletedSeminaryList() {
		return completedSeminaryList;
	}

	public void setCompletedSeminaryList(List<SeminaryCoreContent> completedSeminaryList) {
		this.completedSeminaryList = completedSeminaryList;
	}	
	
	
	public List<UserCoreContent> getCompletedUserList() {
		return completedUserList;
	}

	public void setCompletedUserList(List<UserCoreContent> completedUserList) {
		this.completedUserList = completedUserList;
	}
}
