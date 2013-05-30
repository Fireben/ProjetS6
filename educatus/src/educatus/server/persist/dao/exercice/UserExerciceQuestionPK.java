package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the userexercicequestion database table.
 * 
 */
@Embeddable
public class UserExerciceQuestionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", unique=true, nullable=false)
	private Integer userId;

	@Column(name="exqu_id", unique=true, nullable=false)
	private Integer exquId;

    public UserExerciceQuestionPK() {
    }
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getExquId() {
		return this.exquId;
	}
	public void setExquId(Integer exquId) {
		this.exquId = exquId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserExerciceQuestionPK)) {
			return false;
		}
		UserExerciceQuestionPK castOther = (UserExerciceQuestionPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.exquId.equals(castOther.exquId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.exquId.hashCode();
		
		return hash;
    }
}