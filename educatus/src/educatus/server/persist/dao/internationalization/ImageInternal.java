package educatus.server.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class ImageInternal extends Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "imin_content", nullable = false)
	private byte[] imageContent;

	@Column(name = "imin_name", nullable = false, length = 50)
	private String imageName;

	public ImageInternal() {
	}

	public byte[] getImageContent() {
		return this.imageContent;
	}

	public void setImageContent(byte[] imageContent) {
		this.imageContent = imageContent;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}