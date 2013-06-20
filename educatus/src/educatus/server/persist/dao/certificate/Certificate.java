package educatus.server.persist.dao.certificate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.seminary.Difficulty;

@Entity
@Table(name = "certificate.certificate")
public class Certificate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cert_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diff_value", nullable = false, insertable = false, updatable = false)
	private Difficulty difficulty;
	
	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_description", nullable = false, insertable = true, updatable = true)
	private TextContentEntry description;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name", nullable = false, insertable = true, updatable = true)
	private TextContentEntry name;

	// bi-directional many-to-one association to CertificatePrerequisite
	@OneToMany(mappedBy = "certificate1")
	private List<CertificatePrerequisite> certificateprerequisites1;

	// bi-directional many-to-one association to CertificatePrerequisite
	@OneToMany(mappedBy = "certificate2")
	private List<CertificatePrerequisite> certificateprerequisites2;

	// bi-directional many-to-one association to CertificateSequence
	@OneToMany(mappedBy = "certificate")
	private List<CertificateSequence> certificatesequences;

	public Certificate() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public TextContentEntry getDescription() {
		return this.description;
	}

	public void seDescription(TextContentEntry description) {
		this.description = description;
	}

	public TextContentEntry getName() {
		return this.name;
	}

	public void setName(TextContentEntry name) {
		this.name = name;
	}

	public List<CertificatePrerequisite> getCertificateprerequisites1() {
		return this.certificateprerequisites1;
	}

	public void setCertificateprerequisites1(
			List<CertificatePrerequisite> certificateprerequisites1) {
		this.certificateprerequisites1 = certificateprerequisites1;
	}

	public List<CertificatePrerequisite> getCertificateprerequisites2() {
		return this.certificateprerequisites2;
	}

	public void setCertificateprerequisites2(
			List<CertificatePrerequisite> certificateprerequisites2) {
		this.certificateprerequisites2 = certificateprerequisites2;
	}

	public List<CertificateSequence> getCertificatesequences() {
		return this.certificatesequences;
	}

	public void setCertificatesequences(
			List<CertificateSequence> certificatesequences) {
		this.certificatesequences = certificatesequences;
	}

}