package educatus.shared.persist.dao.certificate;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the certificateexercicesequence database table.
 * 
 */
@Entity
@Table(name="certificate.certificateexercicesequence")
public class CertificateExerciceSequence implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CertificateExerciceSequencePK certificateExerciceSequencePK;

    public CertificateExerciceSequence() {
    }

	public CertificateExerciceSequencePK getCertificateExerciceSequencePK() {
		return this.certificateExerciceSequencePK;
	}

	public void setCertificateExerciceSequencePK(CertificateExerciceSequencePK id) {
		this.certificateExerciceSequencePK = id;
	}	
}