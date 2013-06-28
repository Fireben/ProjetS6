package educatus.server.persist.dao.dynamiccontent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("4")
// DYNAMIC SECTION TYPE = 4
public class DynamicSectionFormula extends DynamicSection implements Serializable {

	private static final long serialVersionUID = -9202759163770617301L;
	public static final int DYNAMIC_SECTION_TYPE_VALUE = 4;
	
	@Column(name = "dysf_formula", nullable = false, length = 2147483647)
	private String formula;

	public DynamicSectionFormula() {
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
}