package educatus.server.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = TextContentTranslationEntry.FIND_ALL, query = "SELECT t FROM TextContentTranslationEntry t"),
		@NamedQuery(name = TextContentTranslationEntry.FIND_ALL_BY_TECE, query = "SELECT t FROM TextContentTranslationEntry t WHERE t.textContentEntry=:textContentEntry") })
@Table(name = "internationalization.textcontenttranslationentry")
public class TextContentTranslationEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "TEXT_CONTENT_TRANSLATION_ENTRY.FIND_ALL";
	public static final String FIND_ALL_BY_TECE = "TEXT_CONTENT_TRANSLATION_ENTRY.FIND_ALL_BY_TECE";
	public static final String FIND_ALL_BY_TECE_PARAM_NAME = "textContentEntry";

	@EmbeddedId
	private TextContentTranslationEntryPK id;

	@Column(name = "tcte_translation", nullable = false, length = 2147483647)
	private String tcteTranslation;

	// bi-directional many-to-one association to Culture
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cult_id", nullable = false, insertable = false, updatable = false)
	private Culture culture;

	// bi-directional many-to-one association to Language
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lang_id", nullable = false, insertable = false, updatable = false)
	private Language language;

	// bi-directional many-to-one association to Textcontententry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_id", nullable = false, insertable = false, updatable = false)
	private TextContentEntry textContentEntry;

	public TextContentTranslationEntry() {
	}

	public TextContentTranslationEntryPK getId() {
		return this.id;
	}

	public void setId(TextContentTranslationEntryPK id) {
		this.id = id;
	}

	public String getTcteTranslation() {
		return this.tcteTranslation;
	}

	public void setTcteTranslation(String tcteTranslation) {
		this.tcteTranslation = tcteTranslation;
	}

	public Culture getCulture() {
		return this.culture;
	}

	public void setCulture(Culture culture) {
		this.culture = culture;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public TextContentEntry getTextcontententry() {
		return this.textContentEntry;
	}

	public void setTextcontententry(TextContentEntry textcontententry) {
		this.textContentEntry = textcontententry;
	}

}