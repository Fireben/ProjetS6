package educatus.shared.persist.dao.seminary;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userseminary database table.
 * 
 */
@Entity
@Table(name="seminary.userseminary")
public class UsersSminary implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsersSminaryPK id;

	//bi-directional many-to-one association to Seminary
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="semi_id", nullable=false, insertable=false, updatable=false)
	private Seminary seminary;

    public UsersSminary() {
    }

	public UsersSminaryPK getId() {
		return this.id;
	}

	public void setId(UsersSminaryPK id) {
		this.id = id;
	}
	
	public Seminary getSeminary() {
		return this.seminary;
	}

	public void setSeminary(Seminary seminary) {
		this.seminary = seminary;
	}
	
}