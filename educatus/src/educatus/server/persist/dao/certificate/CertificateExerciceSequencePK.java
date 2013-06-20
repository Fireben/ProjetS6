package educatus.server.persist.dao.certificate;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class CertificateExerciceSequencePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "exer_id", unique = true, nullable = false)
	private Integer exerId;

	@Column(name = "cert_id", unique = true, nullable = false)
	private Integer certId;

	public CertificateExerciceSequencePK() {
	}

	public Integer getExerId() {
		return this.exerId;
	}

	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}

	public Integer getCertId() {
		return this.certId;
	}

	public void setCertId(Integer certId) {
		this.certId = certId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CertificateExerciceSequencePK)) {
			return false;
		}
		CertificateExerciceSequencePK castOther = (CertificateExerciceSequencePK) other;
		return this.exerId.equals(castOther.exerId)
				&& this.certId.equals(castOther.certId);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.exerId.hashCode();
		hash = hash * prime + this.certId.hashCode();

		return hash;
	}
}