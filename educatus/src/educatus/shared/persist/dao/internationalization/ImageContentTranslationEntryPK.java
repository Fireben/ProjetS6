package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the imagecontenttranslationentry database table.
 * 
 */
@Embeddable
public class ImageContentTranslationEntryPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "imce_id", unique = true, nullable = false)
	private Integer imceId;

	@Column(name = "cult_id", unique = true, nullable = false)
	private Integer cultId;

	@Column(name = "lang_id", unique = true, nullable = false)
	private Integer langId;

	public ImageContentTranslationEntryPK() {
	}

	public Integer getImceId() {
		return this.imceId;
	}

	public void setImceId(Integer imceId) {
		this.imceId = imceId;
	}

	public Integer getCultId() {
		return this.cultId;
	}

	public void setCultId(Integer cultId) {
		this.cultId = cultId;
	}

	public Integer getLangId() {
		return this.langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ImageContentTranslationEntryPK)) {
			return false;
		}
		ImageContentTranslationEntryPK castOther = (ImageContentTranslationEntryPK) other;
		return this.imceId.equals(castOther.imceId)
				&& this.cultId.equals(castOther.cultId)
				&& this.langId.equals(castOther.langId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.imceId.hashCode();
		hash = hash * prime + this.cultId.hashCode();
		hash = hash * prime + this.langId.hashCode();

		return hash;
	}
}