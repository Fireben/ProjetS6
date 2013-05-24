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
 * The persistent class for the language database table.
 * 
 */
@Entity
@Table(name = "internationalization.language")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "LANGUAGE_LANG_GENERATOR", sequenceName = "internationalization.language_lang_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LANGUAGE_LANG_GENERATOR")
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