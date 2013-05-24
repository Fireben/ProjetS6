package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the imagetype database table.
 * 
 */
@Entity
@Table(name = "internationalization.imagetype")
public class ImageType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "imty_id", unique = true, nullable = false)
	private Integer imtyId;

	// bi-directional many-to-one association to Textcontententry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name", nullable = false)
	private TextContentEntry textcontententry;

	public ImageType() {
	}

	public Integer getImtyId() {
		return this.imtyId;
	}

	public void setImtyId(Integer imtyId) {
		this.imtyId = imtyId;
	}

	public TextContentEntry getTextcontententry() {
		return this.textcontententry;
	}

	public void setTextcontententry(TextContentEntry textcontententry) {
		this.textcontententry = textcontententry;
	}

}