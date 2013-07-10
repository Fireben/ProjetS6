package educatus.server.persist.dao.dynamiccontent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("5")
// DYNAMIC SECTION TYPE = 5
public class DynamicSectionPDF extends DynamicSection implements Serializable {
	
	private static final long serialVersionUID = -2543647229190293191L;
	public static final int DYNAMIC_SECTION_TYPE_VALUE = 5;

	@Column(name = "dspd_data", nullable = false)
	private byte[] pdfData;

	public DynamicSectionPDF() {
	}

	public byte[] getPdfData() {
		return pdfData;
	}

	public void setPdfData(byte[] pdfData) {
		this.pdfData = pdfData;
	}
}