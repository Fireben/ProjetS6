package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="internationalization.Culture")
public class Culture implements Serializable {
	
	private static final long serialVersionUID = 6906290633139198946L;

	@Id
    @GeneratedValue
	@Column(name="CULT_Id", nullable=false)
	private int 	id;

	@Column(name="CULT_Code", nullable=false)
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
