package educatus.shared.persist.dao.security;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the loguserconnection database table.
 * 
 */
@Entity
@Table(name = "security.loguserconnection")
public class LogUserConnection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "louc_id")
	private Integer id;

	@Column(name = "louc_attemptsuccess")
	private Boolean attemptSuccess;

	@Column(name = "louc_timestamp")
	private Timestamp timestamp;

	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public LogUserConnection() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAttemptSuccess() {
		return this.attemptSuccess;
	}

	public void setAttemptSuccess(Boolean attemptSuccess) {
		this.attemptSuccess = attemptSuccess;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}