package educatus.shared.dto.dynamiccontent;

import java.io.Serializable;

public abstract class AbstractDynamicSection implements Serializable {

	public static enum DynamicSectionType {
		FORMULA_SECTION, 
		IMAGE_SECTION, 
		VIDEO_SECTION, 
		TEXT_SECTION,
		PDF_SECTION
	}

	private static final long serialVersionUID = -5902916778158111997L;

	private int id;
	private int sequenceNumber;
	private DynamicSectionAlignment alignment;

	public abstract DynamicSectionType getSectionType();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public DynamicSectionAlignment getAlignment() {
		return alignment;
	}

	public void setAlignment(DynamicSectionAlignment alignment) {
		this.alignment = alignment;
	}

}
