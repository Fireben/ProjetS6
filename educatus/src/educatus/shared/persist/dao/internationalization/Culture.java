package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the culture database table.
 * 
 */
@Entity
@Table(name = "internationalization.culture")
public class Culture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CULTURE_CULTID_GENERATOR", sequenceName = "internationalization.culture_cult_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CULTURE_CULTID_GENERATOR")
	@Column(name = "cult_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "cult_code", nullable = false, length = 2)
	private String code;

	public Culture() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}