package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "exercice.anwsernumeric")
public class AnwserNumeric implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "exqu_id", unique = true, nullable = false)
	private Integer exquId;

	@Column(name = "annu_value", nullable = false)
	private Integer annuValue;

	public AnwserNumeric() {
	}

	public Integer getExquId() {
		return this.exquId;
	}

	public void setExquId(Integer exquId) {
		this.exquId = exquId;
	}

	public Integer getAnnuValue() {
		return this.annuValue;
	}

	public void setAnnuValue(Integer annuValue) {
		this.annuValue = annuValue;
	}
}