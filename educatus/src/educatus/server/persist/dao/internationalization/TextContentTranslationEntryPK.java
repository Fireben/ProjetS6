package educatus.server.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TextContentTranslationEntryPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "lang_id", unique = true, nullable = false)
	private Integer languageId;

	@Column(name = "cult_id", unique = true, nullable = false)
	private Integer cultureId;

	@Column(name = "tece_id", unique = true, nullable = false)
	private Integer textContentEntryId;

	public TextContentTranslationEntryPK() {
	}

	public TextContentTranslationEntryPK(Integer languageId, Integer cultureId, Integer textContentEntryId) {
		this.languageId = languageId;
		this.cultureId = cultureId;
		this.textContentEntryId = textContentEntryId;
	}
	
	public Integer getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Integer langId) {
		this.languageId = langId;
	}

	public Integer getCultureId() {
		return this.cultureId;
	}

	public void setCultureId(Integer cultId) {
		this.cultureId = cultId;
	}

	public Integer getTextContentEntryId() {
		return this.textContentEntryId;
	}

	public void setTextContentEntryId(Integer textContentEntryId) {
		this.textContentEntryId = textContentEntryId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TextContentTranslationEntryPK)) {
			return false;
		}
		TextContentTranslationEntryPK castOther = (TextContentTranslationEntryPK) other;
		return this.languageId.equals(castOther.languageId)
				&& this.cultureId.equals(castOther.cultureId)
				&& this.textContentEntryId.equals(castOther.textContentEntryId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.languageId.hashCode();
		hash = hash * prime + this.cultureId.hashCode();
		hash = hash * prime + this.textContentEntryId.hashCode();

		return hash;
	}
}