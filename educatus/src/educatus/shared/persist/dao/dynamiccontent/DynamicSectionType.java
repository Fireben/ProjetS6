package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import educatus.shared.persist.dao.internationalization.TextContentEntry;

/**
 * The persistent class for the dynamicsectiontype database table.
 * 
 */
@Entity
@Table(name = "dynamic_content.dynamicsectiontype")
public class DynamicSectionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "dyst_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name", nullable = false, insertable = false, updatable = false)
	private TextContentEntry name;

	public DynamicSectionType() {
	}

	public Integer getId() {
		return this.id;
	} 

	public void setId(Integer id) {
		this.id = id;
	}

	public TextContentEntry getName() {
		return name;
	}

	public void setName(TextContentEntry name) {
		this.name = name;
	}
}