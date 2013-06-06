package educatus.server.persist.dao.certificate;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the certificateprerequisite database table.
 * 
 */
@Entity
@Table(name="certificate.certificateprerequisite")
public class CertificatePrerequisite implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CertificatePrerequisitePK id;

	//bi-directional many-to-one association to Certificate
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cert_parent", nullable=false, insertable=false, updatable=false)
	private Certificate certificate1;

	//bi-directional many-to-one association to Certificate
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cert_child", nullable=false)
	private Certificate certificate2;

    public CertificatePrerequisite() {
    }

	public CertificatePrerequisitePK getId() {
		return this.id;
	}

	public void setId(CertificatePrerequisitePK id) {
		this.id = id;
	}
	
	public Certificate getCertificate1() {
		return this.certificate1;
	}

	public void setCertificate1(Certificate certificate1) {
		this.certificate1 = certificate1;
	}
	
	public Certificate getCertificate2() {
		return this.certificate2;
	}

	public void setCertificate2(Certificate certificate2) {
		this.certificate2 = certificate2;
	}
	
}