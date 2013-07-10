package educatus.server.businesslogic;

import educatus.server.persist.dao.dynamiccontent.DynamicSectionImage;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionPDF;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionText;
import educatus.server.persist.dao.internationalization.Image;
import educatus.server.persist.dao.internationalization.ImageInternal;
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
