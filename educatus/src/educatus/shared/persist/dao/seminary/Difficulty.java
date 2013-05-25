package educatus.shared.persist.dao.seminary;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the difficulty database table.
 * 
 */
@Entity
@Table(name="seminary.difficulty")
public class Difficulty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="diff_value", unique=true, nullable=false)
	private Integer diffValue;

	@Column(name="tece_name", nullable=false)
	private Integer teceName;

	//bi-directional many-to-one association to Seminary
	@OneToMany(mappedBy="difficulty")
	private List<Seminary> seminaries;

    public Difficulty() {
    }

	public Integer getDiffValue() {
		return this.diffValue;
	}

	public void setDiffValue(Integer diffValue) {
		this.diffValue = diffValue;
	}

	public Integer getTeceName() {
		return this.teceName;
	}

	public void setTeceName(Integer teceName) {
		this.teceName = teceName;
	}

	public List<Seminary> getSeminaries() {
		return this.seminaries;
	}

	public void setSeminaries(List<Seminary> seminaries) {
		this.seminaries = seminaries;
	}
	
}