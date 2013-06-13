package educatus;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.seminary.Category;

public class CategoryDaoTest {

	private static String EN_LANG = "en";
	private static String CA_CULT = "CA";

	public static void main(String[] args) {
		try {
			InternationalizationDao internationalizationDao = null;
			SeminaryDao seminaryDao = null;
			EntityManager manager = null;

			Injector dbInjector = Guice.createInjector(new DaoModule(
					"db-manager-localhost"));
			dbInjector.getInstance(JpaInitializer.class);
			internationalizationDao = dbInjector
					.getInstance(InternationalizationDao.class);
			seminaryDao = dbInjector.getInstance(SeminaryDao.class);
			manager = dbInjector.getInstance(EntityManager.class);

			List<Category> topLevelCat = seminaryDao.findTopLevelCategories();

			for (Category category : topLevelCat) {
				System.out.println(category.getName()
						.getTextContentTranslationEntries().get(0)
						.getTcteTranslation());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
