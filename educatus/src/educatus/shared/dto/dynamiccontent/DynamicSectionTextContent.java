package educatus.shared.dto.dynamiccontent;

import java.io.Serializable;

public class DynamicSectionTextContent extends AbstractDynamicSection implements Serializable {

	private static final long serialVersionUID = 7808422881108095945L;

	private String title;
	private String text;
	
	@Override
	public DynamicSectionType getSectionType() {
		return DynamicSectionType.TEXT_SECTION;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
