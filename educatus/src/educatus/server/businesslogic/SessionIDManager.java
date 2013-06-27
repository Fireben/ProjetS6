package educatus.server.businesslogic;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Singleton;

@Singleton
public class SessionIDManager {

	private Map<String, String> userSessionIdMap = new HashMap<String, String>();
	
	public void addNewSessionId(String cip, String sessionId){
		
	}
	
	public String getUserFromSessionID(String id){
		return null;
	}
	
}
