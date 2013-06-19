package educatus.server.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.internationalization.Culture;
import educatus.server.persist.dao.internationalization.Image;
import educatus.server.persist.dao.internationalization.ImageContentEntry;
import educatus.server.persist.dao.internationalization.ImageContentTranslationEntry;
import educatus.server.persist.dao.internationalization.ImageContentTranslationEntryPK;
import educatus.server.persist.dao.internationalization.ImageExternal;
import educatus.server.persist.dao.internationalization.ImageType;
import educatus.server.persist.dao.internationalization.Language;
import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntryPK;
import educatus.server.persist.dao.internationalization.Video;
import educatus.server.persist.dao.internationalization.VideoContentEntry;
import educatus.server.persist.dao.internationalization.VideoContentTranslationEntry;

@Singleton
public class InternationalizationDao {
	@Inject
	private EntityManager entityManager;

	public Culture findCultureByCode(String code) throws Exception {
		List<?> resultList = entityManager.createNamedQuery(Culture.FIND_BY_CODE)
				.setParameter(Culture.FIND_BY_CODE_PARAM_NAME, code)
				.getResultList();

		if (resultList.size() == 1) {
			return (Culture) resultList.get(0);
		} else {
			throw new EntityNotFoundException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Culture> findAllCulture() throws Exception {
		List<?> resultList = entityManager.createNamedQuery(Culture.FIND_ALL)
				.getResultList();

		return (List<Culture>) resultList;
	}

	public Language findLanguageByCode(String code) throws Exception {
		List<?> resultList = entityManager.createNamedQuery(Language.FIND_BY_CODE)
				.setParameter(Language.FIND_BY_CODE_PARAM_NAME, code)
				.getResultList();

		if (resultList.size() == 1) {
			return (Language) resultList.get(0);
		} else {
			throw new EntityNotFoundException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Language> findAllLanguage() throws Exception {
		List<?> resultList = entityManager.createNamedQuery(Language.FIND_ALL)
				.getResultList();

		return (List<Language>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Image> findAllImage() throws Exception {

		List<?> resultList = entityManager.createNamedQuery(Image.FIND_ALL)
				.getResultList();

		return (List<Image>) resultList;
	}

	public ImageExternal findImageById(Integer id) {
		ImageExternal imageExternal = null;
		try {
			imageExternal = (ImageExternal) entityManager.find(Image.class, id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		return imageExternal;
	}

	@SuppressWarnings("unchecked")
	public List<ImageContentEntry> findAllImageContentEntry() throws Exception {

		List<?> resultList = entityManager.createNamedQuery(ImageContentEntry.FIND_ALL)
				.getResultList();

		return (List<ImageContentEntry>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<ImageContentTranslationEntry> findAllImageContentTranslationEntry() throws Exception {

		List<?> resultList = entityManager.createNamedQuery(ImageContentTranslationEntry.FIND_ALL)
				.getResultList();

		return (List<ImageContentTranslationEntry>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<TextContentEntry> findAllTextContentEntry() throws Exception {

		List<?> resultList = entityManager.createNamedQuery(TextContentEntry.FIND_ALL)
				.getResultList();

		return (List<TextContentEntry>) resultList;
	}

	public TextContentTranslationEntry findTextContentTranslationEntryById(Integer languageId, Integer cultureId, Integer textContentEntryId) {
		TextContentTranslationEntry textContentTranslationEntry;
		try {
			TextContentTranslationEntryPK contentEntryPk = new TextContentTranslationEntryPK(languageId, cultureId, textContentEntryId);
			textContentTranslationEntry = entityManager.find(TextContentTranslationEntry.class, contentEntryPk);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		return textContentTranslationEntry;
	}

	@SuppressWarnings("unchecked")
	public List<TextContentTranslationEntry> findAllTextContentTranslationEntry() throws Exception {

		List<?> resultList = entityManager.createNamedQuery(TextContentTranslationEntry.FIND_ALL)
				.getResultList();

		return (List<TextContentTranslationEntry>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Video> findAllVideo() throws Exception {

		List<?> resultList = entityManager.createNamedQuery(Video.FIND_ALL)
				.getResultList();

		return (List<Video>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<VideoContentEntry> findAllVideoContentEntry() throws Exception {
		List<?> resultList = entityManager.createNamedQuery(VideoContentEntry.FIND_ALL)
				.getResultList();

		return (List<VideoContentEntry>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<VideoContentTranslationEntry> findAllVideoContentTranslationEntry() throws Exception {
		List<?> resultList = entityManager.createNamedQuery(VideoContentTranslationEntry.FIND_ALL)
				.getResultList();

		return (List<VideoContentTranslationEntry>) resultList;
	}

	public TextContentTranslationEntry insertTextContentTranslationEntry(String languageCode, String cultureCode, String translation) throws Exception {

		Language language = findLanguageByCode(languageCode);
		Culture culture = findCultureByCode(cultureCode);

		TextContentEntry tece = new TextContentEntry();
		entityManager.persist(tece);

		TextContentTranslationEntry tcte = new TextContentTranslationEntry();

		// Create a primary key
		TextContentTranslationEntryPK pk = new TextContentTranslationEntryPK();
		pk.setLanguageId(language.getId());
		pk.setCultureId(culture.getId());
		pk.setTextContentEntryId(tece.getId());

		// Assign primary key and objects
		tcte.setId(pk);
		tcte.setCulture(culture);
		tcte.setLanguage(language);
		tcte.setTextcontententry(tece);
		tcte.setTcteTranslation(translation);

		// Insert Object
		entityManager.persist(tcte);

		return tcte;
	}

	public TextContentTranslationEntry insertTextContentTranslationEntry(String languageCode, String cultureCode, String translation, int textContentEntryId) throws Exception {
		Language language = findLanguageByCode(languageCode);
		Culture culture = findCultureByCode(cultureCode);
		TextContentEntry tece = entityManager.find(TextContentEntry.class, textContentEntryId);

		TextContentTranslationEntry tcte = new TextContentTranslationEntry();

		// Create a primary key
		TextContentTranslationEntryPK pk = new TextContentTranslationEntryPK();
		pk.setLanguageId(language.getId());
		pk.setCultureId(culture.getId());
		pk.setTextContentEntryId(tece.getId());

		// Assign primary key and objects
		tcte.setId(pk);
		tcte.setCulture(culture);
		tcte.setLanguage(language);
		tcte.setTextcontententry(tece);
		tcte.setTcteTranslation(translation);

		// Insert Object
		entityManager.persist(tcte);

		return tcte;
	}
	
	public ImageContentTranslationEntry insertImageTranslationEntry(String languageCode, String cultureCode, int imageId) throws Exception{

		Language language = findLanguageByCode(languageCode);
		Culture culture = findCultureByCode(cultureCode);

		ImageContentEntry imce = new ImageContentEntry();
		entityManager.persist(imce);
		
		Image image = entityManager.find(Image.class, imageId);

		ImageContentTranslationEntry imte = new ImageContentTranslationEntry();

		// Create a primary key
		ImageContentTranslationEntryPK pk = new ImageContentTranslationEntryPK();
		pk.setLanguageId(language.getId());
		pk.setCultureId(culture.getId());
		pk.setImageContentEntryId(imce.getId());

		// Assign primary key and objects
		imte.setId(pk);
		imte.setCulture(culture);
		imte.setLanguage(language);
		imte.setImagecontententry(imce);
		imte.setImage(image);

		// Insert Object
		entityManager.persist(imte);

		return imte;		
	}

	public ImageExternal insertImageExternal(String externalUrl) throws Exception {

		ImageType externalType = entityManager.find(ImageType.class, 2);

		ImageExternal imageExternal = new ImageExternal();
		imageExternal.setUrl(externalUrl);
		imageExternal.setType(externalType);

		// Insert Object
		entityManager.persist(imageExternal);

		return imageExternal;
	}
}
