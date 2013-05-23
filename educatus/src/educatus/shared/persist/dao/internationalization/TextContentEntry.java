package educatus.shared.persist.dao.internationalization;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

public class TextContentEntry implements Serializable {

	private static final long serialVersionUID = 4388922998941460004L;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<TextContentTranslationEntry> textContentTranslationEntries;	
}
