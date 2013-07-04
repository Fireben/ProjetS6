package educatus.server.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionAlignment;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionImage;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionText;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionType;
import educatus.server.persist.dao.internationalization.ImageContentEntry;
import educatus.server.persist.dao.internationalization.TextContentEntry;

public class DynamicContentDao {

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private InternationalizationDao interDao;
	
	@SuppressWarnings("unchecked")
	public List<DynamicContent> findAllDynamicContent() throws Exception
	{

		List<?> resultList = entityManager.createNamedQuery(DynamicContent.FIND_ALL)
				.getResultList();

		return (List<DynamicContent>) resultList;
	}
	
	public DynamicContent createDynamicContent() {
		
		DynamicContent content = new DynamicContent();
		entityManager.persist(content);
		
		return content;
	}
	
	public void addDynamicSectionText(int dynamicContentId, int titleTextContentEntryId, int textTextContentEntryId, int dynamicSectionAlignmentId, int sequenceNumber) throws Exception {
		
		DynamicContent dynamicContent = entityManager.find(DynamicContent.class, dynamicContentId);
		TextContentEntry titleTextContentEntry = entityManager.find(TextContentEntry.class, titleTextContentEntryId);
		TextContentEntry textTextContentEntry = entityManager.find(TextContentEntry.class, textTextContentEntryId);
		DynamicSectionAlignment dynamicSectionAlignment = entityManager.find(DynamicSectionAlignment.class, dynamicSectionAlignmentId);
		DynamicSectionType dynamicSectionType = entityManager.find(DynamicSectionType.class, DynamicSectionText.DYNAMIC_SECTION_TYPE_VALUE);
		
		DynamicSectionText dynamicSectionText = new DynamicSectionText();
		dynamicSectionText.setDynamicContent(dynamicContent);
		dynamicSectionText.setSequenceNumber(sequenceNumber);
		dynamicSectionText.setTitle(titleTextContentEntry);
		dynamicSectionText.setText(textTextContentEntry);	
		dynamicSectionText.setDynamicSectionAlignment(dynamicSectionAlignment);
		dynamicSectionText.setDynamicSectionType(dynamicSectionType);
		
		dynamicContent.getDynamicSectionList().add(dynamicSectionText);
		entityManager.persist(dynamicSectionText);
	}
	
	public void addDynamicSectionImage(int dynamicContentId, int descriptionTextContentEntryId, int imageContentEntryId, int dynamicSectionAlignmentId, int sequenceNumber) throws Exception {
		
		DynamicContent dynamicContent = entityManager.find(DynamicContent.class, dynamicContentId);
		TextContentEntry descriptionTextContentEntry = entityManager.find(TextContentEntry.class, descriptionTextContentEntryId);
		ImageContentEntry imageContentEntry = entityManager.find(ImageContentEntry.class, imageContentEntryId);
		DynamicSectionAlignment dynamicSectionAlignment = entityManager.find(DynamicSectionAlignment.class, dynamicSectionAlignmentId);
		DynamicSectionType dynamicSectionType = entityManager.find(DynamicSectionType.class, DynamicSectionImage.DYNAMIC_SECTION_TYPE_VALUE);
		
		DynamicSectionImage dynamicSectionImage = new DynamicSectionImage();
		dynamicSectionImage.setDescription(descriptionTextContentEntry);
		dynamicSectionImage.setDynamicContent(dynamicContent);
		dynamicSectionImage.setDynamicSectionAlignment(dynamicSectionAlignment);
		dynamicSectionImage.setSequenceNumber(sequenceNumber);
		dynamicSectionImage.setImage(imageContentEntry);
		dynamicSectionImage.setDynamicSectionType(dynamicSectionType);

		dynamicContent.getDynamicSectionList().add(dynamicSectionImage);
		entityManager.persist(dynamicSectionImage);
	}
}
