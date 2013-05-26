package educatus.shared.persist.dao.achievement;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the achievementseminary database table.
 * 
 */
@Entity
@Table(name="achievement.achievementseminary")
public class AchievementSeminary implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AchievementSeminaryPK id;

	//bi-directional many-to-one association to Achievement
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="achi_id", nullable=false, insertable=false, updatable=false)
	private Achievement achievement;

	// TODO Ajouter le liens vers Seminary
	
    public AchievementSeminary() {
    }

	public AchievementSeminaryPK getId() {
		return this.id;
	}

	public void setId(AchievementSeminaryPK id) {
		this.id = id;
	}
	
	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}
	
}