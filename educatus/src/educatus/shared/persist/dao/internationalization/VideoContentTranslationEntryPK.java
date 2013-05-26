package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the videocontenttranslationentry database table.
 * 
 */
@Embeddable
public class VideoContentTranslationEntryPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "vice_id", unique = true, nullable = false)
	private Integer viceId;

	@Column(name = "cult_id", unique = true, nullable = false)
	private Integer cultId;

	@Column(name = "lang_id", unique = true, nullable = false)
	private Integer langId;

	public VideoContentTranslationEntryPK() {
	}

	public Integer getViceId() {
		return this.viceId;
	}

	public void setViceId(Integer viceId) {
		this.viceId = viceId;
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
		if (!(other instanceof VideoContentTranslationEntryPK)) {
			return false;
		}
		VideoContentTranslationEntryPK castOther = (VideoContentTranslationEntryPK) other;
		return this.viceId.equals(castOther.viceId)
				&& this.cultId.equals(castOther.cultId)
				&& this.langId.equals(castOther.langId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.viceId.hashCode();
		hash = hash * prime + this.cultId.hashCode();
		hash = hash * prime + this.langId.hashCode();

		return hash;
	}
}