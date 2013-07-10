package educatus.server.persist.dao.exercice;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;


@Entity
@Inheritance
@DiscriminatorColumn(name = "EXQT_TYPE", discriminatorType = DiscriminatorType.INTEGER)
// Vue Answer
@Table(name = "exercice.vanswer")
public abstract class Answer implements Serializable {

	private static final long serialVersionUID = -782926302852300985L;
	
	

}
