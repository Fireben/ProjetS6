package educatus.server.businesslogic;

import java.text.SimpleDateFormat;

import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Category;
import educatus.server.persist.dao.seminary.Seminary;
import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.dto.seminary.SeminaryCoreContent;
import educatus.shared.dto.user.UserCoreContent;

public class SeminaryAdapter {

	public static SeminaryCoreContent seminaryToSeminaryCoreContent(Seminary seminary, String culture, String language) {

		SeminaryCoreContent seminaryCoreContent = new SeminaryCoreContent();

		seminaryCoreContent.setId(seminary.getSemiId());
		seminaryCoreContent.setTitle(InternationalizationUtility.getTranslationEntry(seminary.getTitle(), culture, language).getTcteTranslation());
		seminaryCoreContent.setDescription(InternationalizationUtility.getTranslationEntry(seminary.getDescription(), culture, language).getTcteTranslation());
		seminaryCoreContent.setDifficulty(InternationalizationUtility.getTranslationEntry(seminary.getDifficulty().getName(), culture, language).getTcteTranslation());
		seminaryCoreContent.setDifficultyValue(seminary.getDifficulty().getDifficultyValue());
		
		User author = seminary.getAuthor();
		User editor = seminary.getLastEditor();

		// TODO, do we need to merge with LDAP user ?
		UserCoreContent authorUserCoreContent = UserAdapter.userToUserCoreContent(author);
		UserCoreContent editorUserCoreContent = UserAdapter.userToUserCoreContent(editor);

		seminaryCoreContent.setAuthor(authorUserCoreContent);
		seminaryCoreContent.setLastEditor(editorUserCoreContent);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM:ss");		
		seminaryCoreContent.setCreatedDate(simpleDateFormat.format(seminary.getDateCreated()));
		seminaryCoreContent.setEditedDate(simpleDateFormat.format(seminary.getDateModified()));

		return seminaryCoreContent;
	}

	public static CategoryCoreContent categoryToCategoryCoreContent(Category category, String culture, String language) {

		CategoryCoreContent categoryCoreContent = new CategoryCoreContent();
		category.getId();

		categoryCoreContent.setId(category.getId());
		categoryCoreContent.setName(InternationalizationUtility.getTranslationEntry(category.getName(), culture, language).getTcteTranslation());
		categoryCoreContent.setDescription(InternationalizationUtility.getTranslationEntry(category.getDescription(), culture, language).getTcteTranslation());

		String url = ImageAdapter.getUrlFromAbstractImage(category.getImage());
		categoryCoreContent.setImageUrl(url);

		return categoryCoreContent;
	}
}
