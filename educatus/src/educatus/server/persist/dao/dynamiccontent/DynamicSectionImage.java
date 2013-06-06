package educatus.server.persist.dao.dynamiccontent;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import educatus.server.persist.dao.internationalization.ImageContentEntry;
import educatus.server.persist.dao.internationalization.TextContentEntry;

@Entity
@DiscriminatorValue("2")
// DYNAMIC SECTION TYPE = 2
public class DynamicSectionImage extends DynamicSection implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to ImageContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imce_image", nullable = false, insertable = false, updatable = false)
	private ImageContentEntry image;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	// changed tece_description to tece_descriptionimage
	@JoinColumn(name = "tece_descriptionimage", nullable = false, insertable = false, updatable = false)
	private TextContentEntry description;

	public DynamicSectionImage() {
	}

	public TextContentEntry getDescription() {
		return description;
	}

	public void setDescription(TextContentEntry description) {
		this.description = description;
	}

	public ImageContentEntry getImage() {
		return image;
	}

	public void setImage(ImageContentEntry image) {
		this.image = image;
	}
}