package educatus.shared.dto.seminary;

import java.io.Serializable;

public class CategoryCoreContent implements Serializable {

	private static final long serialVersionUID = -1828187453368863687L;
	private int id;
	private String name;
	private String description;
	private String imageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}