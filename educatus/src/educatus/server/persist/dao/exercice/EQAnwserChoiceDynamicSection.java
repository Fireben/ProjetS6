package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "exercice.eqanwserchoicedynamicsection")
public class EQAnwserChoiceDynamicSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EQAnwserChoiceDynamicSectionPK id;

	// bi-directional many-to-one association to AnwserChoice
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "exqt_type", referencedColumnName = "exqt_type", nullable = false),
			@JoinColumn(name = "exqu_id", referencedColumnName = "exqu_id", nullable = false, insertable = false, updatable = false) })
	private AnwserChoice anwserchoice;

	public EQAnwserChoiceDynamicSection() {
	}

	public EQAnwserChoiceDynamicSectionPK getId() {
		return this.id;
	}

	public void setId(EQAnwserChoiceDynamicSectionPK id) {
		this.id = id;
	}

	public AnwserChoice getAnwserchoice() {
		return this.anwserchoice;
	}

	public void setAnwserchoice(AnwserChoice anwserchoice) {
		this.anwserchoice = anwserchoice;
	}

}