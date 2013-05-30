package educatus.server.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VideoContentTranslationEntryPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "vice_id", unique = true, nullable = false)
	private Integer videoContentEntryId;

	@Column(name = "cult_id", unique = true, nullable = false)
	private Integer cultureId;

	@Column(name = "lang_id", unique = true, nullable = false)
	private Integer languageId;

	public VideoContentTranslationEntryPK() {
	}

	public Integer getVideoContentEntryId() {
		return this.videoContentEntryId;
	}

	public void setVideoContentEntryId(Integer videoContentEntryId) {
		this.videoContentEntryId = videoContentEntryId;
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
		if (!(other instanceof VideoContentTranslationEntryPK)) {
			return false;
		}
		VideoContentTranslationEntryPK castOther = (VideoContentTranslationEntryPK) other;
		return this.videoContentEntryId.equals(castOther.videoContentEntryId)
				&& this.cultureId.equals(castOther.cultureId)
				&& this.languageId.equals(castOther.languageId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.videoContentEntryId.hashCode();
		hash = hash * prime + this.cultureId.hashCode();
		hash = hash * prime + this.languageId.hashCode();

		return hash;
	}
}