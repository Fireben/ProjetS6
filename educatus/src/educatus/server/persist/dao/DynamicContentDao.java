package educatus.server.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.eclipse.persistence.tools.schemaframework.DynamicSchemaManager;

import com.google.inject.Inject;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionAlignment;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionImage;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionPDF;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionText;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionType;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionVideo;
import educatus.server.persist.dao.internationalization.ImageContentEntry;
import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.internationalization.VideoContentEntry;

public class DynamicContentDao {

	@Inject
	private EntityManager entityManager;

	@Inject
	private InternationalizationDao interDao;

	@SuppressWarnings("unchecked")
	public List<DynamicContent> findAllDynamicContent() throws Exception {

		List<?> resultList = entityManager.createNamedQuery(DynamicContent.FIND_ALL).getResultList();

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

	public void addDynamicSectionPDF(int dynamicContentId, byte[] rawData, int dynamicSectionAlignmentId, int sequenceNumber) throws Exception {

		DynamicContent dynamicContent = entityManager.find(DynamicContent.class, dynamicContentId);
		DynamicSectionAlignment dynamicSectionAlignment = entityManager.find(DynamicSectionAlignment.class, dynamicSectionAlignmentId);
		DynamicSectionType dynamicSectionType = entityManager.find(DynamicSectionType.class, DynamicSectionPDF.DYNAMIC_SECTION_TYPE_VALUE);

		DynamicSectionPDF dynamicSectionPDF = new DynamicSectionPDF();
		dynamicSectionPDF.setDynamicContent(dynamicContent);
		dynamicSectionPDF.setDynamicSectionAlignment(dynamicSectionAlignment);
		dynamicSectionPDF.setSequenceNumber(sequenceNumber);
		dynamicSectionPDF.setPdfData(rawData);
		dynamicSectionPDF.setDynamicSectionType(dynamicSectionType);

		dynamicContent.getDynamicSectionList().add(dynamicSectionPDF);
		entityManager.persist(dynamicSectionPDF);
	}
	
	public void addDynamicSectionVideo(int dynamicContentId, int descriptionTextContentEntryId, int videoContentEntryId, int dynamicSectionAlignmentId, int sequenceNumber) throws Exception {

		DynamicContent dynamicContent = entityManager.find(DynamicContent.class, dynamicContentId);
		TextContentEntry descriptionTextContentEntry = entityManager.find(TextContentEntry.class, descriptionTextContentEntryId);
		VideoContentEntry videoContentEntry = entityManager.find(VideoContentEntry.class, videoContentEntryId);
		DynamicSectionAlignment dynamicSectionAlignment = entityManager.find(DynamicSectionAlignment.class, dynamicSectionAlignmentId);
		DynamicSectionType dynamicSectionType = entityManager.find(DynamicSectionType.class, DynamicSectionVideo.DYNAMIC_SECTION_TYPE_VALUE);

		DynamicSectionVideo dynamicSectionVideo = new DynamicSectionVideo();
		dynamicSectionVideo.setDescription(descriptionTextContentEntry);	
		dynamicSectionVideo.setDynamicContent(dynamicContent);
		dynamicSectionVideo.setDynamicSectionAlignment(dynamicSectionAlignment);
		dynamicSectionVideo.setSequenceNumber(sequenceNumber);
		dynamicSectionVideo.setVideo(videoContentEntry); 
		dynamicSectionVideo.setDynamicSectionType(dynamicSectionType);
		
		dynamicContent.getDynamicSectionList().add(dynamicSectionVideo);
		entityManager.persist(dynamicSectionVideo);
	}
}
