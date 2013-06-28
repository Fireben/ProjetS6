package educatus.server.persist.dao.dynamiccontent;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.internationalization.VideoContentEntry;

@Entity
@DiscriminatorValue("3")
// DYNAMIC SECTION TYPE = 3
public class DynamicSectionVideo extends DynamicSection implements Serializable {

	private static final long serialVersionUID = 7988700524253032200L;
	public static final int DYNAMIC_SECTION_TYPE_VALUE = 3;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	// changed tece_description to tece_descriptionvideo
	@JoinColumn(name = "tece_descriptionvideo", nullable = false)
	private TextContentEntry description;

	// bi-directional many-to-one association to VideoContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vice_video", nullable = false)
	private VideoContentEntry video;

	public DynamicSectionVideo() {
	}

	public TextContentEntry getDescription() {
		return description;
	}

	public void setDescription(TextContentEntry description) {
		this.description = description;
	}

	public VideoContentEntry getVideo() {
		return video;
	}

	public void setVideo(VideoContentEntry video) {
		this.video = video;
	}
}