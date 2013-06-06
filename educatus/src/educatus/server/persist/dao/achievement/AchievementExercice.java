package educatus.server.persist.dao.achievement;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the achievementexercice database table.
 * 
 */
@Entity
@Table(name="achievement.achievementexercice")
public class AchievementExercice implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AchievementExercicePK id;

	//bi-directional many-to-one association to Achievement
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="achi_id", nullable=false, insertable=false, updatable=false)
	private Achievement achievement;

	// TODO, revoir cette classe, c'est seulement une table d'intersection
	
    public AchievementExercice() {
    }

	public AchievementExercicePK getId() {
		return this.id;
	}

	public void setId(AchievementExercicePK id) {
		this.id = id;
	}
	
	public Achievement getAchievement() {
		return this.achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}
	
}