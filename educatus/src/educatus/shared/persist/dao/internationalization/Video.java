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
@NamedQueries({ @NamedQuery(name = Video.FIND_ALL, query = "SELECT v FROM Video v") })
@Table(name = "internationalization.video")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "VIDEO.FIND_ALL";

	@Id
	@SequenceGenerator(name = "VIDEO_VIDE_ID_GENERATOR", sequenceName = "internationalization.video_vide_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VIDEO_VIDE_ID_GENERATOR")
	@Column(name = "vide_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "vide_url", nullable = false, length = 1024)
	private String url;

	// bi-directional many-to-one association to Videocontenttranslationentry
	@OneToMany(mappedBy = "video")
	private List<VideoContentTranslationEntry> videoContentTranslationEntries;

	public Video() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<VideoContentTranslationEntry> getVideoContentTranslationEntries() {
		return this.videoContentTranslationEntries;
	}

	public void setVideoContentTranslationEntries(
			List<VideoContentTranslationEntry> videoContentTranslationEntries) {
		this.videoContentTranslationEntries = videoContentTranslationEntries;
	}

	public VideoContentTranslationEntry addVideoContentTranslationEntry(
			VideoContentTranslationEntry videoContentTranslationEntry) {
		getVideoContentTranslationEntries().add(videoContentTranslationEntry);
		videoContentTranslationEntry.setVideo(this);

		return videoContentTranslationEntry;
	}

	public VideoContentTranslationEntry removeVideoContentTranslationEntry(
			VideoContentTranslationEntry videocontenttranslationentry) {
		getVideoContentTranslationEntries()
				.remove(videocontenttranslationentry);
		videocontenttranslationentry.setVideo(null);

		return videocontenttranslationentry;
	}

}