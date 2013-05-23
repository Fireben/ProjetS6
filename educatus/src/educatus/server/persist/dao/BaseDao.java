package educatus.server.persist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.JpaEntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BaseDao {

	@Inject
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		entityManager.clear();
		return entityManager;
	}

	public void refreshCache() {
		entityManager.clear();
	}

	public void set(Object o, Class<?> cl) {

		entityManager.getTransaction().begin();
		entityManager.merge(o);
		entityManager.getTransaction().commit();
	}

	public void setPersist(Object o, Class<?> cl) {
		entityManager.clear();
		entityManager.getTransaction().begin();
		entityManager.persist(o);
		entityManager.getTransaction().commit();
	}

	public Object get(Object id, Class<?> cl) {
		entityManager.clear();
		Object entity = entityManager.find(cl, id);
		return entity;
	}

	public boolean remove(Object id, Class<?> cl) {
		boolean status = false;

		try {
			Object o = getEntityManager().find(cl, id);
			if (o == null)
				return false;
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(o);
			getEntityManager().getTransaction().commit();
			status = true;
		} catch (EntityNotFoundException ex) {
			status = false;
		}
		return status;
	}

	public List get(String sql, List<String> parameters) {
		Query query = getEntityManager().createNativeQuery(sql);
		int i = 0;
		for (Object parameter : parameters) {
			query.setParameter(++i, parameter);
		}
		List results = query.getResultList();
		return results;
	}

	public int set(String sql, List<Object> parameters) {
		getEntityManager().getTransaction().begin();
		Query query = getEntityManager().createNativeQuery(sql);
		int i = 0;
		for (Object parameter : parameters) {
			query.setParameter(++i, parameter);
		}
		int results = query.executeUpdate();
		getEntityManager().getTransaction().commit();
		return results;
	}

	public void execute(String sql, List<Object> parameters) {
		getEntityManager().getTransaction().begin();
		Query query = getEntityManager().createNativeQuery(sql);
		int i = 0;
		for (Object parameter : parameters) {
			query.setParameter(++i, parameter);
		}

		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}

	public void execute(String sql) {
		try {
			List<Object> parameters = new ArrayList<Object>();
			execute(sql, parameters);
		} catch (Exception e) {

		}
	}

	public void executeFunction(String sql) {
		try {
			Query query = getEntityManager().createNativeQuery(sql);
			query.getSingleResult();
		} catch (Exception e) {

		}
	}

	public List<?> get(String sql) {
		Query query = getEntityManager().createNativeQuery(sql);
		List<?> results = query.getResultList();
		return results;
	}

	public List<Map<String, Object>> getMap(String sql) {
		List<Object> parameters = new ArrayList<Object>();
		return getMap(sql, parameters);
	}

	@SuppressWarnings("finally")
	public List<Map<String, Object>> getMap(String sql, List<Object> parameters) {
		List<Map<String, Object>> a = new ArrayList<Map<String, Object>>();
		try {
			getEntityManager().getTransaction().begin();
			Connection connection = (((JpaEntityManager) getEntityManager()
					.getDelegate())).getServerSession().getAccessor()
					.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			int j = 0;
			for (Object parameter : parameters) {
				ps.setObject(++j, parameter);
			}
			ResultSet r = ps.executeQuery();
			Map<String, Object> m;
			while (r.next()) {
				m = new HashMap<String, Object>();
				for (int i = 1; i <= r.getMetaData().getColumnCount(); i++) {
					m.put(r.getMetaData().getColumnLabel(i), r.getObject(i));
				}
				a.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getEntityManager().getTransaction().commit();
			return a;
		}
	}
}
