package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the image database table.
 * 
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "IMTY_TYPE")
// Vue Image
@Table(name = "internationalization.vimage")
public abstract class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "imag_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to Imagetype
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imty_type", unique = true, nullable = false)
	private ImageType type;

	// bi-directional many-to-one association to Imagecontenttranslationentry
	@OneToMany(mappedBy = "image")
	private List<ImageContentTranslationEntry> imageContentTranslationEntries;

	public Image() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImageType getType() {
		return this.type;
	}

	public void setType(ImageType type) {
		this.type = type;
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
		imagecontenttranslationentry.setImage(this);

		return imagecontenttranslationentry;
	}

	public ImageContentTranslationEntry removeImageContentTranslationEntry(
			ImageContentTranslationEntry imagecontenttranslationentry) {
		getImageContentTranslationEntries()
				.remove(imagecontenttranslationentry);
		imagecontenttranslationentry.setImage(null);

		return imagecontenttranslationentry;
	}
}