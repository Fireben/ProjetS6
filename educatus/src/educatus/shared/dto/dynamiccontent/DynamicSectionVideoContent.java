package educatus.shared.dto.dynamiccontent;

import java.io.Serializable;

public class DynamicSectionVideoContent extends AbstractDynamicSection implements Serializable {

	private static final long serialVersionUID = -5024107190445279652L;
	
	private String videoUrl;
	
	@Override
	public DynamicSectionType getSectionType() {
		return DynamicSectionType.VIDEO_SECTION;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
}
