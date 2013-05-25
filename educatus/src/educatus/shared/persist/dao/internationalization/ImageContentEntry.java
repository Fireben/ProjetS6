package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the imagecontententry database table.
 * 
 */
@Entity
@Table(name = "internationalization.imagecontententry")
public class ImageContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "IMAGECONTENTENTRY_IMCE_ID_GENERATOR", sequenceName = "internationalization.imagecontententry_imce_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGECONTENTENTRY_IMCE_ID_GENERATOR")
	@Column(name = "imce_id", unique = true, nullable = false)
	private Integer imceId;

	// bi-directional many-to-one association to Imagecontenttranslationentry
	@OneToMany(mappedBy = "imagecontententry")
	private List<ImageContentTranslationEntry> imagecontenttranslationentries;

	public ImageContentEntry() {
	}

	public Integer getImceId() {
		return this.imceId;
	}

	public void setImceId(Integer imceId) {
		this.imceId = imceId;
	}

	public List<ImageContentTranslationEntry> getImagecontenttranslationentries() {
		return this.imagecontenttranslationentries;
	}

	public void setImagecontenttranslationentries(
			List<ImageContentTranslationEntry> imagecontenttranslationentries) {
		this.imagecontenttranslationentries = imagecontenttranslationentries;
	}

	public ImageContentTranslationEntry addImagecontenttranslationentry(
			ImageContentTranslationEntry imagecontenttranslationentry) {
		getImagecontenttranslationentries().add(imagecontenttranslationentry);
		imagecontenttranslationentry.setImagecontententry(this);

		return imagecontenttranslationentry;
	}

	public ImageContentTranslationEntry removeImagecontenttranslationentry(
			ImageContentTranslationEntry imagecontenttranslationentry) {
		getImagecontenttranslationentries()
				.remove(imagecontenttranslationentry);
		imagecontenttranslationentry.setImagecontententry(null);

		return imagecontenttranslationentry;
	}

}