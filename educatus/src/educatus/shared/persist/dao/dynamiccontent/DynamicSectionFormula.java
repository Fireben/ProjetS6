package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dynamicsectionformula database table.
 * 
 */
@Entity
@Table(name="dynamic_content.dynamicsectionformula")
public class DynamicSectionFormula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dyse_id", unique=true, nullable=false)
	private Integer dyseId;

	@Column(name="dysf_formula", nullable=false, length=2147483647)
	private String dysfFormula;

    public DynamicSectionFormula() {
    }

	public Integer getDyseId() {
		return this.dyseId;
	}

	public void setDyseId(Integer dyseId) {
		this.dyseId = dyseId;
	}

	public String getDysfFormula() {
		return this.dysfFormula;
	}

	public void setDysfFormula(String dysfFormula) {
		this.dysfFormula = dysfFormula;
	}
}