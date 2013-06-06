package junit.dao.internationalization;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.JpaInitializer;

public class InternationalizationTest {

	static Injector dbInjector = null;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));
		dbInjector.getInstance(JpaInitializer.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
