package educatus.shared.persist.dao.certificate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the certificatesequencetype database table.
 * 
 */
@Entity
@Table(name="certificate.certificatesequencetype")
public class CertificateSequenceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cest_id", unique=true, nullable=false)
	private Integer cestId;

	@Column(name="tece_description", nullable=false)
	private Integer teceDescription;

	@Column(name="tece_name", nullable=false)
	private Integer teceName;

	//bi-directional many-to-one association to CertificateSequence
	@OneToMany(mappedBy="certificatesequencetype")
	private List<CertificateSequence> certificatesequences;

    public CertificateSequenceType() {
    }

	public Integer getCestId() {
		return this.cestId;
	}

	public void setCestId(Integer cestId) {
		this.cestId = cestId;
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

	public List<CertificateSequence> getCertificatesequences() {
		return this.certificatesequences;
	}

	public void setCertificatesequences(List<CertificateSequence> certificatesequences) {
		this.certificatesequences = certificatesequences;
	}
	
}