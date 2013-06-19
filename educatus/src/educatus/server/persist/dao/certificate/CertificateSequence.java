package educatus.server.persist.dao.certificate;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CEST_Type")
@Table(name = "certificate.certificatesequence")
public abstract class CertificateSequence implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CertificateSequencePK certificateSequencePK;

	// bi-directional many-to-one association to Certificate
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cert_id", nullable = false, insertable = false, updatable = false)
	private Certificate certificate;

	// bi-directional many-to-one association to CertificateSequenceType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cest_type", unique = true, nullable = false)
	private CertificateSequenceType certificatesequencetype;

	public CertificateSequence() {
	}

	public CertificateSequencePK getCertificateSequencePK() {
		return this.certificateSequencePK;
	}

	public void setCertificateSequencePK(CertificateSequencePK id) {
		this.certificateSequencePK = id;
	}

	public Certificate getCertificate() {
		return this.certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public CertificateSequenceType getCertificatesequencetype() {
		return this.certificatesequencetype;
	}

	public void setCertificatesequencetype(
			CertificateSequenceType certificatesequencetype) {
		this.certificatesequencetype = certificatesequencetype;
	}

}