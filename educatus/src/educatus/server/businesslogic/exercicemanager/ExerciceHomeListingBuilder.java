package educatus.server.businesslogic.exercicemanager;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.ExerciceAdapter;
import educatus.server.businesslogic.SearchManager;
import educatus.server.businesslogic.SeminaryAdapter;
import educatus.server.persist.dao.ExerciceDao;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.exercice.Exercice;
import educatus.server.persist.dao.seminary.Category;
import educatus.shared.dto.exercice.ExerciceCoreContent;
import educatus.shared.dto.pagecontent.ExerciceHomePageListingContent;
import educatus.shared.dto.seminary.CategoryCoreContent;

@Singleton
public class ExerciceHomeListingBuilder {

	@Inject
	private ExerciceDao exerciceDao;
	@Inject
	private SeminaryDao semDao;
	@Inject
	private SearchManager searchManager;
	@Inject
	private InternationalizationDao internationalizationDao;

	public ExerciceHomePageListingContent buildExerciceHomePageListingContent(String culture, String language) {
		ExerciceHomePageListingContent pageContent = new ExerciceHomePageListingContent();

		try {
			// We set common parent
			pageContent.setCommonParent(null);

			List<Exercice> exerciceList = exerciceDao.findAllExercice();

			for (Exercice exercice : exerciceList) {
				ExerciceCoreContent exerciceCoreContent = ExerciceAdapter.exerciceToExerciceCoreContent(exercice, culture, language);
				pageContent.getExercicesChildren().add(exerciceCoreContent);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return pageContent;
	}

	public ExerciceHomePageListingContent buildExerciceHomePageListingContent(int parentId, String culture, String language) {

		ExerciceHomePageListingContent pageContent = new ExerciceHomePageListingContent();

		try {
			Category parentCategory = semDao.findCategoryById(parentId);

			// We set common parent
			CategoryCoreContent commonParent = SeminaryAdapter.categoryToCategoryCoreContent(parentCategory, culture, language);
			pageContent.setCommonParent(commonParent);

			List<Exercice> exerciceList = exerciceDao.findExercicesByCategory(parentId);

			for (Exercice exercice : exerciceList) {
				ExerciceCoreContent exerciceCoreContent = ExerciceAdapter.exerciceToExerciceCoreContent(exercice, culture, language);
				pageContent.getExercicesChildren().add(exerciceCoreContent);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return pageContent;
	}
	
	public ExerciceHomePageListingContent buildExerciceHomePageListingContent(String searchTerm, String culture, String language) {

		ExerciceHomePageListingContent pageContent = new ExerciceHomePageListingContent();

		try {
			// We set common parent
			pageContent.setCommonParent(null);

			List<Integer> ids = searchManager.SearchInExercice(searchTerm, true, internationalizationDao.findLanguageByCode("en"));
			if(ids.size() != 0) {				
				List<Exercice> exerciceList = exerciceDao.findExercicesByIds(ids);
	
				for (Exercice exercice : exerciceList) {
					ExerciceCoreContent exerciceCoreContent = ExerciceAdapter.exerciceToExerciceCoreContent(exercice, culture, language);
					pageContent.getExercicesChildren().add(exerciceCoreContent);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return pageContent;
	}	
}
