package educatus.server.services.requestservice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;

import educatus.server.persist.dao.DaoModule;

public class GuiceInjector
{
	private static Injector dbInjector = Guice.createInjector(
			new ServletModule()
			{
				@Override
				protected void configureServlets()
				{
					serve("/educatus/requestservice").with(
							RequestServiceImpl.class);
				}
			}, new DaoModule("db-manager-localhost"));

	public static Injector getInjector()
	{
		return dbInjector;
	}
}
