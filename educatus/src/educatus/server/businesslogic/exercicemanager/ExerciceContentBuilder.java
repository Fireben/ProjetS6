package educatus.server.businesslogic.exercicemanager;

import com.google.inject.Singleton;

import educatus.shared.dto.exercice.ExerciceContent;
import educatus.shared.dto.exercice.ExerciceCoreContent;

@Singleton
public class ExerciceContentBuilder {

	public ExerciceContent buildSeminaryContent(int exerciceId, String culture, String language) {

		ExerciceContent exerciceContent = new ExerciceContent();
		ExerciceCoreContent excerciceCoreContent = new ExerciceCoreContent();
		
		
		
		
		return exerciceContent;
	}
}
