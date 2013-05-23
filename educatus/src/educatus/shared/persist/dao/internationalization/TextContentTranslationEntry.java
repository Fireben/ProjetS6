package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class TextContentTranslationEntry implements Serializable {
	
	private static final long serialVersionUID = 8431089526329699040L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "CULT_Id", nullable = false)
	private Culture 	culture;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "LANG_Id", nullable = false)
	private Language 	language;
		
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
}
