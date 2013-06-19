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
import educatus.server.persist.dao.seminary.Seminary;
import educatus.shared.dto.dynamiccontent.DynamicSectionAlignment;
import educatus.shared.dto.dynamiccontent.DynamicSectionAlignment.AlignmentEnum;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.dto.seminary.SeminaryCoreContent;

@Singleton
public class SeminaryContentFactory {

	@Inject
	private EntityManager entityManager;
	
	public SeminaryContent createSeminaryContent(int seminaryId, String culture, String language) {
		
		culture = "CA";
		language = "en";
		Seminary seminary = entityManager.find(Seminary.class, 6);
		
		SeminaryContent content = parseSeminary(seminary, culture, language);
//		SeminaryContent content = new SeminaryContent();
//		
//		SeminaryCoreContent coreContent = new SeminaryCoreContent();
//		coreContent.setId(1);
//		coreContent.setTitle("Design patterns");
//		coreContent.setDescription("Design patterns : Beginner");
//		coreContent.setDifficulty("EASY");
//		coreContent.setAuthor("Marc-André Beaudry");
//		coreContent.setLastEditor("Marc-André Beaudry");
//		coreContent.setCreatedDate("2013-06-18");
//		coreContent.setEditedDate("2013-06-18");
//		
//		content.setCoreContent(coreContent);
//
//		DynamicSectionAlignment centered = new DynamicSectionAlignment();
//		centered.setAlignmentEnum(AlignmentEnum.CENTER);
//		centered.setAlignmentName("CENTER");
//		
//		DynamicSectionTextContent paragraph1 = new DynamicSectionTextContent();
//		paragraph1.setId(1);
//		paragraph1.setSequenceNumber(1);
//		paragraph1.setAlignment(centered);
//		paragraph1.setTitle("Abstract Factory");
//		paragraph1.setText("Provides an interface for creating families of related or dependant objects without specifying their concrete classes.");
//		
//		DynamicSectionImageContent image = new DynamicSectionImageContent();
//		image.setImageUrl("images/abstractFactory.png");
//		
//		DynamicSectionTextContent paragraph2 = new DynamicSectionTextContent();
//		paragraph2.setId(2);
//		paragraph2.setSequenceNumber(2);
//		paragraph2.setAlignment(centered);
//		paragraph2.setTitle("Factory Method");
//		paragraph2.setText("Define an interface for creating an object, but let subclass defice which class to instanciate. Factory Method lets a class defer instantiation to the subclasses.");
//		
//		content.getDynamicSectionList().add(paragraph1);
//		content.getDynamicSectionList().add(image);
//		content.getDynamicSectionList().add(paragraph2);
		
		return content;
	}
	
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
					ImageExternal imageExternal = null;
					if (image.getId() == 2){
						imageExternal = (ImageExternal) image;
						dynamicSectionImageContent.setImageUrl(imageExternal.getUrl());
						dynamicSectionImageContent.setImageDescription(InternationalizationUtility.getTranslationEntry(dynamicSectionImage.getDescription(), culture, language).getTcteTranslation());	
						dynamicSectionImageContent.setAlignment(alignment);
						
						content.getDynamicSectionList().add(dynamicSectionImageContent);
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
