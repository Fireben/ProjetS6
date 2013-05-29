package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "internationalization.imagetype")
public class ImageType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "IMAGETYPE_IMTY_ID_GENERATOR", sequenceName = "internationalization.imagetype_imty_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGETYPE_IMTY_ID_GENERATOR")
	@Column(name = "imty_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to Textcontententry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name", nullable = false)
	private TextContentEntry textContentEntry;

	public ImageType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TextContentEntry getTextContentEntry() {
		return this.textContentEntry;
	}

	public void setTextContentEntry(TextContentEntry textContentEntry) {
		this.textContentEntry = textContentEntry;
	}
}