package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the anwsertext database table.
 * 
 */
@Entity
@Table(name="exercice.anwsertext")
public class AnwserText implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="exqu_id", unique=true, nullable=false)
	private Integer exquId;

	@Column(name="tece_value", nullable=false)
	private Integer teceValue;

    public AnwserText() {
    }

	public Integer getExquId() {
		return this.exquId;
	}

	public void setExquId(Integer exquId) {
		this.exquId = exquId;
	}

	public Integer getTeceValue() {
		return this.teceValue;
	}

	public void setTeceValue(Integer teceValue) {
		this.teceValue = teceValue;
	}	
}