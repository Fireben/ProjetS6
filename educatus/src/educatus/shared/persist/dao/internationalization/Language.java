package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="internationalization.Language")
public class Language implements Serializable {

	private static final long serialVersionUID = 7965310762634036402L;

	@Id
    @GeneratedValue
	@Column(name="LANG_Id", nullable=false)
	private int 	id;

	@Column(name="LANG_Code", nullable=false)
	private String 	code;
		
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}	
}
