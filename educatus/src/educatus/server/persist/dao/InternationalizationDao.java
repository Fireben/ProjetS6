package educatus.server.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.shared.persist.dao.internationalization.Culture;
import educatus.shared.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.shared.persist.dao.internationalization.TextContentTranslationEntryPK;

@Singleton
public class InternationalizationDao {
	
	@Inject
	private EntityManager entityManager;
	
	public Culture getCultureByCode(String code) throws EntityNotFoundException, Exception {
		
		List<?> resultList = entityManager.createQuery("SELECT c FROM Culture c WHERE c.code=:code")
				.setParameter("code", code).getResultList();
		
		if (resultList.size() == 1)
		{			
			return (Culture)resultList.get(0);
		} else {			
			throw new EntityNotFoundException();
		}
	}
	
	public TextContentTranslationEntry getTextContentTranslationEntry(TextContentTranslationEntryPK id)
	{
		
		return null;
	}
	
}
