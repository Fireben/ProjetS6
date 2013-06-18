package educatus.server.businesslogic.seminarymanager;

import educatus.shared.dto.dynamiccontent.DynamicSectionAlignment;
import educatus.shared.dto.dynamiccontent.DynamicSectionAlignment.AlignmentEnum;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.dto.seminary.SeminaryCoreContent;

public class SeminaryContentFactory {

	public static SeminaryContent createSeminaryContent(int seminaryId, String culture, String language) {
		
		SeminaryContent content = new SeminaryContent();
		
		SeminaryCoreContent coreContent = new SeminaryCoreContent();
		coreContent.setId(1);
		coreContent.setTitle("Design patterns");
		coreContent.setDescription("Design patterns : Beginner");
		coreContent.setDifficulty("EASY");
		coreContent.setAuthor("Marc-André Beaudry");
		coreContent.setLastEditor("Marc-André Beaudry");
		coreContent.setCreatedDate("2013-06-18");
		coreContent.setEditedDate("2013-06-18");
		
		content.setCoreContent(coreContent);

		DynamicSectionAlignment centered = new DynamicSectionAlignment();
		centered.setAlignmentEnum(AlignmentEnum.CENTER);
		centered.setAlignmentName("CENTER");
		
		DynamicSectionTextContent paragraph1 = new DynamicSectionTextContent();
		paragraph1.setId(1);
		paragraph1.setSequenceNumber(1);
		paragraph1.setAlignment(centered);
		paragraph1.setTitle("Abstract Factory");
		paragraph1.setText("Provides an interface for creating families of related or dependant objects without specifying their concrete classes.");
		
		DynamicSectionTextContent paragraph2 = new DynamicSectionTextContent();
		paragraph1.setId(2);
		paragraph1.setSequenceNumber(2);
		paragraph1.setAlignment(centered);
		paragraph1.setTitle("Factory Method");
		paragraph1.setText("Define an interface for creating an object, but let subclass defice which class to instanciate. Factory Method lets a class defer instantiation to the subclasses.");
		
		content.getDynamicSectionList().add(paragraph1);
		content.getDynamicSectionList().add(paragraph2);
		
		return content;
	}
}
