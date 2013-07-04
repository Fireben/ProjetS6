package educatus;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.seminary.Category;
import educatus.server.persist.dao.seminary.Seminary;

public class CategoryTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/* Create DAOs */
			SeminaryDao seminaryDao = null;
			EntityManager manager = null;

			Injector dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));
			dbInjector.getInstance(JpaInitializer.class);
			
			seminaryDao = dbInjector.getInstance(SeminaryDao.class);
			manager = dbInjector.getInstance(EntityManager.class);
			
			/* Test stored procedure call to create category tree */
			Query query = manager.createNativeQuery("select * from seminary.getCategoryTree(?)").setParameter(1, 49);
			List<Integer> result = (List<Integer>) query.getResultList();
			for(int id : result)
				System.out.println(id);
			
			/* Test find seminary by id */
			Seminary seminary = seminaryDao.findSeminaryById(12);
			System.out.println(seminary.getSemiId());
			
			/* Test Named Native Query equal to create category tree */
			result = seminaryDao.findAllChildrenCategories(49);
			for(int id : result)
				System.out.println(id);
			
			/* Test Find All Semis under specified category */
			List<Seminary> seminaries = seminaryDao.findSeminariesByCategory(48);
			for (Seminary seminar : seminaries)
				System.out.println(seminar.getSemiId());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
