package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dynamicsectionalignment database table.
 * 
 */
@Entity
@Table(name="dynamic_content.dynamicsectionalignment")
public class DynamicSectionAlignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dysa_id", unique=true, nullable=false)
	private Integer dysaId;

	@Column(name="tece_name", nullable=false)
	private Integer teceName;

	//bi-directional many-to-one association to DynamicSection
	@OneToMany(mappedBy="dynamicsectionalignment")
	private List<DynamicSection> dynamicsections;

    public DynamicSectionAlignment() {
    }

	public Integer getDysaId() {
		return this.dysaId;
	}

	public void setDysaId(Integer dysaId) {
		this.dysaId = dysaId;
	}

	public Integer getTeceName() {
		return this.teceName;
	}

	public void setTeceName(Integer teceName) {
		this.teceName = teceName;
	}

	public List<DynamicSection> getDynamicsections() {
		return this.dynamicsections;
	}

	public void setDynamicsections(List<DynamicSection> dynamicsections) {
		this.dynamicsections = dynamicsections;
	}
	
}