package educatus.server.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.shared.persist.dao.internationalization.Culture;
import educatus.shared.persist.dao.internationalization.Image;
import educatus.shared.persist.dao.internationalization.ImageContentEntry;
import educatus.shared.persist.dao.internationalization.ImageContentTranslationEntry;
import educatus.shared.persist.dao.internationalization.Language;
import educatus.shared.persist.dao.internationalization.TextContentEntry;
import educatus.shared.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.shared.persist.dao.internationalization.TextContentTranslationEntryPK;
import educatus.shared.persist.dao.internationalization.Video;
import educatus.shared.persist.dao.internationalization.VideoContentEntry;
import educatus.shared.persist.dao.internationalization.VideoContentTranslationEntry;

@Singleton
public class InternationalizationDao {
	
	@Inject
	private EntityManager entityManager;
	
	public Culture findCultureByCode(String code) throws Exception {
		
		List<?> resultList = entityManager.createNamedQuery(Culture.FIND_BY_CODE).setParameter(Culture.FIND_BY_CODE_PARAM_NAME, code).getResultList();
		
		if (resultList.size() == 1)
		{			
			return (Culture)resultList.get(0);
		} else {			
			throw new EntityNotFoundException();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Culture> findAllCulture() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(Culture.FIND_ALL).getResultList();		
		
		return (List<Culture>) resultList;
	}
	
	public Language findLanguageByCode(String code) throws Exception {
		
		List<?> resultList = entityManager.createNamedQuery(Language.FIND_BY_CODE).setParameter(Language.FIND_BY_CODE_PARAM_NAME, code).getResultList();
		
		if (resultList.size() == 1)
		{			
			return (Language)resultList.get(0);
		} else {			
			throw new EntityNotFoundException();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Language> findAllLanguage() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(Language.FIND_ALL).getResultList();		
		
		return (List<Language>) resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Image> findAllImage() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(Image.FIND_ALL).getResultList();		
		
		return (List<Image>) resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ImageContentEntry> findAllImageContentEntry() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(ImageContentEntry.FIND_ALL).getResultList();		
		
		return (List<ImageContentEntry>) resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ImageContentTranslationEntry> findAllImageContentTranslationEntry() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(ImageContentTranslationEntry.FIND_ALL).getResultList();		
		
		return (List<ImageContentTranslationEntry>) resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<TextContentEntry> findAllTextContentEntry() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(TextContentEntry.FIND_ALL).getResultList();		
		
		return (List<TextContentEntry>) resultList;
	}
		
	@SuppressWarnings("unchecked")
	public List<TextContentTranslationEntry> findAllTextContentTranslationEntry() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(TextContentTranslationEntry.FIND_ALL).getResultList();		
		
		return (List<TextContentTranslationEntry>) resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Video> findAllVideo() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(Video.FIND_ALL).getResultList();		
		
		return (List<Video>) resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<VideoContentEntry> findAllVideoContentEntry() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(VideoContentEntry.FIND_ALL).getResultList();		
		
		return (List<VideoContentEntry>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<VideoContentTranslationEntry> findAllVideoContentTranslationEntry() throws Exception {		
		
		List<?> resultList = entityManager.createNamedQuery(VideoContentTranslationEntry.FIND_ALL).getResultList();		
		
		return (List<VideoContentTranslationEntry>) resultList;
	}
	
	public TextContentTranslationEntry getTextContentTranslationEntry(TextContentTranslationEntryPK id)
	{
		
		return null;
	}
	
}
