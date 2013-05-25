package educatus.shared.persist.dao.certificate;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the certificatesequence database table.
 * 
 */
@Embeddable
public class CertificateSequencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cert_id", unique=true, nullable=false)
	private Integer certId;

	@Column(name="cese_sequence", unique=true, nullable=false)
	private Integer ceseSequence;

    public CertificateSequencePK() {
    }
	public Integer getCertId() {
		return this.certId;
	}
	public void setCertId(Integer certId) {
		this.certId = certId;
	}
	public Integer getCeseSequence() {
		return this.ceseSequence;
	}
	public void setCeseSequence(Integer ceseSequence) {
		this.ceseSequence = ceseSequence;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CertificateSequencePK)) {
			return false;
		}
		CertificateSequencePK castOther = (CertificateSequencePK)other;
		return 
			this.certId.equals(castOther.certId)
			&& this.ceseSequence.equals(castOther.ceseSequence);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.certId.hashCode();
		hash = hash * prime + this.ceseSequence.hashCode();
		
		return hash;
    }
}