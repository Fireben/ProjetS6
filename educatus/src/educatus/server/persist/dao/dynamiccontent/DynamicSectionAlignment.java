package educatus.server.persist.dao.dynamiccontent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import educatus.server.persist.dao.internationalization.TextContentEntry;

@Entity
@Table(name = "dynamic_content.dynamicsectionalignment")
public class DynamicSectionAlignment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "dysa_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name", nullable = false, insertable = false, updatable = false)
	private TextContentEntry name;

	// bi-directional many-to-one association to DynamicSection
	@OneToMany(mappedBy = "DynamicSectionAlignment")
	private List<DynamicSection> dynamicSections;

	public DynamicSectionAlignment() {
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

	public void setDynamicSections(List<DynamicSection> dynamicSections) {
		this.dynamicSections = dynamicSections;
	}

	public TextContentEntry getName() {
		return name;
	}

	public void setName(TextContentEntry name) {
		this.name = name;
	}

}