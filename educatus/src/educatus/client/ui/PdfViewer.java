package educatus.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class PdfViewer extends Composite {	
	HTMLPanel pdfPanel;

	public PdfViewer() {
		super();
		pdfPanel = new HTMLPanel("embed", "");
		initWidget(pdfPanel);		
		pdfPanel.setSize("900px", "750px");
	}

	public void setPdfSrc(String src){		
		pdfPanel.getElement().setAttribute("src", src);
	}
}
