package educatus.server.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name = Culture.FIND_ALL, query = "SELECT c FROM Culture c"),
		@NamedQuery(name = Culture.FIND_BY_CODE, query = "SELECT c FROM Culture c WHERE c.code=:code") })
@Table(name = "internationalization.culture")
public class Culture implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "CULTURE.FIND_ALL";
	public static final String FIND_BY_CODE = "CULTURE.FIND_BY_CODE";
	public static final String FIND_BY_CODE_PARAM_NAME = "code";

	@Id
	@SequenceGenerator(name = "CULTURE_CULT_ID_GENERATOR", sequenceName = "internationalization.culture_cult_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CULTURE_CULT_ID_GENERATOR")
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