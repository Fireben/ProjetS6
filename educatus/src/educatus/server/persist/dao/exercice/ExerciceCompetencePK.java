package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the exercicecompetence database table.
 * 
 */
@Embeddable
public class ExerciceCompetencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="exer_id", unique=true, nullable=false)
	private Integer exerId;

	@Column(name="comp_id", unique=true, nullable=false)
	private Integer compId;

    public ExerciceCompetencePK() {
    }
	public Integer getExerId() {
		return this.exerId;
	}
	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}
	public Integer getCompId() {
		return this.compId;
	}
	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ExerciceCompetencePK)) {
			return false;
		}
		ExerciceCompetencePK castOther = (ExerciceCompetencePK)other;
		return 
			this.exerId.equals(castOther.exerId)
			&& this.compId.equals(castOther.compId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.exerId.hashCode();
		hash = hash * prime + this.compId.hashCode();
		
		return hash;
    }
}