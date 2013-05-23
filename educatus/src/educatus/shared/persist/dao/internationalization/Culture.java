package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Culture implements Serializable {
	
	private static final long serialVersionUID = 6906290633139198946L;
	
	private int 	id;
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
