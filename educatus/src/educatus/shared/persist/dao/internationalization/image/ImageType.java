package educatus.shared.persist.dao.internationalization.image;

import java.io.Serializable;

import educatus.shared.persist.dao.internationalization.TextContentEntry;

public class ImageType implements Serializable {

	private static final long serialVersionUID = -9118705636444688751L;
	
	private int id;
	private TextContentEntry name;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public TextContentEntry getName() {
		return name;
	}
	
	public void setName(TextContentEntry name) {
		this.name = name;
	}
}
