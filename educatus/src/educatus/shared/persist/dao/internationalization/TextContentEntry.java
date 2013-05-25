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
 * The persistent class for the textcontententry database table.
 * 
 */
@Entity
@Table(name = "internationalization.textcontententry")
public class TextContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TEXTCONTENTENTRY_TECE_ID_GENERATOR", sequenceName = "internationalization.textcontententry_tece_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEXTCONTENTENTRY_TECE_ID_GENERATOR")
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