package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the dynamicsectiontype database table.
 * 
 */
@Entity
@Table(name="dynamic_content.dynamicsectiontype")
public class DynamicSectionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dyst_id", unique=true, nullable=false)
	private Integer dystId;

	@Column(name="tece_name", nullable=false)
	private Integer teceName;

    public DynamicSectionType() {
    }

	public Integer getDystId() {
		return this.dystId;
	}

	public void setDystId(Integer dystId) {
		this.dystId = dystId;
	}

	public Integer getTeceName() {
		return this.teceName;
	}

	public void setTeceName(Integer teceName) {
		this.teceName = teceName;
	}	
}