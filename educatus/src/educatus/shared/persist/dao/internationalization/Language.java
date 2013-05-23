package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

public class Language implements Serializable {

	private static final long serialVersionUID = 7965310762634036402L;
	
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
