package educatus.shared.persist.dao.achievement;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the achievementseminary database table.
 * 
 */
@Embeddable
public class AchievementSeminaryPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "semi_id", unique = true, nullable = false)
	private Integer semiId;

	@Column(name = "achi_id", unique = true, nullable = false)
	private Integer achiId;

	public AchievementSeminaryPK() {
	}

	public Integer getSemiId() {
		return this.semiId;
	}

	public void setSemiId(Integer semiId) {
		this.semiId = semiId;
	}

	public Integer getAchiId() {
		return this.achiId;
	}

	public void setAchiId(Integer achiId) {
		this.achiId = achiId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AchievementSeminaryPK)) {
			return false;
		}
		AchievementSeminaryPK castOther = (AchievementSeminaryPK) other;
		return this.semiId.equals(castOther.semiId)
				&& this.achiId.equals(castOther.achiId);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.semiId.hashCode();
		hash = hash * prime + this.achiId.hashCode();

		return hash;
	}
}