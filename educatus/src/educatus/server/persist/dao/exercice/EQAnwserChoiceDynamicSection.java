package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

import educatus.server.persist.dao.dynamiccontent.DynamicSection;

@Entity
@Table(name = "exercice.eqanwserchoicedynamicsection")
public class EQAnwserChoiceDynamicSection implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EQAnwserChoiceDynamicSectionPK id;

	// bi-directional many-to-one association to AnwserChoice
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "exqt_type", referencedColumnName = "exqt_type", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "exqu_id", referencedColumnName = "exqu_id", nullable = false, insertable = false, updatable = false) })
	private AnwserChoice anwserChoice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dyse_id", nullable = false, insertable = false, updatable = false)
	private DynamicSection dynamicSection;

	public EQAnwserChoiceDynamicSection() {
	}

	public EQAnwserChoiceDynamicSectionPK getId() {
		return this.id;
	}

	public void setId(EQAnwserChoiceDynamicSectionPK id) {
		this.id = id;
	}

	public AnwserChoice getAnwserchoice() {
		return this.anwserChoice;
	}

	public void setAnwserchoice(AnwserChoice anwserchoice) {
		this.anwserChoice = anwserchoice;
	}

	public DynamicSection getDynamicSection() {
		return dynamicSection;
	}

	public void setDynamicSection(DynamicSection dynamicSection) {
		this.dynamicSection = dynamicSection;
	}

}