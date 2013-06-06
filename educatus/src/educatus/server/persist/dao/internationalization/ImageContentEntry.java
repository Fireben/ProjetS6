package educatus.server.persist.dao.internationalization;

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
@Table(name = "internationalization.imagecontententry")
@NamedQueries({ @NamedQuery(name = ImageContentEntry.FIND_ALL, query = "SELECT i FROM ImageContentEntry i") })
public class ImageContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "IMAGE_CONTENT_ENTRY.FIND_ALL";

	@Id
	@SequenceGenerator(name = "IMAGECONTENTENTRY_IMCE_ID_GENERATOR", sequenceName = "internationalization.imagecontententry_imce_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGECONTENTENTRY_IMCE_ID_GENERATOR")
	@Column(name = "imce_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to Imagecontenttranslationentry
	@OneToMany(mappedBy = "imagecontententry")
	private List<ImageContentTranslationEntry> imageContentTranslationEntries;

	public ImageContentEntry() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ImageContentTranslationEntry> getImageContentTranslationEntries() {
		return this.imageContentTranslationEntries;
	}

	public void setImageContentTranslationEntries(
			List<ImageContentTranslationEntry> imagecontenttranslationentries) {
		this.imageContentTranslationEntries = imagecontenttranslationentries;
	}

	public ImageContentTranslationEntry addImageContentTranslationEntry(
			ImageContentTranslationEntry imagecontenttranslationentry) {
		getImageContentTranslationEntries().add(imagecontenttranslationentry);
		imagecontenttranslationentry.setImagecontententry(this);

		return imagecontenttranslationentry;
	}

	public ImageContentTranslationEntry removeImageContentTranslationEntry(
			ImageContentTranslationEntry imagecontenttranslationentry) {
		getImageContentTranslationEntries()
				.remove(imagecontenttranslationentry);
		imagecontenttranslationentry.setImagecontententry(null);

		return imagecontenttranslationentry;
	}
}