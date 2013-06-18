package educatus.shared.dto.seminary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;

public class SeminaryContent implements Serializable {

	private static final long serialVersionUID = 3375315024357286924L;

	private SeminaryCoreContent coreContent;

	private List<AbstractDynamicSection> dynamicSectionList = new ArrayList<AbstractDynamicSection>();

	public SeminaryCoreContent getCoreContent() {
		return coreContent;
	}

	public void setCoreContent(SeminaryCoreContent coreContent) {
		this.coreContent = coreContent;
	}

	public List<AbstractDynamicSection> getDynamicSectionList() {
		return dynamicSectionList;
	}

	public void setDynamicSectionList(List<AbstractDynamicSection> dynamicSectionList) {
		this.dynamicSectionList = dynamicSectionList;
	}
}
