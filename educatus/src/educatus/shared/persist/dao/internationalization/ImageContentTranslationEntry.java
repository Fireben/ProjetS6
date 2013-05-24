package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the imagecontenttranslationentry database table.
 * 
 */
@Entity
@Table(name = "internationalization.imagecontenttranslationentry")
public class ImageContentTranslationEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ImageContentTranslationEntryPK id;

	// bi-directional many-to-one association to Culture
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cult_id", nullable = false, insertable = false, updatable = false)
	private Culture culture;

	// bi-directional many-to-one association to Language
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lang_id", nullable = false, insertable = false, updatable = false)
	private Language language;

	// bi-directional many-to-one association to Image
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imag_id", nullable = false)
	private Image image;

	// bi-directional many-to-one association to Imagecontententry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imce_id", nullable = false, insertable = false, updatable = false)
	private ImageContentEntry imagecontententry;

	public ImageContentTranslationEntry() {
	}

	public ImageContentTranslationEntryPK getId() {
		return this.id;
	}

	public void setId(ImageContentTranslationEntryPK id) {
		this.id = id;
	}

	public Culture getCulture() {
		return this.culture;
	}

	public void setCulture(Culture culture) {
		this.culture = culture;
	}

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ImageContentEntry getImagecontententry() {
		return this.imagecontententry;
	}

	public void setImagecontententry(ImageContentEntry imagecontententry) {
		this.imagecontententry = imagecontententry;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}