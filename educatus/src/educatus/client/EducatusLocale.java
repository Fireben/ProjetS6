package educatus.client;

import com.google.inject.Singleton;

@Singleton
public class EducatusLocale {
	private String language = "en";
	private String culture = "CA";

	public EducatusLocale() {

	}

	public String getLanguage() {
		return language;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
