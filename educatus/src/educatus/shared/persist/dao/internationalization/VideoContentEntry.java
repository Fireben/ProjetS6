package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = VideoContentEntry.FIND_ALL, query = "SELECT v FROM VideoContentEntry v") })
@Table(name = "internationalization.videocontententry")
public class VideoContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "VIDEO_CONTENT_ENTRY.FIND_ALL";

	@Id
	@SequenceGenerator(name = "VIDEOCONTENTENTRY_VICE_ID_GENERATOR", sequenceName = "internationalization.videocontententry_vice_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDEOCONTENTENTRY_VICE_ID_GENERATOR")
	@Column(name = "vice_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to Videocontenttranslationentry
	@OneToMany(mappedBy = "VideoContentEntry")
	private List<VideoContentTranslationEntry> videoContentTranslationEntries;

	public VideoContentEntry() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<VideoContentTranslationEntry> getVideoContentTranslationEntries() {
		return this.videoContentTranslationEntries;
	}

	public void setVideoContentTranslationEntries(
			List<VideoContentTranslationEntry> videocontenttranslationentries) {
		this.videoContentTranslationEntries = videocontenttranslationentries;
	}

	public VideoContentTranslationEntry addVideoContentTranslationEntry(
			VideoContentTranslationEntry videoContentTranslationEntry) {
		getVideoContentTranslationEntries().add(videoContentTranslationEntry);
		videoContentTranslationEntry.setVideocontententry(this);

		return videoContentTranslationEntry;
	}

	public VideoContentTranslationEntry removeVideoContentTranslationEntry(
			VideoContentTranslationEntry videoContentTranslationEntry) {
		getVideoContentTranslationEntries()
				.remove(videoContentTranslationEntry);
		videoContentTranslationEntry.setVideocontententry(null);

		return videoContentTranslationEntry;
	}

}