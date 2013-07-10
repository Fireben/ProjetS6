package educatus.shared.dto.dynamiccontent;

import java.io.Serializable;

public class DynamicSectionPDFContent extends AbstractDynamicSection implements Serializable {

	private static final long serialVersionUID = -923155627721881857L;

	private String PDFUrl;
	
	@Override
	public DynamicSectionType getSectionType() {
		return DynamicSectionType.PDF_SECTION;
	}

	public String getPDFUrl() {
		return PDFUrl;
	}

	public void setPDFUrl(String pDFUrl) {
		PDFUrl = pDFUrl;
	}
}
