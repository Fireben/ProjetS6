package educatus.server.businesslogic.seminarymanager;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.InternationalizationUtility;
import educatus.server.persist.dao.dynamiccontent.DynamicSection;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionFormula;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionImage;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionText;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionVideo;
import educatus.server.persist.dao.internationalization.Image;
import educatus.server.persist.dao.internationalization.ImageExternal;
import educatus.server.persist.dao.internationalization.ImageInternal;
import educatus.server.persist.dao.seminary.Seminary;
import educatus.shared.dto.dynamiccontent.DynamicSectionAlignment;
import educatus.shared.dto.dynamiccontent.DynamicSectionAlignment.AlignmentEnum;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.dto.seminary.SeminaryCoreContent;

@Singleton
public class SeminaryContentBuilder {

	@Inject
	private EntityManager entityManager;
	
	public SeminaryContent buildSeminaryContent(int seminaryId, String culture, String language) {
		
		Seminary seminary = entityManager.find(Seminary.class, seminaryId);		
		SeminaryContent content = parseSeminary(seminary, culture, language);		
		return content;
	}
	
	// TODO, move parse seminary 
	private SeminaryContent parseSeminary(Seminary seminary, String culture, String language){
		
		SeminaryContent content = new SeminaryContent();
		SeminaryCoreContent coreContent = new SeminaryCoreContent();
		
		coreContent.setId(seminary.getSemiId());
		coreContent.setTitle(InternationalizationUtility.getTranslationEntry(seminary.getTitle(), culture, language).getTcteTranslation());
		coreContent.setDescription(InternationalizationUtility.getTranslationEntry(seminary.getDescription(), culture, language).getTcteTranslation());		
		coreContent.setDifficulty(InternationalizationUtility.getTranslationEntry(seminary.getDifficulty().getName(), culture, language).getTcteTranslation());
		coreContent.setAuthor(seminary.getAuthor().getFirstName() + " " + seminary.getAuthor().getLastName());
		coreContent.setLastEditor(seminary.getLastEditor().getFirstName() + " " + seminary.getLastEditor().getLastName());
		coreContent.setCreatedDate(seminary.getDateCreated().toString());
		coreContent.setEditedDate(seminary.getDateModified().toString());
		
		// TODO Remove hardcoding
		DynamicSectionAlignment alignment = new DynamicSectionAlignment();
		alignment.setAlignmentEnum(AlignmentEnum.CENTER);
		alignment.setAlignmentName("CENTER");
		
		for (DynamicSection dynamicSection : seminary.getDynamicContent().getDynamicSectionList()) {
			switch (dynamicSection.getDynamicSectionType().getId()) {
				// ID 1 => DynamicSectionText
				case 1:
					DynamicSectionText dynamicSectionText = (DynamicSectionText) dynamicSection;
					DynamicSectionTextContent dynamicSectionTextContent = new DynamicSectionTextContent();
					
					dynamicSectionTextContent.setId(dynamicSectionText.getId());
					dynamicSectionTextContent.setSequenceNumber(dynamicSectionText.getSequenceNumber());
					dynamicSectionTextContent.setTitle(InternationalizationUtility.getTranslationEntry(dynamicSectionText.getTitle(), culture, language).getTcteTranslation());	
					dynamicSectionTextContent.setText(InternationalizationUtility.getTranslationEntry(dynamicSectionText.getText(), culture, language).getTcteTranslation());	
					dynamicSectionTextContent.setAlignment(alignment);
				
					content.getDynamicSectionList().add(dynamicSectionTextContent);
					break;
				case 2:
					DynamicSectionImage dynamicSectionImage = (DynamicSectionImage) dynamicSection;
					DynamicSectionImageContent dynamicSectionImageContent = new DynamicSectionImageContent();
					
					dynamicSectionImageContent.setId(dynamicSectionImage.getId());
					dynamicSectionImageContent.setSequenceNumber(dynamicSectionImage.getSequenceNumber());
					
					Image image = dynamicSectionImage.getImage().getImageContentTranslationEntries().get(0).getImage();
					
					if (image.getType().getId() == 2){
						ImageExternal imageExternal = (ImageExternal) image;
						dynamicSectionImageContent.setImageUrl(imageExternal.getUrl());
						dynamicSectionImageContent.setImageDescription(InternationalizationUtility.getTranslationEntry(dynamicSectionImage.getDescription(), culture, language).getTcteTranslation());	
						dynamicSectionImageContent.setAlignment(alignment);
						
						content.getDynamicSectionList().add(dynamicSectionImageContent);
					} else if (image.getType().getId() == 1){
						ImageInternal imageInternal = (ImageInternal) image;
					}
										
					break;
				case 3:
					DynamicSectionVideo dynamicSectionVideo = (DynamicSectionVideo) dynamicSection;					
					break;
				case 4:
					DynamicSectionFormula dynamicSectionFormula = (DynamicSectionFormula) dynamicSection;					
					break;
			default:
				break;
			}
		}
		
		content.setCoreContent(coreContent);
		return content;
	}
}
