package educatus.shared.dto.dynamiccontent;

import java.io.Serializable;

public class DynamicSectionFormulaContent extends AbstractDynamicSection
		implements Serializable {

	private static final long serialVersionUID = 717657519809992550L;
	
	private String formula;
	
	@Override
	public DynamicSectionType getSectionType() {
		return DynamicSectionType.FORMULA_SECTION;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
}
