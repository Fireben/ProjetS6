package educatus.shared.dto.dynamiccontent;

import java.io.Serializable;

public class DynamicSectionAlignment implements Serializable {

	private static final long serialVersionUID = 5969652842808175366L;

	public static enum AlignmentEnum {
		LEFT, 
		RIGHT, 
		CENTER
	}

	private AlignmentEnum alignmentEnum;
	private String alignmentName;

	public AlignmentEnum getAlignmentEnum() {
		return alignmentEnum;
	}

	public void setAlignmentEnum(AlignmentEnum alignmentEnum) {
		this.alignmentEnum = alignmentEnum;
	}

	public String getAlignmentName() {
		return alignmentName;
	}

	public void setAlignmentName(String alignmentName) {
		this.alignmentName = alignmentName;
	}

}
