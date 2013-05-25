package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the textcontententry database table.
 * 
 */
@Entity
@Table(name = "internationalization.textcontententry")
public class TextContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "IMAGECONTENTENTRY_IMCE_ID_GENERATOR", sequenceName = "internationalization.textcontententry_tece_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGECONTENTENTRY_IMCE_ID_GENERATOR")
	@Column(name = "tece_id", unique = true, nullable = false)
	private Integer teceId;

	// bi-directional many-to-one association to Textcontenttranslationentry
	@OneToMany(mappedBy = "textcontententry")
	private List<TextContentTranslationEntry> textcontenttranslationentries;

	public TextContentEntry() {
	}

	public Integer getTeceId() {
		return this.teceId;
	}

	public void setTeceId(Integer teceId) {
		this.teceId = teceId;
	}

	public List<TextContentTranslationEntry> getTextcontenttranslationentries() {
		return this.textcontenttranslationentries;
	}

	public void setTextcontenttranslationentries(
			List<TextContentTranslationEntry> textcontenttranslationentries) {
		this.textcontenttranslationentries = textcontenttranslationentries;
	}

	public TextContentTranslationEntry addTextcontenttranslationentry(
			TextContentTranslationEntry textcontenttranslationentry) {
		getTextcontenttranslationentries().add(textcontenttranslationentry);
		textcontenttranslationentry.setTextcontententry(this);

		return textcontenttranslationentry;
	}

	public TextContentTranslationEntry removeTextcontenttranslationentry(
			TextContentTranslationEntry textcontenttranslationentry) {
		getTextcontenttranslationentries().remove(textcontenttranslationentry);
		textcontenttranslationentry.setTextcontententry(null);

		return textcontenttranslationentry;
	}

}