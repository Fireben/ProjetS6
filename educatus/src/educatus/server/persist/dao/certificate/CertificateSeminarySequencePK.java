package educatus.server.persist.dao.certificate;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class CertificateSeminarySequencePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "semi_id", unique = true, nullable = false)
	private Integer semiId;

	@Column(name = "cert_id", unique = true, nullable = false)
	private Integer certId;

	public CertificateSeminarySequencePK() {
	}

	public Integer getSemiId() {
		return this.semiId;
	}

	public void setSemiId(Integer semiId) {
		this.semiId = semiId;
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
		if (!(other instanceof CertificateSeminarySequencePK)) {
			return false;
		}
		CertificateSeminarySequencePK castOther = (CertificateSeminarySequencePK) other;
		return this.semiId.equals(castOther.semiId)
				&& this.certId.equals(castOther.certId);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.semiId.hashCode();
		hash = hash * prime + this.certId.hashCode();

		return hash;
	}
}