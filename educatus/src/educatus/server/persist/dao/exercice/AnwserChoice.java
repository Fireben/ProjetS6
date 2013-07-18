package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;

@Entity
@DiscriminatorValue("3")
public class AnwserChoice extends Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AnwserChoicePK pk;

	// bi-directional many-to-one association to DynamicContent
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dyco_id", nullable = false, insertable = true, updatable = true)
	private DynamicContent dynamicContent;

	@OneToMany(mappedBy = "anwserChoice")
	private List<EQAnwserChoiceDynamicSection> eqAnwserChoiceDynamicSection;

	public List<EQAnwserChoiceDynamicSection> getEqAnwserChoiceDynamicSection() {
		return eqAnwserChoiceDynamicSection;
	}

	public void setEqAnwserChoiceDynamicSection(List<EQAnwserChoiceDynamicSection> eqAnwserChoiceDynamicSection) {
		this.eqAnwserChoiceDynamicSection = eqAnwserChoiceDynamicSection;
	}

	public AnwserChoice() {
	}

	public AnwserChoicePK getPK() {
		return this.pk;
	}

	public void setPK(AnwserChoicePK pk) {
		this.pk = pk;
	}

	public DynamicContent getDynamicContent() {
		return this.dynamicContent;
	}

	public void setDynamicContent(DynamicContent dynamicContent) {
		this.dynamicContent = dynamicContent;
	}
}