package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dynamicsectiontext database table.
 * 
 */
@Entity
@Table(name="dynamic_content.dynamicsectiontext")
public class DynamicSectionText implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dyse_id", unique=true, nullable=false)
	private Integer dyseId;

	@Column(name="tece_text", nullable=false)
	private Integer teceText;

	@Column(name="tece_title", nullable=false)
	private Integer teceTitle;

    public DynamicSectionText() {
    }

	public Integer getDyseId() {
		return this.dyseId;
	}

	public void setDyseId(Integer dyseId) {
		this.dyseId = dyseId;
	}

	public Integer getTeceText() {
		return this.teceText;
	}

	public void setTeceText(Integer teceText) {
		this.teceText = teceText;
	}

	public Integer getTeceTitle() {
		return this.teceTitle;
	}

	public void setTeceTitle(Integer teceTitle) {
		this.teceTitle = teceTitle;
	}
}