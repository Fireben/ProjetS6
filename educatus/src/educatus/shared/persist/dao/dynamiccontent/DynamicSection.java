package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the dynamicsection database table.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "DYST_Type")
@Table(name="dynamic_content.dynamicsection")
public class DynamicSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dyse_id", unique=true, nullable=false)
	private Integer dyseId;

	@Column(name="dyse_sequence", nullable=false)
	private Integer dyseSequence;

	//bi-directional many-to-one association to DynamicContent
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dyco_container", nullable=false)
	private DynamicContent dynamiccontent;

	//bi-directional many-to-one association to DynamicSectionAlignment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dysa_alignment", nullable=false)
	private DynamicSectionAlignment dynamicsectionalignment;

	//bi-directional many-to-one association to DynamicSectionType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dyst_type", unique=true, nullable=false)
	private DynamicSectionType dynamicsectiontype;

    public DynamicSection() {
    }

	public Integer getDyseId() {
		return this.dyseId;
	}

	public void setDyseId(Integer dyseId) {
		this.dyseId = dyseId;
	}

	public Integer getDyseSequence() {
		return this.dyseSequence;
	}

	public void setDyseSequence(Integer dyseSequence) {
		this.dyseSequence = dyseSequence;
	}

	public DynamicContent getDynamiccontent() {
		return this.dynamiccontent;
	}

	public void setDynamiccontent(DynamicContent dynamiccontent) {
		this.dynamiccontent = dynamiccontent;
	}
	
	public DynamicSectionAlignment getDynamicsectionalignment() {
		return this.dynamicsectionalignment;
	}

	public void setDynamicsectionalignment(DynamicSectionAlignment dynamicsectionalignment) {
		this.dynamicsectionalignment = dynamicsectionalignment;
	}
	
	public DynamicSectionType getDynamicsectiontype() {
		return this.dynamicsectiontype;
	}

	public void setDynamicsectiontype(DynamicSectionType dynamicsectiontype) {
		this.dynamicsectiontype = dynamicsectiontype;
	}	
}