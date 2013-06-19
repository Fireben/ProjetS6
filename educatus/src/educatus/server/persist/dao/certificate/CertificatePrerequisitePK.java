package educatus.server.persist.dao.certificate;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class CertificatePrerequisitePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cert_parent", unique=true, nullable=false)
	private Integer certParent;

	@Column(name="cepr_sequence", unique=true, nullable=false)
	private Integer ceprSequence;

    public CertificatePrerequisitePK() {
    }
	public Integer getCertParent() {
		return this.certParent;
	}
	public void setCertParent(Integer certParent) {
		this.certParent = certParent;
	}
	public Integer getCeprSequence() {
		return this.ceprSequence;
	}
	public void setCeprSequence(Integer ceprSequence) {
		this.ceprSequence = ceprSequence;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CertificatePrerequisitePK)) {
			return false;
		}
		CertificatePrerequisitePK castOther = (CertificatePrerequisitePK)other;
		return 
			this.certParent.equals(castOther.certParent)
			&& this.ceprSequence.equals(castOther.ceprSequence);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.certParent.hashCode();
		hash = hash * prime + this.ceprSequence.hashCode();
		
		return hash;
    }
}