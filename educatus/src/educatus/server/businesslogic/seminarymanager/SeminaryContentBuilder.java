package educatus.server.businesslogic.seminarymanager;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.DynamicContentAdapter;
import educatus.server.businesslogic.SeminaryAdapter;
import educatus.server.persist.dao.dynamiccontent.DynamicSection;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionFormula;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionImage;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionPDF;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionText;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionVideo;
import educatus.server.persist.dao.seminary.Seminary;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionPDFContent;
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
	private SeminaryContent parseSeminary(Seminary seminary, String culture, String language) {

		SeminaryContent content = new SeminaryContent();
		SeminaryCoreContent coreContent = SeminaryAdapter.seminaryToSeminaryCoreContent(seminary, culture, language);

		for (DynamicSection dynamicSection : seminary.getDynamicContent().getDynamicSectionList()) {
			switch (dynamicSection.getDynamicSectionType().getId()) {
			// ID 1 => DynamicSectionText
			case 1:
				DynamicSectionText dynamicSectionText = (DynamicSectionText) dynamicSection;
				DynamicSectionTextContent dynamicSectionTextContent = DynamicContentAdapter.dynamicSectionTextToContent(dynamicSectionText, culture, language);
				content.getDynamicSectionList().add(dynamicSectionTextContent);
				break;
			case 2:
				DynamicSectionImage dynamicSectionImage = (DynamicSectionImage) dynamicSection;
				DynamicSectionImageContent dynamicSectionImageContent = DynamicContentAdapter.dynamicSectionImageToContent(dynamicSectionImage, culture, language);
				content.getDynamicSectionList().add(dynamicSectionImageContent);
				break;
			case 3:
				DynamicSectionVideo dynamicSectionVideo = (DynamicSectionVideo) dynamicSection;
				break;
			case 4:
				DynamicSectionFormula dynamicSectionFormula = (DynamicSectionFormula) dynamicSection;
				break;
			case 5:
				DynamicSectionPDF dynamicSectionPDF = (DynamicSectionPDF) dynamicSection;
				DynamicSectionPDFContent dynamicSectionPDFContent = DynamicContentAdapter.dynamicSectionPDFToContent(dynamicSectionPDF, culture, language);
				content.getDynamicSectionList().add(dynamicSectionPDFContent);
				break;
			default:
				break;
			}
		}

		content.setCoreContent(coreContent);
		return content;
	}
}
