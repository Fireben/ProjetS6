package educatus.server.persist.dao;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class DaoModule extends AbstractModule {
	
	final String persistenceUnit;
		
	public DaoModule( String persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
	}
		
	@Override
	protected void configure() {
		JpaPersistModule jpaPersistModule = new JpaPersistModule(persistenceUnit);
        install(jpaPersistModule);
	}	
}

