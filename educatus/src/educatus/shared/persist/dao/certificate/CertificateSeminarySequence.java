package educatus.shared.persist.dao.certificate;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the certificateseminarysequence database table.
 * 
 */
@Entity
@Table(name="certificate.certificateseminarysequence")
public class CertificateSeminarySequence extends CertificateSequence implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CertificateSeminarySequencePK certificateSeminarySequencePK;

    public CertificateSeminarySequence() {
    }

	public CertificateSeminarySequencePK getCertificateSeminarySequencePK() {
		return this.certificateSeminarySequencePK;
	}

	public void setCertificateSeminarySequencePK(CertificateSeminarySequencePK id) {
		this.certificateSeminarySequencePK = id;
	}
}