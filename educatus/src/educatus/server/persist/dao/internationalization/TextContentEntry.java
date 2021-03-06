package educatus.server.persist.dao.internationalization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = TextContentEntry.FIND_ALL, query = "SELECT t FROM TextContentEntry t")})
@Table(name = "internationalization.textcontententry")
public class TextContentEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "TEXT_CONTENT_ENTRY.FIND_ALL";
	
	@Id
	@SequenceGenerator(name = "TEXTCONTENTENTRY_TECE_ID_GENERATOR", sequenceName = "internationalization.textcontententry_tece_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEXTCONTENTENTRY_TECE_ID_GENERATOR")
	@Column(name = "tece_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to Textcontenttranslationentry
	@OneToMany(mappedBy = "TextContentEntry", fetch = FetchType.LAZY)
	private List<TextContentTranslationEntry> textContentTranslationEntries = new ArrayList<TextContentTranslationEntry>();

	public TextContentEntry() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<TextContentTranslationEntry> getTextContentTranslationEntries() {
		return this.textContentTranslationEntries;
	}

	public void setTextContentTranslationEntries(
			List<TextContentTranslationEntry> textcontenttranslationentries) {
		this.textContentTranslationEntries = textcontenttranslationentries;
	}

	public TextContentTranslationEntry addTextContentTranslationEntry(
			TextContentTranslationEntry textcontenttranslationentry) {
		getTextContentTranslationEntries().add(textcontenttranslationentry);
		textcontenttranslationentry.setTextcontententry(this);

		return textcontenttranslationentry;
	}

	public TextContentTranslationEntry removeTextContentTranslationEntry(
			TextContentTranslationEntry textcontenttranslationentry) {
		getTextContentTranslationEntries().remove(textcontenttranslationentry);
		textcontenttranslationentry.setTextcontententry(null);

		return textcontenttranslationentry;
	}

}