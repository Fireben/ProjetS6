package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.dynamiccontent.DynamicSection;

@Entity
@DiscriminatorValue("3")
public class AnwserChoice extends Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to DynamicContent
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dyco_id", nullable = false, insertable = true, updatable = true)
	private DynamicContent dynamicContent;
	
	 @OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
	 @JoinTable(
	   name = "exercice.EQAnwserChoiceDynamicSection", 
	   joinColumns = @JoinColumn(name = "exqu_id"), 
	   inverseJoinColumns = @JoinColumn(name = "dyse_id")
	 )
	private List<DynamicSection> eqAnwserChoiceDynamicSection;

	public AnwserChoice() {
	}

	public DynamicContent getDynamicContent() {
		return this.dynamicContent;
	}

	public void setDynamicContent(DynamicContent dynamicContent) {
		this.dynamicContent = dynamicContent;
	}
	
	public List<DynamicSection> getEqAnwserChoiceDynamicSection() {
		return eqAnwserChoiceDynamicSection;
	}

	public void setEqAnwserChoiceDynamicSection(List<DynamicSection> eqAnwserChoiceDynamicSection) {
		this.eqAnwserChoiceDynamicSection = eqAnwserChoiceDynamicSection;
	}
}