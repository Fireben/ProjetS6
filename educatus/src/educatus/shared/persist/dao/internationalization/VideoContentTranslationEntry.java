package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class VideoContentTranslationEntry implements Serializable {

	private static final long serialVersionUID = -180532143330448878L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CULT_Id", nullable = false)
	private Culture 	culture;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LANG_Id", nullable = false)
	private Language 	language;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "VIDE_Id", nullable = false)
	private Video 		video;
		
	public Culture getCulture() {
		return culture;
	}
	
	public void setCulture(Culture culture) {
		this.culture = culture;
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
}
