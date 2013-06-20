package educatus.server.businesslogic.uibuilder;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.InternationalizationUtility;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.internationalization.ImageExternal;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.server.persist.dao.seminary.Category;
import educatus.server.persist.dao.seminary.Difficulty;
import educatus.shared.dto.SeminaryEditorContent;
import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.dto.seminary.DifficultyContent;

@Singleton
public class SeminaryEditorContentFactory {

	/* Text */
	private static final int TITLE = -12000;
	private static final int DESCRIPTION = -12001;
	private static final int CONFIRM_BUTTON = -12002;
	private static final int CANCEL_BUTTON = -12003;
	private static final int BROWSE_BUTTON = -12004;
	private static final int CATEGORY = -12005;
	private static final int DIFFICULTY = -12006;
	private static final int PATH = -12007;

	@Inject
	private InternationalizationDao interDao;

	@Inject
	private SeminaryDao seminaryDao;

	public SeminaryEditorContent createSeminaryEditorContent(String culture, String language) {

		int cultureId;
		int languageId;
		try {
			cultureId = interDao.findCultureByCode(culture).getId();
			languageId = interDao.findLanguageByCode(language).getId();

		} catch (Exception e) {
			// TODO Manage Exceptions
			e.printStackTrace();
			return null;
		}

		SeminaryEditorContent seminaryEditorContent = new SeminaryEditorContent();

		TextContentTranslationEntry textContentTranslationEntry = null;
		String text = "";

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, TITLE);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		seminaryEditorContent.setTitleText(text);

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, DESCRIPTION);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		seminaryEditorContent.setDescriptionText(text);

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, CONFIRM_BUTTON);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		seminaryEditorContent.setConfirmButtonText(text);

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, CANCEL_BUTTON);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		seminaryEditorContent.setCancelButtonText(text);

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, BROWSE_BUTTON);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		seminaryEditorContent.setBrowseButtonText(text);

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, CATEGORY);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		seminaryEditorContent.setCategoryText(text);

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, DIFFICULTY);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		seminaryEditorContent.setDifficultyText(text);

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, PATH);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		seminaryEditorContent.setPathText(text);

		return seminaryEditorContent;
	}

	public List<CategoryCoreContent> createCategoryCoreContentList(String culture, String language) throws Exception {

		List<CategoryCoreContent> categoryCoreContentList = new ArrayList<CategoryCoreContent>();

		List<Category> categoryList = seminaryDao.findAllCategories();

		for (Category category : categoryList) {
			// TODO, Unify the parsing of category to categoryCoreContent
			CategoryCoreContent content = new CategoryCoreContent();

			// TODO We need to get the correct translation in the entries
			content.setId(category.getId());
			content.setName(InternationalizationUtility.getTranslationEntry(category.getName(), culture, language).getTcteTranslation());
			content.setDescription(InternationalizationUtility.getTranslationEntry(category.getDescription(), culture, language).getTcteTranslation());

			// We know it's an externalImage
			ImageExternal externalImage = (ImageExternal) category.getImage();
			content.setImageUrl(externalImage.getUrl());

			// We add category content to the list
			categoryCoreContentList.add(content);
		}

		return categoryCoreContentList;
	}
	
	public List<DifficultyContent> createDifficultyContentList(String culture, String language) throws Exception {
	
		List<DifficultyContent> difficultyContentLit = new ArrayList<DifficultyContent>();
		
		List<Difficulty> difficultyList = seminaryDao.findAllDifficulty();
		
		for (Difficulty difficulty : difficultyList) {
			DifficultyContent difficultyContent = new DifficultyContent();
			
			difficultyContent.setLevel(difficulty.getDifficultyValue());
			difficultyContent.setName(InternationalizationUtility.getTranslationEntry(difficulty.getName(), culture, language).getTcteTranslation());
			
			difficultyContentLit.add(difficultyContent);
		}
		
		return difficultyContentLit;
	}
}
