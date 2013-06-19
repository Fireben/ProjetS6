package educatus.server.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import com.google.inject.Inject;

import educatus.server.persist.dao.security.User;

public class SecurityDao {

	@Inject
	private EntityManager entityManager;

	public User findUserByCip(String cip) throws Exception {

		List<?> resultList = entityManager.createNamedQuery(User.FIND_BY_CIP)
				.setParameter(User.FIND_BY_CIP_PARAM_NAME, cip)
				.getResultList();

		if (resultList.size() == 1) {
			return (User) resultList.get(0);
		} else {
			throw new EntityNotFoundException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllCulture() throws Exception {

		List<?> resultList = entityManager.createNamedQuery(User.FIND_ALL)
				.getResultList();

		return (List<User>) resultList;
	}

	public void updateUser(User user) throws Exception {
		
		entityManager.merge(user);		
	}
}
