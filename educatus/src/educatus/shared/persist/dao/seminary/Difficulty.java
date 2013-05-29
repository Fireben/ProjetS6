package educatus.shared.persist.dao.seminary;

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

import educatus.shared.persist.dao.internationalization.TextContentEntry;

@Entity
@Table(name = "seminary.difficulty")
public class Difficulty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "diff_value", unique = true, nullable = false)
	private Integer difficultyValue;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name", nullable = false, insertable = false, updatable = false)
	private TextContentEntry name;

	// bi-directional many-to-one association to Seminary
	@OneToMany(mappedBy = "difficulty")
	private List<Seminary> seminaries;

	public Difficulty() {
	}

	public Integer getDifficultyValue() {
		return this.difficultyValue;
	}

	public void setDifficultyValue(Integer difficultyValue) {
		this.difficultyValue = difficultyValue;
	}

	public List<Seminary> getSeminaries() {
		return this.seminaries;
	}

	public void setSeminaries(List<Seminary> seminaries) {
		this.seminaries = seminaries;
	}

	public TextContentEntry getName() {
		return name;
	}

	public void setName(TextContentEntry name) {
		this.name = name;
	}
}