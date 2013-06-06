package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the exercicecategory database table.
 * 
 */
@Embeddable
public class ExerciceCategoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="exer_id", unique=true, nullable=false)
	private Integer exerId;

	@Column(name="cate_id", unique=true, nullable=false)
	private Integer cateId;

    public ExerciceCategoryPK() {
    }
	public Integer getExerId() {
		return this.exerId;
	}
	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}
	public Integer getCateId() {
		return this.cateId;
	}
	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ExerciceCategoryPK)) {
			return false;
		}
		ExerciceCategoryPK castOther = (ExerciceCategoryPK)other;
		return 
			this.exerId.equals(castOther.exerId)
			&& this.cateId.equals(castOther.cateId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.exerId.hashCode();
		hash = hash * prime + this.cateId.hashCode();
		
		return hash;
    }
}