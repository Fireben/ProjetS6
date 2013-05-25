package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the videocontententry database table.
 * 
 */
@Entity
@Table(name = "internationalization.videocontententry")
public class VideoContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "vice_id", unique = true, nullable = false)
	private Integer viceId;

	// bi-directional many-to-one association to Videocontenttranslationentry
	@OneToMany(mappedBy = "videocontententry")
	private List<VideoContentTranslationEntry> videocontenttranslationentries;

	public VideoContentEntry() {
	}

	public Integer getViceId() {
		return this.viceId;
	}

	public void setViceId(Integer viceId) {
		this.viceId = viceId;
	}

	public List<VideoContentTranslationEntry> getVideocontenttranslationentries() {
		return this.videocontenttranslationentries;
	}

	public void setVideocontenttranslationentries(
			List<VideoContentTranslationEntry> videocontenttranslationentries) {
		this.videocontenttranslationentries = videocontenttranslationentries;
	}

	public VideoContentTranslationEntry addVideocontenttranslationentry(
			VideoContentTranslationEntry videocontenttranslationentry) {
		getVideocontenttranslationentries().add(videocontenttranslationentry);
		videocontenttranslationentry.setVideocontententry(this);

		return videocontenttranslationentry;
	}

	public VideoContentTranslationEntry removeVideocontenttranslationentry(
			VideoContentTranslationEntry videocontenttranslationentry) {
		getVideocontenttranslationentries()
				.remove(videocontenttranslationentry);
		videocontenttranslationentry.setVideocontententry(null);

		return videocontenttranslationentry;
	}

}