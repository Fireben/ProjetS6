package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the anwserchoice database table.
 * 
 */
@Entity
@Table(name="exercice.anwserchoice")
public class AnwserChoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AnwserChoicePK id;

	@Column(name="dyco_id", nullable=false)
	private Integer dycoId;

	//bi-directional many-to-one association to EQAnwserChoiceDynamicSection
	@OneToMany(mappedBy="anwserchoice")
	private List<EQAnwserChoiceDynamicSection> eqanwserchoicedynamicsections;

    public AnwserChoice() {
    }

	public AnwserChoicePK getId() {
		return this.id;
	}

	public void setId(AnwserChoicePK id) {
		this.id = id;
	}
	
	public Integer getDycoId() {
		return this.dycoId;
	}

	public void setDycoId(Integer dycoId) {
		this.dycoId = dycoId;
	}
	
	public List<EQAnwserChoiceDynamicSection> getEqanwserchoicedynamicsections() {
		return this.eqanwserchoicedynamicsections;
	}

	public void setEqanwserchoicedynamicsections(List<EQAnwserChoiceDynamicSection> eqanwserchoicedynamicsections) {
		this.eqanwserchoicedynamicsections = eqanwserchoicedynamicsections;
	}
	
}