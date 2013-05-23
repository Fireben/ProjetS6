package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class VideoContentEntry implements Serializable {

	private static final long serialVersionUID = 5941630195412817594L;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<VideoContentTranslationEntry> videoContentTranslationEntries;
}
