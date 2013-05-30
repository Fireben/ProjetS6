package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dynamic_content.dynamiccontent")
public class DynamicContent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "dyco_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to DynamicSection
	@OneToMany(mappedBy = "DynamicContent")
	private List<DynamicSection> dynamicSections;

	public DynamicContent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<DynamicSection> getDynamicSections() {
		return this.dynamicSections;
	}

	public void setDynamicsections(List<DynamicSection> dynamicSections) {
		this.dynamicSections = dynamicSections;
	}

}