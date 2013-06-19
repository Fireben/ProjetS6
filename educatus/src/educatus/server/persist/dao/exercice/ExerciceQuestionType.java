package educatus.server.persist.dao.exercice;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exercicequestiontype")
public class ExerciceQuestionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "exqt_id", unique = true, nullable = false)
	private Integer exqtId;

	@Column(name = "tece_description", nullable = false)
	private Integer teceDescription;

	@Column(name = "tece_name", nullable = false)
	private Integer teceName;

	public ExerciceQuestionType() {
	}

	public Integer getExqtId() {
		return this.exqtId;
	}

	public void setExqtId(Integer exqtId) {
		this.exqtId = exqtId;
	}

	public Integer getTeceDescription() {
		return this.teceDescription;
	}

	public void setTeceDescription(Integer teceDescription) {
		this.teceDescription = teceDescription;
	}

	public Integer getTeceName() {
		return this.teceName;
	}

	public void setTeceName(Integer teceName) {
		this.teceName = teceName;
	}
}