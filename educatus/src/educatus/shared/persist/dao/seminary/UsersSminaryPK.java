package educatus.shared.persist.dao.seminary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the userseminary database table.
 * 
 */
@Embeddable
public class UsersSminaryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", unique=true, nullable=false)
	private Integer userId;

	@Column(name="semi_id", unique=true, nullable=false)
	private Integer semiId;

    public UsersSminaryPK() {
    }
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSemiId() {
		return this.semiId;
	}
	public void setSemiId(Integer semiId) {
		this.semiId = semiId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsersSminaryPK)) {
			return false;
		}
		UsersSminaryPK castOther = (UsersSminaryPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.semiId.equals(castOther.semiId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.semiId.hashCode();
		
		return hash;
    }
}