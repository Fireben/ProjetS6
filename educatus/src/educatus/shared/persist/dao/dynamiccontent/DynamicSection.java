package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name = "DYST_Type")
@Table(name = "dynamic_content.vdynamicsection")
public class DynamicSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "dyse_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "dyse_sequence", nullable = false)
	private Integer sequence;

	// bi-directional many-to-one association to DynamicContent
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dyco_container", nullable = false)
	private DynamicContent dynamicContent;

	// bi-directional many-to-one association to DynamicSectionAlignment
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dysa_alignment", nullable = false)
	private DynamicSectionAlignment dynamicSectionAlignment;

	// bi-directional many-to-one association to DynamicSectionType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dyst_type", unique = true, nullable = false)
	private DynamicSectionType dynamicSectionType;

	public DynamicSection() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public DynamicContent getDynamicContent() {
		return this.dynamicContent;
	}

	public void setDynamicContent(DynamicContent dynamicContent) {
		this.dynamicContent = dynamicContent;
	}

	public DynamicSectionAlignment getDynamicSectionAlignment() {
		return this.dynamicSectionAlignment;
	}

	public void setDynamicSectionAlignment(
			DynamicSectionAlignment dynamicSectionAlignment) {
		this.dynamicSectionAlignment = dynamicSectionAlignment;
	}

	public DynamicSectionType getDynamicSectionType() {
		return this.dynamicSectionType;
	}

	public void setDynamicSectionType(DynamicSectionType dynamicSectionType) {
		this.dynamicSectionType = dynamicSectionType;
	}
}