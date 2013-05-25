package educatus.shared.persist.dao.seminary;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the competence database table.
 * 
 */
@Entity
@Table(name="seminary.competence")
public class Competence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="comp_id", unique=true, nullable=false)
	private Integer compId;

	@Column(name="comp_datecreated", nullable=false)
	private Timestamp compDatecreated;

	@Column(name="comp_datedeleted")
	private Timestamp compDatedeleted;

	@Column(name="comp_datemodified", nullable=false)
	private Timestamp compDatemodified;

	@Column(name="comp_deleteflag", nullable=false)
	private Boolean compDeleteflag;

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
		return this.compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Timestamp getCompDatecreated() {
		return this.compDatecreated;
	}

	public void setCompDatecreated(Timestamp compDatecreated) {
		this.compDatecreated = compDatecreated;
	}

	public Timestamp getCompDatedeleted() {
		return this.compDatedeleted;
	}

	public void setCompDatedeleted(Timestamp compDatedeleted) {
		this.compDatedeleted = compDatedeleted;
	}

	public Timestamp getCompDatemodified() {
		return this.compDatemodified;
	}

	public void setCompDatemodified(Timestamp compDatemodified) {
		this.compDatemodified = compDatemodified;
	}

	public Boolean getCompDeleteflag() {
		return this.compDeleteflag;
	}

	public void setCompDeleteflag(Boolean compDeleteflag) {
		this.compDeleteflag = compDeleteflag;
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