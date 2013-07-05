package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PdfViewer extends Composite {

	private static PdfViewerUiBinder uiBinder = GWT
			.create(PdfViewerUiBinder.class);

	interface PdfViewerUiBinder extends UiBinder<Widget, PdfViewer> {
	}
	
	@UiField
	VerticalPanel pdfVerticalPanel;

	public PdfViewer() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPdfSrc(String src){
		HTMLPanel pdfPanel = new HTMLPanel("embed", "");
		pdfPanel.getElement().setAttribute("src", src);
		pdfPanel.getElement().setAttribute("width", "1000");
		pdfPanel.getElement().setAttribute("height", "750");
		pdfVerticalPanel.clear();
		pdfVerticalPanel.add(pdfPanel);
	}
}
