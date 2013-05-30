package educatus.server.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ImageContentTranslationEntryPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "imce_id", unique = true, nullable = false)
	private Integer imageContentEntryId;

	@Column(name = "cult_id", unique = true, nullable = false)
	private Integer cultureId;

	@Column(name = "lang_id", unique = true, nullable = false)
	private Integer languageId;

	public ImageContentTranslationEntryPK() {
	}

	public Integer getImageContentEntryId() {
		return this.imageContentEntryId;
	}

	public void setImageContentEntryId(Integer imageContentEntryId) {
		this.imageContentEntryId = imageContentEntryId;
	}

	public Integer getCultureId() {
		return this.cultureId;
	}

	public void setCultureId(Integer cultureId) {
		this.cultureId = cultureId;
	}

	public Integer getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ImageContentTranslationEntryPK)) {
			return false;
		}
		ImageContentTranslationEntryPK castOther = (ImageContentTranslationEntryPK) other;
		return this.imageContentEntryId.equals(castOther.imageContentEntryId)
				&& this.cultureId.equals(castOther.cultureId)
				&& this.languageId.equals(castOther.languageId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.imageContentEntryId.hashCode();
		hash = hash * prime + this.cultureId.hashCode();
		hash = hash * prime + this.languageId.hashCode();

		return hash;
	}
}