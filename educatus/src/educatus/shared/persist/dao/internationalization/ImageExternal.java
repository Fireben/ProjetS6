package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * The persistent class for the imageexternal database table.
 * 
 */
@Entity
@DiscriminatorValue("2")
public class ImageExternal extends Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "imex_url", nullable = false, length = 1024)
	private String url;

	public ImageExternal() {
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}