package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import educatus.shared.persist.dao.internationalization.image.AbstractImage;

public class ImageContentTranslationEntry implements Serializable {

	private static final long serialVersionUID = 5342429386700372203L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CULT_Id", nullable = false)
	private Culture 		culture;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LANG_Id", nullable = false)
	private Language 		language;

	private AbstractImage 	image;
		
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

	public AbstractImage getImage() {
		return image;
	}

	public void setImage(AbstractImage image) {
		this.image = image;
	}
}
