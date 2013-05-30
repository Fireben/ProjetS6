package educatus.server.persist.dao.achievement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the achievementexercice database table.
 * 
 */
@Embeddable
public class AchievementExercicePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "achi_id", unique = true, nullable = false)
	private Integer achiId;

	@Column(name = "exer_exercice", unique = true, nullable = false)
	private Integer exerExercice;

	public AchievementExercicePK() {
	}

	public Integer getAchiId() {
		return this.achiId;
	}

	public void setAchiId(Integer achiId) {
		this.achiId = achiId;
	}

	public Integer getExerExercice() {
		return this.exerExercice;
	}

	public void setExerExercice(Integer exerExercice) {
		this.exerExercice = exerExercice;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AchievementExercicePK)) {
			return false;
		}
		AchievementExercicePK castOther = (AchievementExercicePK) other;
		return this.achiId.equals(castOther.achiId)
				&& this.exerExercice.equals(castOther.exerExercice);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.achiId.hashCode();
		hash = hash * prime + this.exerExercice.hashCode();

		return hash;
	}
}