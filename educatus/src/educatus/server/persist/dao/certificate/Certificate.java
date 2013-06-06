package educatus.server.persist.dao.certificate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the certificate database table.
 * 
 */
@Entity
@Table(name="certificate.certificate")
public class Certificate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cert_id", unique=true, nullable=false)
	private Integer certId;

	@Column(name="diff_value", nullable=false)
	private Integer diffValue;

	@Column(name="tece_description", nullable=false)
	private Integer teceDescription;

	@Column(name="tece_name", nullable=false)
	private Integer teceName;

	//bi-directional many-to-one association to CertificatePrerequisite
	@OneToMany(mappedBy="certificate1")
	private List<CertificatePrerequisite> certificateprerequisites1;

	//bi-directional many-to-one association to CertificatePrerequisite
	@OneToMany(mappedBy="certificate2")
	private List<CertificatePrerequisite> certificateprerequisites2;

	//bi-directional many-to-one association to CertificateSequence
	@OneToMany(mappedBy="certificate")
	private List<CertificateSequence> certificatesequences;

    public Certificate() {
    }

	public Integer getCertId() {
		return this.certId;
	}

	public void setCertId(Integer certId) {
		this.certId = certId;
	}

	public Integer getDiffValue() {
		return this.diffValue;
	}

	public void setDiffValue(Integer diffValue) {
		this.diffValue = diffValue;
	}

	public Integer getTeceDescription() {
		return this.teceDescription;
	}

	public void setTeceDescription(Integer teceDescription) {
		this.teceDescription = teceDescription;
	}

	public Integer getTeceName() {
		return this.teceName;
	}

	public void setTeceName(Integer teceName) {
		this.teceName = teceName;
	}

	public List<CertificatePrerequisite> getCertificateprerequisites1() {
		return this.certificateprerequisites1;
	}

	public void setCertificateprerequisites1(List<CertificatePrerequisite> certificateprerequisites1) {
		this.certificateprerequisites1 = certificateprerequisites1;
	}
	
	public List<CertificatePrerequisite> getCertificateprerequisites2() {
		return this.certificateprerequisites2;
	}

	public void setCertificateprerequisites2(List<CertificatePrerequisite> certificateprerequisites2) {
		this.certificateprerequisites2 = certificateprerequisites2;
	}
	
	public List<CertificateSequence> getCertificatesequences() {
		return this.certificatesequences;
	}

	public void setCertificatesequences(List<CertificateSequence> certificatesequences) {
		this.certificatesequences = certificatesequences;
	}
	
}