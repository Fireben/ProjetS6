package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the videocontenttranslationentry database table.
 * 
 */
@Entity
@Table(name = "internationalization.videocontenttranslationentry")
public class VideoContentTranslationEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VideoContentTranslationEntryPK id;

	// bi-directional many-to-one association to Culture
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cult_id", nullable = false, insertable = false, updatable = false)
	private Culture culture;

	// bi-directional many-to-one association to Language
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lang_id", nullable = false, insertable = false, updatable = false)
	private Language language;

	// bi-directional many-to-one association to Video
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vide_id", nullable = false)
	private Video video;

	// bi-directional many-to-one association to Videocontententry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vice_id", nullable = false, insertable = false, updatable = false)
	private VideoContentEntry videocontententry;

	public VideoContentTranslationEntry() {
	}

	public VideoContentTranslationEntryPK getId() {
		return this.id;
	}

	public void setId(VideoContentTranslationEntryPK id) {
		this.id = id;
	}

	public Culture getCulture() {
		return this.culture;
	}

	public void setCulture(Culture culture) {
		this.culture = culture;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public VideoContentEntry getVideocontententry() {
		return this.videocontententry;
	}

	public void setVideocontententry(VideoContentEntry videocontententry) {
		this.videocontententry = videocontententry;
	}

}