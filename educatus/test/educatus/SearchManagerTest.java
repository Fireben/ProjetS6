package educatus;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.businesslogic.PermissionManager;
import educatus.server.businesslogic.SearchManager;
import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.DynamicContentDao;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.SeminaryDao;

public class SearchManagerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		
		InternationalizationDao internationalizationDao = null;
		SeminaryDao seminaryDao = null;
		SecurityDao securityDao = null;
		DynamicContentDao dynamicContentDao = null;
		EntityManager manager = null;
		PermissionManager permManage = null;
		SearchManager searchManager = null;
		
		Injector dbInjector = Guice.createInjector(new DaoModule(
				"db-manager-localhost"));
		dbInjector.getInstance(JpaInitializer.class);
		internationalizationDao = dbInjector
				.getInstance(InternationalizationDao.class);
		seminaryDao = dbInjector.getInstance(SeminaryDao.class);
		securityDao = dbInjector.getInstance(SecurityDao.class);
		dynamicContentDao = dbInjector.getInstance(DynamicContentDao.class);
		manager = dbInjector.getInstance(EntityManager.class);
		permManage = dbInjector.getInstance(PermissionManager.class);		
		searchManager = dbInjector.getInstance(SearchManager.class);
		
		List<Integer> list = null;
		List<Integer> list2 = null;
		
		trys
		{
			list = searchManager.SearchInSeminary("Basics of factory", false, internationalizationDao.findLanguageByCode("en"));
			list2 = searchManager.SearchInExercice("question de ",  true, internationalizationDao.findLanguageByCode("en"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
