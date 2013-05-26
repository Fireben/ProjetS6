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
 * The persistent class for the imagecontententry database table.
 * 
 */
@Entity
@Table(name = "internationalization.imagecontententry")
public class ImageContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "IMAGECONTENTENTRY_IMCE_ID_GENERATOR", sequenceName = "internationalization.imagecontententry_imce_id_seq", allocationSize = 1)
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