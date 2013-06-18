package educatus.shared.dto.seminary;

import java.io.Serializable;
import java.util.ArrayList;

public class SeminaryHomeListingContent implements Serializable {

	private static final long serialVersionUID = 6160737635292640612L;

	private CategoryCoreContent commonParent = null;
	private ArrayList<SeminaryCoreContent> seminariesChildren = new ArrayList<SeminaryCoreContent>();

	public CategoryCoreContent getCommonParent() {
		return commonParent;
	}

	public void setCommonParent(CategoryCoreContent commonParent) {
		this.commonParent = commonParent;
	}

	public ArrayList<SeminaryCoreContent> getSeminariesChildren() {
		return seminariesChildren;
	}

	public void setSeminariesChildren(ArrayList<SeminaryCoreContent> seminariesChildren) {
		this.seminariesChildren = seminariesChildren;
	}
}
