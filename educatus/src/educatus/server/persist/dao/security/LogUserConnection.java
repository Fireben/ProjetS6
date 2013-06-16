package educatus.server.persist.dao.security;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "security.loguserconnection")
public class LogUserConnection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "LOGUSERCONNECTION_LOUC_ID_GENERATOR", sequenceName = "security.loguserconnection_louc_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOGUSERCONNECTION_LOUC_ID_GENERATOR")
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