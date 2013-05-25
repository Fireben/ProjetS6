package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dynamiccontent database table.
 * 
 */
@Entity
@Table(name="dynamic_content.dynamiccontent")
public class DynamicContent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dyco_id", unique=true, nullable=false)
	private Integer dycoId;

	//bi-directional many-to-one association to DynamicSection
	@OneToMany(mappedBy="dynamiccontent")
	private List<DynamicSection> dynamicsections;

    public DynamicContent() {
    }

	public Integer getDycoId() {
		return this.dycoId;
	}

	public void setDycoId(Integer dycoId) {
		this.dycoId = dycoId;
	}

	public List<DynamicSection> getDynamicsections() {
		return this.dynamicsections;
	}

	public void setDynamicsections(List<DynamicSection> dynamicsections) {
		this.dynamicsections = dynamicsections;
	}
	
}