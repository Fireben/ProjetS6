package educatus.server.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;

public class DynamicContentDao {

	@Inject
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<DynamicContent> findAllDynamicContent() throws Exception
	{

		List<?> resultList = entityManager.createNamedQuery(DynamicContent.FIND_ALL)
				.getResultList();

		return (List<DynamicContent>) resultList;
	}
	
	public DynamicContent createDynamicContent(){
		
		DynamicContent content = new DynamicContent();
		entityManager.getTransaction().begin();		
		entityManager.merge(content);
		entityManager.getTransaction().commit();	
		
		return content;
	}
}
