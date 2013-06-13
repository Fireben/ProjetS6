package educatus.client;

import com.google.inject.Singleton;

@Singleton
public class EducatusLocale {
    private String language = null;
    private String culture = null;

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
