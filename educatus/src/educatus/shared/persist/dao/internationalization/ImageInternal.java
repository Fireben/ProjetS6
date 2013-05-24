package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * The persistent class for the imageinternal database table.
 * 
 */
@Entity
@DiscriminatorValue("1")
public class ImageInternal extends Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "imin_content", nullable = false)
	private byte[] imagContent;

	@Column(name = "imin_name", nullable = false, length = 50)
	private String imagName;

	public ImageInternal() {
	}

	public byte[] getImagContent() {
		return this.imagContent;
	}

	public void setImagContent(byte[] imagContent) {
		this.imagContent = imagContent;
	}

	public String getImagName() {
		return this.imagName;
	}

	public void setImagName(String imagName) {
		this.imagName = imagName;
	}
}