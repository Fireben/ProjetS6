package educatus.client.ui.widget;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import educatus.client.ui.PdfViewer;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.dynamiccontent.DynamicSectionImageContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionPDFContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionTextContent;
import educatus.shared.dto.dynamiccontent.DynamicSectionVideoContent;

public class DynamicSection extends Composite {
	private FlowPanel panel;
	private boolean containsPdf;
	
	public DynamicSection() {
		panel = new FlowPanel();
		initWidget(panel);
		createPanel();		
	}

	private void createPanel() {
		panel.setStyleName("dynamicSectionContainer");		
	}

	public void setList(List<AbstractDynamicSection> dynamicSectionList, FlowPanel parentContainer) {
		panel.setVisible(dynamicSectionList.size() != 0);		
		containsPdf = false;		
		panel.setStyleName("dynamicSectionContainer");
		
		for(AbstractDynamicSection abstractDynamicSection : dynamicSectionList) {
			addSection(abstractDynamicSection);
		}
		
		String parentWidth = containsPdf ? "900px" : "700px";
		parentContainer.setWidth(parentWidth);
	}
	
	public void addSection(AbstractDynamicSection abstractDynamicSection) {
		switch (abstractDynamicSection.getSectionType()) {
		case FORMULA_SECTION:
			break;
		case IMAGE_SECTION:
			DynamicSectionImageContent imageContent = (DynamicSectionImageContent) abstractDynamicSection;
			addImageSection(imageContent.getImageUrl());
			break;
		case TEXT_SECTION:
			DynamicSectionTextContent textContent = (DynamicSectionTextContent) abstractDynamicSection;
			addTextSection(textContent.getTitle(), textContent.getText());
			break;
		case VIDEO_SECTION:
			DynamicSectionVideoContent videoContent = (DynamicSectionVideoContent) abstractDynamicSection;
			addVideoSection(videoContent.getVideoUrl());
			break;
		case PDF_SECTION:
			DynamicSectionPDFContent pdfContent = (DynamicSectionPDFContent) abstractDynamicSection;
			addPDFSection(pdfContent.getPDFUrl());
			break;
		}
		panel.add(new HTML("<br/>"));
	}
	
	private void addImageSection(String imageUrl) {
		Image image = new Image(imageUrl);
		
		panel.add(image);
	}

	private void addTextSection(String title, String text) {
		Label titleLabel = new Label(title);
		titleLabel.setStyleName("title");
		panel.add(titleLabel);
		panel.add(new HTML(text));		
	}
	
	private void addPDFSection(String pdfUrl) {
		panel.clear();
		panel.setStyleName("pdfSectionContainer");
		
		PdfViewer pdfViewer = new PdfViewer();
		pdfViewer.setPdfSrc(pdfUrl);
		panel.add(pdfViewer);
		
		containsPdf = true;
	}
	
	//TODO replace by Nic's widget
	private void addVideoSection(String videoUrl){
		TextBox textBox = new TextBox();
		textBox.setText(videoUrl);
		
		panel.add(textBox);
	}

	public void clear() {
		panel.clear();
	}
}
