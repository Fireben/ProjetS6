package educatus.server.persist.dao.dynamiccontent;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import educatus.server.persist.dao.internationalization.TextContentEntry;

@Entity
@DiscriminatorValue("1")
// DYNAMIC SECTION TYPE = 1
public class DynamicSectionText extends DynamicSection implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_text", nullable = false)
	private TextContentEntry text;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_title", nullable = false)
	private TextContentEntry title;

	public DynamicSectionText() {
	}

	public TextContentEntry getText() {
		return text;
	}

	public void setText(TextContentEntry text) {
		this.text = text;
	}

	public TextContentEntry getTitle() {
		return title;
	}

	public void setTitle(TextContentEntry title) {
		this.title = title;
	}
}