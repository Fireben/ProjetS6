package educatus.server.persist.dao;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class JpaInitializer {

	@Inject 
	JpaInitializer(PersistService service) {
		service.start();						
	}
}
