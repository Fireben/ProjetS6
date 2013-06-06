package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the eqanwserchoicedynamicsection database table.
 * 
 */
@Embeddable
public class EQAnwserChoiceDynamicSectionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="exqu_id", unique=true, nullable=false)
	private Integer exquId;

	@Column(name="dyse_id", unique=true, nullable=false)
	private Integer dyseId;

    public EQAnwserChoiceDynamicSectionPK() {
    }
	public Integer getExquId() {
		return this.exquId;
	}
	public void setExquId(Integer exquId) {
		this.exquId = exquId;
	}
	public Integer getDyseId() {
		return this.dyseId;
	}
	public void setDyseId(Integer dyseId) {
		this.dyseId = dyseId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EQAnwserChoiceDynamicSectionPK)) {
			return false;
		}
		EQAnwserChoiceDynamicSectionPK castOther = (EQAnwserChoiceDynamicSectionPK)other;
		return 
			this.exquId.equals(castOther.exquId)
			&& this.dyseId.equals(castOther.dyseId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.exquId.hashCode();
		hash = hash * prime + this.dyseId.hashCode();
		
		return hash;
    }
}