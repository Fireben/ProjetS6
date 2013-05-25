package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dynamicsectionimage database table.
 * 
 */
@Entity
@Table(name="dynamic_content.dynamicsectionimage")
public class DynamicSectionImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dyse_id", unique=true, nullable=false)
	private Integer dyseId;

	@Column(name="imce_image", nullable=false)
	private Integer imceImage;

	@Column(name="tece_description", nullable=false)
	private Integer teceDescription;

    public DynamicSectionImage() {
    }

	public Integer getDyseId() {
		return this.dyseId;
	}

	public void setDyseId(Integer dyseId) {
		this.dyseId = dyseId;
	}

	public Integer getImceImage() {
		return this.imceImage;
	}

	public void setImceImage(Integer imceImage) {
		this.imceImage = imceImage;
	}

	public Integer getTeceDescription() {
		return this.teceDescription;
	}

	public void setTeceDescription(Integer teceDescription) {
		this.teceDescription = teceDescription;
	}
}