package educatus.server.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance
@NamedQueries({ @NamedQuery(name = Image.FIND_ALL, query = "SELECT i FROM Image i") })
@DiscriminatorColumn(name = "IMTY_TYPE", discriminatorType = DiscriminatorType.INTEGER)
// Vue Image
@Table(name = "internationalization.vimage")
public abstract class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "IMAGE.FIND_ALL";

	@Id
	@SequenceGenerator(name = "IMAGE_IMAG_ID_GENERATOR", sequenceName = "internationalization.image_imag_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_IMAG_ID_GENERATOR")
	@Column(name = "imag_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to Imagetype
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imty_type", unique = true, nullable = false)
	private ImageType type;

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
}