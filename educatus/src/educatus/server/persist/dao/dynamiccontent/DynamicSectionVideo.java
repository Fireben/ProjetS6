package educatus.server.persist.dao.dynamiccontent;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.internationalization.VideoContentEntry;

/**
 * The persistent class for the dynamicsectionvideo database table.
 * 
 */
@Entity
@DiscriminatorValue("3")
// DYNAMIC SECTION TYPE = 3
public class DynamicSectionVideo extends DynamicSection implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	// changed tece_description to tece_descriptionvideo
	@JoinColumn(name = "tece_descriptionvideo", nullable = false, insertable = false, updatable = false)
	private TextContentEntry description;

	// bi-directional many-to-one association to VideoContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vice_video", nullable = false, insertable = false, updatable = false)
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