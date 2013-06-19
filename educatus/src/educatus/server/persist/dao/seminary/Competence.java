package educatus.server.persist.dao.seminary;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="seminary.competence")
public class Competence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "competence_comp_id", sequenceName = "seminary.competence_comp_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competence_comp_id")
	@Column(name="comp_id", unique=true, nullable=false)
	private Integer id;

	@Column(name="comp_datecreated", nullable=false)
	private Timestamp dateCreated;

	@Column(name="comp_datedeleted")
	private Timestamp dateDeleted;

	@Column(name="comp_datemodified", nullable=false)
	private Timestamp dateModified;

	@Column(name="comp_deleteflag", nullable=false)
	private Boolean deleteFlag;

	@Column(name="tece_description", nullable=false)
	private Integer teceDescription;

	@Column(name="tece_name", nullable=false)
	private Integer teceName;

	@Column(name="user_author", nullable=false)
	private Integer userAuthor;

	@Column(name="user_lasteditor", nullable=false)
	private Integer userLasteditor;

	//bi-directional many-to-many association to Seminary
	@ManyToMany(mappedBy="competences")
	private List<Seminary> seminaries;

    public Competence() {
    }

	public Integer getCompId() {
		return this.id;
	}

	public void setCompId(Integer compId) {
		this.id = compId;
	}

	public Timestamp getCompDatecreated() {
		return this.dateCreated;
	}

	public void setCompDatecreated(Timestamp compDatecreated) {
		this.dateCreated = compDatecreated;
	}

	public Timestamp getCompDatedeleted() {
		return this.dateDeleted;
	}

	public void setCompDatedeleted(Timestamp compDatedeleted) {
		this.dateDeleted = compDatedeleted;
	}

	public Timestamp getCompDatemodified() {
		return this.dateModified;
	}

	public void setCompDatemodified(Timestamp compDatemodified) {
		this.dateModified = compDatemodified;
	}

	public Boolean getCompDeleteflag() {
		return this.deleteFlag;
	}

	public void setCompDeleteflag(Boolean compDeleteflag) {
		this.deleteFlag = compDeleteflag;
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

	public Integer getUserAuthor() {
		return this.userAuthor;
	}

	public void setUserAuthor(Integer userAuthor) {
		this.userAuthor = userAuthor;
	}

	public Integer getUserLasteditor() {
		return this.userLasteditor;
	}

	public void setUserLasteditor(Integer userLasteditor) {
		this.userLasteditor = userLasteditor;
	}

	public List<Seminary> getSeminaries() {
		return this.seminaries;
	}

	public void setSeminaries(List<Seminary> seminaries) {
		this.seminaries = seminaries;
	}
	
}