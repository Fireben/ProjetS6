package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class AnwserChoicePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "exqu_id", unique = true, nullable = false)
	private Integer exquId;

	@Column(name = "exqt_type", unique = true, nullable = false)
	private Integer exqtType;

	public AnwserChoicePK() {
	}

	public Integer getExquId() {
		return this.exquId;
	}

	public void setExquId(Integer exquId) {
		this.exquId = exquId;
	}

	public Integer getExqtType() {
		return this.exqtType;
	}

	public void setExqtType(Integer exqtType) {
		this.exqtType = exqtType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AnwserChoicePK)) {
			return false;
		}
		AnwserChoicePK castOther = (AnwserChoicePK) other;
		return this.exquId.equals(castOther.exquId)
				&& this.exqtType.equals(castOther.exqtType);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.exquId.hashCode();
		hash = hash * prime + this.exqtType.hashCode();

		return hash;
	}
}