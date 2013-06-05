package educatus.server.services.requestservice;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import educatus.server.persist.dao.JpaInitializer;

public class RequestServiceContextListener extends GuiceServletContextListener
{
	@Override
	protected Injector getInjector()
	{
		Injector dbInjector = GuiceInjector.getInjector();
		dbInjector.getInstance(JpaInitializer.class);
		return dbInjector;
	}
}
