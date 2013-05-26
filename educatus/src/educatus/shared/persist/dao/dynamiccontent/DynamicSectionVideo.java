package educatus.shared.persist.dao.dynamiccontent;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dynamicsectionvideo database table.
 * 
 */
@Entity
@Table(name="dynamic_content.dynamicsectionvideo")
public class DynamicSectionVideo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dyse_id", unique=true, nullable=false)
	private Integer dyseId;

	@Column(name="tece_description", nullable=false)
	private Integer teceDescription;

	@Column(name="vice_video", nullable=false)
	private Integer viceVideo;

    public DynamicSectionVideo() {
    }

	public Integer getDyseId() {
		return this.dyseId;
	}

	public void setDyseId(Integer dyseId) {
		this.dyseId = dyseId;
	}

	public Integer getTeceDescription() {
		return this.teceDescription;
	}

	public void setTeceDescription(Integer teceDescription) {
		this.teceDescription = teceDescription;
	}

	public Integer getViceVideo() {
		return this.viceVideo;
	}

	public void setViceVideo(Integer viceVideo) {
		this.viceVideo = viceVideo;
	}
}