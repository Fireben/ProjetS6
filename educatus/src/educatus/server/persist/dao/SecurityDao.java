package educatus.server.persist.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import com.google.gwt.thirdparty.javascript.jscomp.mozilla.rhino.ast.ArrayLiteral;
import com.google.inject.Inject;

import educatus.server.businesslogic.LDAPUser;
import educatus.server.persist.dao.security.LogUserConnection;
import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Seminary;

public class SecurityDao {

	@Inject
	private EntityManager entityManager;

	public User findUserByCip(String cip) throws Exception {

		List<?> resultList = entityManager.createNamedQuery(User.FIND_BY_CIP).setParameter(User.FIND_BY_CIP_PARAM_NAME, cip).getResultList();

		if (resultList.size() == 1) {
			return (User) resultList.get(0);
		} else {
			throw new EntityNotFoundException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() throws Exception {

		List<?> resultList = entityManager.createNamedQuery(User.FIND_ALL).getResultList();

		return (List<User>) resultList;
	}

	public User insertUserFromLdap(LDAPUser ldapUser) {
		java.util.Date date = new java.util.Date();
		User user = new User();

		user.setCip(ldapUser.getCip());
		user.setFirstName(ldapUser.getFirstName());
		user.setLastName(ldapUser.getLastName());

		user.setDateJoined(new Timestamp(date.getTime()));
		user.setProfilePrivacy(true);
		
		user.setLogUserConnections(new ArrayList<LogUserConnection>());

		entityManager.persist(user);
		return user;
	}

	public void updateUserFromLdap(User user, LDAPUser ldapUser) throws Exception {
		if (user.getCip() != ldapUser.getCip())
			return;

		if (user.getFirstName() != ldapUser.getFirstName() || user.getLastName() != ldapUser.getLastName()) {
			user.setFirstName(ldapUser.getFirstName());
			user.setLastName(ldapUser.getLastName());
			updateUser(user);
		}
	}

	public void updateUser(User user) throws Exception {
		entityManager.merge(user);
	}
}
