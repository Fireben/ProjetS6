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
		@NamedQuery(name = Language.FIND_ALL, query = "SELECT l FROM Language l"),
		@NamedQuery(name = Language.FIND_BY_CODE, query = "SELECT l FROM Language l WHERE lower(l.code)=lower(:code)") })
@Table(name = "internationalization.language")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "LANGUAGE.FIND_ALL";
	public static final String FIND_BY_CODE = "LANGUAGE.FIND_BY_CODE";
	public static final String FIND_BY_CODE_PARAM_NAME = "code";

	@Id
	@SequenceGenerator(name = "LANGUAGE_LANG_ID_GENERATOR", sequenceName = "internationalization.language_lang_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANGUAGE_LANG_ID_GENERATOR")
	@Column(name = "lang_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "lang_code", nullable = false, length = 2)
	private String code;

	public Language() {
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

	public void setLangCode(String code) {
		this.code = code;
	}
}