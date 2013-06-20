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

@Entity
@Table(name = "certificate.certificatesequencetype")
public class CertificateSequenceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cest_id", unique = true, nullable = false)
	private Integer cestId;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_description", nullable = false, insertable = true, updatable = true)
	private TextContentEntry description;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name", nullable = false, insertable = true, updatable = true)
	private TextContentEntry name;

	// bi-directional many-to-one association to CertificateSequence
	@OneToMany(mappedBy = "certificatesequencetype")
	private List<CertificateSequence> certificatesequences;

	public CertificateSequenceType() {
	}

	public Integer getCestId() {
		return this.cestId;
	}

	public void setCestId(Integer cestId) {
		this.cestId = cestId;
	}

	public List<CertificateSequence> getCertificatesequences() {
		return this.certificatesequences;
	}

	public void setCertificatesequences(
			List<CertificateSequence> certificatesequences) {
		this.certificatesequences = certificatesequences;
	}

	public TextContentEntry getDescription() {
		return description;
	}

	public void setDescription(TextContentEntry description) {
		this.description = description;
	}

	public TextContentEntry getName() {
		return name;
	}

	public void setName(TextContentEntry name) {
		this.name = name;
	}
}