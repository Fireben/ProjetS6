package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="internationalization.Video")
public class Video implements Serializable {

	private static final long serialVersionUID = -8563787337941866089L;

	@Id
    @GeneratedValue
	@Column(name="VIDE_Id", nullable=false)
	private int 	id;

	@Column(name="VIDE_Url", nullable=false)
	private String 	url;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
