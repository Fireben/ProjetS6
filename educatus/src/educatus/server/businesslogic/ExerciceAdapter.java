package educatus.server.businesslogic;

import java.text.SimpleDateFormat;

import educatus.server.persist.dao.exercice.Exercice;
import educatus.server.persist.dao.security.User;
import educatus.shared.dto.exercice.ExerciceCoreContent;
import educatus.shared.dto.user.UserCoreContent;

public class ExerciceAdapter {

	public static ExerciceCoreContent exerciceToExerciceCoreContent(Exercice exercice, String culture, String language) {
				
		ExerciceCoreContent exerciceCoreContent = new ExerciceCoreContent();

		exerciceCoreContent.setId(exercice.getId());
		exerciceCoreContent.setTitle(InternationalizationUtility.getTranslationEntry(exercice.getTitle(), culture, language).getTcteTranslation());
		exerciceCoreContent.setDescription(InternationalizationUtility.getTranslationEntry(exercice.getDescription(), culture, language).getTcteTranslation());
		exerciceCoreContent.setDifficulty(InternationalizationUtility.getTranslationEntry(exercice.getDifficultyValue().getName(), culture, language).getTcteTranslation());
		exerciceCoreContent.setDifficultyValue(exercice.getDifficultyValue().getDifficultyValue());
		
		User author = exercice.getAuthor();
		User editor = exercice.getLastEditor();

		// TODO, do we need to merge with LDAP user ?
		UserCoreContent authorUserCoreContent = UserAdapter.userToUserCoreContent(author);
		UserCoreContent editorUserCoreContent = UserAdapter.userToUserCoreContent(editor);

		exerciceCoreContent.setAuthor(authorUserCoreContent);
		exerciceCoreContent.setLastEditor(editorUserCoreContent);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:MM:ss");		
		exerciceCoreContent.setCreatedDate(simpleDateFormat.format(exercice.getDateCreated()));
		exerciceCoreContent.setEditedDate(simpleDateFormat.format(exercice.getDateModified()));

		return exerciceCoreContent;
	}
}
