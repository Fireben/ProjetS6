package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class AnwserNumeric extends Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "annu_value", nullable = false)
	private Integer value;

	public AnwserNumeric() {
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}