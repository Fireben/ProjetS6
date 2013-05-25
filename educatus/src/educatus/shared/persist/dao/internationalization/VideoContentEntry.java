package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the videocontententry database table.
 * 
 */
@Entity
@Table(name = "internationalization.videocontententry")
public class VideoContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "VIDEOCONTENTENTRY_VICE_ID_GENERATOR", sequenceName = "internationalization.videocontententry_vice_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDEOCONTENTENTRY_VICE_ID_GENERATOR")
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