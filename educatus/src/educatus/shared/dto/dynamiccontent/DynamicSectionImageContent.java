package educatus.shared.dto.dynamiccontent;

import java.io.Serializable;

public class DynamicSectionImageContent extends AbstractDynamicSection implements Serializable {

	private static final long serialVersionUID = -5721041505698872988L;
	
	private String imageUrl;
	
	@Override
	public DynamicSectionType getSectionType() {
		return DynamicSectionType.IMAGE_SECTION;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
