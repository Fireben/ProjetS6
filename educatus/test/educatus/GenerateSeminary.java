package educatus;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SeminaryDao;

public class GenerateSeminary {

	private static String EN_LANG = "en";
	private static String CA_CULT = "CA";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InternationalizationDao internationalizationDao = null;
		SeminaryDao seminaryDao = null;
		EntityManager manager = null;
		
		Injector dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));	 
		dbInjector.getInstance(JpaInitializer.class);
		internationalizationDao = dbInjector.getInstance(InternationalizationDao.class);
		seminaryDao = dbInjector.getInstance(SeminaryDao.class);
		manager = dbInjector.getInstance(EntityManager.class);
				
	}

}
