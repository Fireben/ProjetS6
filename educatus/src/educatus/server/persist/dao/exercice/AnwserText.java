package educatus.server.persist.dao.exercice;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import educatus.server.persist.dao.internationalization.TextContentEntry;

@Entity
@DiscriminatorValue("2")
public class AnwserText extends Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_value", nullable = false, insertable = true, updatable = true)
	private TextContentEntry value;

	public AnwserText() {
	}

	public TextContentEntry getValue() {
		return this.value;
	}

	public void setValue(TextContentEntry value) {
		this.value = value;
	}
}