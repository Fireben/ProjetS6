package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="internationalization.ImageContentEntry")
public class ImageContentEntry implements Serializable {

	private static final long serialVersionUID = -7148364128610515371L;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ImageContentTranslationEntry> imageContentTranslationEntries;
}
