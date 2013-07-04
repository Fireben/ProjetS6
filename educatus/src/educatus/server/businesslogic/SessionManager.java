package educatus.server.businesslogic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.security.LogUserConnection;
import educatus.server.persist.dao.security.User;

public class SessionManager {
		
	private class SessionEntry{
		private String cip;
		private String ip;
		private UUID uuid;
		private int nbFailedConnectionAttempt;
		private Date TimestampLastActivity;
		
		public SessionEntry()
		{
			this.cip = "";
			this.ip = "";
			this.uuid = null;
			this.nbFailedConnectionAttempt = 0;
			this.TimestampLastActivity = new Date();
		}
		
		public SessionEntry(String cip){
			this();
			this.cip = cip;
		}
	}

	private static final int MAX_CONNECTION_ATTEMPT = 5;
	private static final int MAX_SESSION_TIME_ACTIVE = 1200;
	private static final int MAX_SESSION_TIME_LOCKED = 900;
	
	@Inject
	private EntityManager entityManager;
	
	private static SessionManager instance;	
	
	private Map<String,SessionEntry> sessionMap;
	
	private SessionManager()
	{
		this.sessionMap  = new HashMap<String,SessionEntry>();
	}
	
	public static SessionManager getInstance()
	{	
		if(SessionManager.instance == null){
			SessionManager.instance = new SessionManager();
		}
		return SessionManager.instance;
	}
	
	public boolean userCanLog(String cip)
	{
		if(this.sessionMap.containsKey(cip)){
			SessionEntry sessionEntry = this.sessionMap.get(cip);
			
			if(sessionEntry.nbFailedConnectionAttempt < SessionManager.MAX_CONNECTION_ATTEMPT){
				return true;
			}
			else
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(sessionEntry.TimestampLastActivity);
				cal.add(Calendar.SECOND, SessionManager.MAX_SESSION_TIME_LOCKED);
											
				Date now = new Date();
				
				return now.after(cal.getTime());				
			}
		}
		else{
			// If the cip is not present, the user never try to log before.
			return true;
		}
	}
	
	public void logConnectionAttempt(String cip, boolean passwordIsValid){
		SessionEntry sessionEntry = null;
		
		if(!this.sessionMap.containsKey(cip)){			
			this.sessionMap.put(cip, new SessionEntry(cip));					
		}
		sessionEntry = this.sessionMap.get(cip);
		
		// Log of the connection attempt.
		//TODO - A verifier
		Injector dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));
		
		User user = null;
		try
		{
			user = dbInjector.getInstance(SecurityDao.class).findUserByCip(cip);
		}
		catch(Exception e)
		{		
		}
		
		entityManager.getTransaction().begin();
		LogUserConnection log = new LogUserConnection();
		log.setAttemptSuccess(passwordIsValid);
		log.setTimestamp(new Timestamp(new Date().getTime()));
		user.addLogUserConnection(log);		
		entityManager.merge(user);
		entityManager.getTransaction().commit();
				
		if(passwordIsValid)
		{
			sessionEntry.nbFailedConnectionAttempt = 0;		
		}
		else
		{
			sessionEntry.nbFailedConnectionAttempt++;
		}
		sessionEntry.TimestampLastActivity = new Date();
	}
	
	public UUID generateSessionUUID(String cip, String ip){
		SessionEntry sessionEntry = this.sessionMap.get(cip);			
		sessionEntry.ip = ip;
		sessionEntry.uuid = UUID.randomUUID(); 
		return sessionEntry.uuid;
	}
	
	public boolean isSessionStillValid(String cip, String ip, UUID uuid){
		SessionEntry sessionEntry = this.sessionMap.get(cip);
		try
		{
		// Session with this User doesn't exist.
		if(sessionEntry == null)
			return false;
		
		//  Session start in a network, end in another.
		if(!sessionEntry.ip.equals(sessionEntry.ip))
			return false;
		
		// Session Id is different from the server's one.
		if(sessionEntry.uuid != uuid)
			return false;
		
		// Session exceeds the allowed time for a session.
		Calendar cal = Calendar.getInstance();
		cal.setTime(sessionEntry.TimestampLastActivity);
		cal.add(Calendar.SECOND, SessionManager.MAX_SESSION_TIME_ACTIVE);
		
		if(new Date().after(cal.getTime()))
			return false;
		
		// Every validation passes.
		return true;
		}
		finally
		{
			// Update the last activity timestamp.
			sessionEntry.TimestampLastActivity = new Date();
		}
	}
	
	public UUID getSessionUUID(String cip){
		SessionEntry sessionEntry = this.sessionMap.get(cip);
		
		if(sessionEntry == null)
			return null;
		else
			return sessionEntry.uuid;
	}
}
