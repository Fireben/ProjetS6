package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the textcontenttranslationentry database table.
 * 
 */
@Embeddable
public class TextContentTranslationEntryPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "lang_id", unique = true, nullable = false)
	private Integer langId;

	@Column(name = "cult_id", unique = true, nullable = false)
	private Integer cultId;

	@Column(name = "tece_id", unique = true, nullable = false)
	private Integer teceId;

	public TextContentTranslationEntryPK() {
	}

	public Integer getLangId() {
		return this.langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public Integer getCultId() {
		return this.cultId;
	}

	public void setCultId(Integer cultId) {
		this.cultId = cultId;
	}

	public Integer getTeceId() {
		return this.teceId;
	}

	public void setTeceId(Integer teceId) {
		this.teceId = teceId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TextContentTranslationEntryPK)) {
			return false;
		}
		TextContentTranslationEntryPK castOther = (TextContentTranslationEntryPK) other;
		return this.langId.equals(castOther.langId)
				&& this.cultId.equals(castOther.cultId)
				&& this.teceId.equals(castOther.teceId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.langId.hashCode();
		hash = hash * prime + this.cultId.hashCode();
		hash = hash * prime + this.teceId.hashCode();

		return hash;
	}
}