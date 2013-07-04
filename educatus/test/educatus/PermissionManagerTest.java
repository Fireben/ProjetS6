package educatus;

import java.sql.Timestamp;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.businesslogic.PermissionConstant;
import educatus.server.businesslogic.PermissionManager;
import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.DynamicContentDao;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.security.Group;
import educatus.server.persist.dao.security.User;

public class PermissionManagerTest {

	public static void main(String[] args) throws Exception {

		InternationalizationDao internationalizationDao = null;
		SeminaryDao seminaryDao = null;
		SecurityDao securityDao = null;
		DynamicContentDao dynamicContentDao = null;
		EntityManager manager = null;
		PermissionManager permManage = null;

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
		
		// Retrouve l'utilisateur temporaire du test s'il existe.
		User tempUser = null;
		try
		{
			tempUser = securityDao.findUserByCip("asdf1234");
		}
		catch(Exception e){
			manager.getTransaction().begin();
			// Creation d'un utilisateur pour les tests.
			tempUser = new User();
			tempUser.setCip("asdf1234");
			tempUser.setFirstName("Test");
			tempUser.setLastName("Case");
			tempUser.setDateJoined(new Timestamp((new java.util.Date()).getTime()));
			tempUser.setProfilePrivacy(false);
			// Ajout de l'utilisateur a la base de donnees.
			manager.persist(tempUser);
			manager.getTransaction().commit();
		}

		boolean returnValue = false;

		// Test - Ajout d'une permission a un utilisateur.
		returnValue = permManage.UserAddPermission(tempUser, PermissionConstant.internationalization_culture_create);
		if (!permManage.HavePermission(tempUser, PermissionConstant.internationalization_culture_create)) 
		{
			throw new Exception("Echoue - Ajout d'une permission a un utilisateur.");
		}

		// Test - Suppression d'une permission a un utilisateur.
		returnValue = permManage.UserRemovePermission(tempUser,PermissionConstant.internationalization_culture_create);
		if(permManage.HavePermission(tempUser, PermissionConstant.internationalization_culture_create)) 
		{
			throw new Exception("Echoue - Suppression d'une permission a un utilisateur.");
		}
		
		// Ajout du Group Temporaire
		manager.getTransaction().begin();		
		Group tempGroup = new Group();
		tempGroup.setName(internationalizationDao.insertTextContentTranslationEntry("fr", "ca", "RoleTest").getTextcontententry());
		tempGroup.setDescription(internationalizationDao.insertTextContentTranslationEntry("fr", "ca", "RoleTest Description").getTextcontententry());		
		manager.persist(tempGroup);		
		manager.getTransaction().commit();
		
		// Ajout de l'utilisateur au Group.
		permManage.AddUserToGroup(tempUser, tempGroup);
		
		// Test - Ajout d'une permission a un Group.
		returnValue = permManage.GroupAddPermission(tempGroup, PermissionConstant.internationalization_culture_create);
		if (!permManage.HavePermission(tempUser, PermissionConstant.internationalization_culture_create)) 
		{
			throw new Exception("Echoue - Ajout d'une permission a un usertype.");
		}

		// Test - Suppression d'une permission a un Group.
		returnValue = permManage.GroupRemovePermission(tempGroup,PermissionConstant.internationalization_culture_create);
		if(permManage.HavePermission(tempUser, PermissionConstant.internationalization_culture_create)) 
		{
			throw new Exception("Echoue - Suppression d'une permission a un usertype.");
		}

		// Suppression du Group.
		manager.getTransaction().begin();
		manager.remove(tempGroup);
		manager.getTransaction().commit();

		// Suppression de l'utilisateur.
		manager.getTransaction().begin();
		manager.remove(tempUser);
		manager.getTransaction().commit();
	}

}
