package educatus.server.businesslogic;

import java.util.ArrayList;
import java.util.List;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.dynamiccontent.DynamicSection;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionFormula;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionImage;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionPDF;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionText;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionVideo;
import educatus.server.persist.dao.internationalization.Image;
import educatus.server.persist.dao.internationalization.ImageInternal;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.dynamiccontent.DynamicSectionAlignment;
import educatus.shared.dto.dynamiccontent.DynamicSectionAlignment.AlignmentEnum;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionPDFContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;

public class DynamicContentAdapter {

	public static DynamicSectionTextContent dynamicSectionTextToContent(DynamicSectionText dynamicSectionText, String culture, String language) {
		
		DynamicSectionTextContent dynamicSectionTextContent = new DynamicSectionTextContent();
		
		dynamicSectionTextContent.setId(dynamicSectionText.getId());
		dynamicSectionTextContent.setSequenceNumber(dynamicSectionText.getSequenceNumber());
		dynamicSectionTextContent.setTitle(InternationalizationUtility.getTranslationEntry(dynamicSectionText.getTitle(), culture, language).getTcteTranslation());	
		dynamicSectionTextContent.setText(InternationalizationUtility.getTranslationEntry(dynamicSectionText.getText(), culture, language).getTcteTranslation());	
		// TODO Remove hardcoding
		dynamicSectionTextContent.setAlignment(getAlignment());
		
		return dynamicSectionTextContent;
	}
	
	public static List<AbstractDynamicSection> getAbstractDynamicSectionFromDynamicContent(DynamicContent dynamicContent, String culture, String language) {
		
		List<AbstractDynamicSection> abstractDynamicSections = new ArrayList<AbstractDynamicSection>();
		
		for (DynamicSection dynamicSection : dynamicContent.getDynamicSectionList()) {
			switch (dynamicSection.getDynamicSectionType().getId()) {
			// ID 1 => DynamicSectionText
			case 1:
				DynamicSectionText dynamicSectionText = (DynamicSectionText) dynamicSection;
				DynamicSectionTextContent dynamicSectionTextContent = DynamicContentAdapter.dynamicSectionTextToContent(dynamicSectionText, culture, language);
				abstractDynamicSections.add(dynamicSectionTextContent);
				break;
			case 2:
				DynamicSectionImage dynamicSectionImage = (DynamicSectionImage) dynamicSection;
				DynamicSectionImageContent dynamicSectionImageContent = DynamicContentAdapter.dynamicSectionImageToContent(dynamicSectionImage, culture, language);
				abstractDynamicSections.add(dynamicSectionImageContent);
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
				abstractDynamicSections.add(dynamicSectionPDFContent);
				break;
			default:
				break;
			}
		}
		
		return abstractDynamicSections;
	}
	
	public static DynamicSectionImageContent dynamicSectionImageToContent(DynamicSectionImage dynamicSectionImage, String culture, String language) {
		
		DynamicSectionImageContent dynamicSectionImageContent = new DynamicSectionImageContent();
		
		dynamicSectionImageContent.setId(dynamicSectionImage.getId());
		dynamicSectionImageContent.setSequenceNumber(dynamicSectionImage.getSequenceNumber());
		
		Image image = dynamicSectionImage.getImage().getImageContentTranslationEntries().get(0).getImage();
		dynamicSectionImageContent.setImageUrl(ImageAdapter.getUrlFromAbstractImage(image));
		
		if (image.getType().getId() == 2){
			dynamicSectionImageContent.setImageDescription(InternationalizationUtility.getTranslationEntry(dynamicSectionImage.getDescription(), culture, language).getTcteTranslation());	
			dynamicSectionImageContent.setAlignment(getAlignment());
			
		} else if (image.getType().getId() == 1){
			ImageInternal imageInternal = (ImageInternal) image;
			dynamicSectionImageContent.setImageDescription(imageInternal.getImageName());
			dynamicSectionImageContent.setAlignment(getAlignment());
		}
		
		return dynamicSectionImageContent;
	}
	
	public static DynamicSectionPDFContent dynamicSectionPDFToContent(DynamicSectionPDF dynamicSectionPDF, String culture, String language) {
		
		DynamicSectionPDFContent dynamicSectionPDFContent = new DynamicSectionPDFContent();
		
		dynamicSectionPDFContent.setId(dynamicSectionPDF.getId());
		dynamicSectionPDFContent.setSequenceNumber(dynamicSectionPDF.getSequenceNumber());
		dynamicSectionPDFContent.setPDFUrl("/internalPdfServlet?id=" + dynamicSectionPDF.getId());
		dynamicSectionPDFContent.setAlignment(getAlignment());
		
		return dynamicSectionPDFContent;
	}

	// TODO Remove hardcoding
	private static DynamicSectionAlignment getAlignment() {
		
		DynamicSectionAlignment alignment = new DynamicSectionAlignment();
		alignment.setAlignmentEnum(AlignmentEnum.CENTER);
		alignment.setAlignmentName("CENTER");
		return alignment;
	}
}
