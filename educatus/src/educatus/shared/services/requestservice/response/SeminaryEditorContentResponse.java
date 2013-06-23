package educatus.shared.services.requestservice.response;

import java.util.ArrayList;
import java.util.List;

import educatus.shared.dto.SeminaryEditorContent;
import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.dto.seminary.DifficultyContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryEditorContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -1618028085456577118L;

	private SeminaryEditorContent seminaryEditorContent = null;
	private List<DifficultyContent> difficultyContentList = new ArrayList<DifficultyContent>();
	private List<CategoryCoreContent> categoryCoreContentList = new ArrayList<CategoryCoreContent>();

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.SEMINARY_EDITOR_CONTENT_RESPONSE;
	}

	public SeminaryEditorContent getSeminaryEditorContent() {
		return seminaryEditorContent;
	}

	public void setSeminaryEditorContent(SeminaryEditorContent seminaryEditorContent) {
		this.seminaryEditorContent = seminaryEditorContent;
	}

	public List<DifficultyContent> getDifficultyContentList() {
		return difficultyContentList;
	}

	public void setDifficultyContentList(List<DifficultyContent> difficultyContentList) {
		this.difficultyContentList = difficultyContentList;
	}

	public List<CategoryCoreContent> getCategoryCoreContentList() {
		return categoryCoreContentList;
	}

	public void setCategoryCoreContentList(List<CategoryCoreContent> categoryCoreContentList) {
		this.categoryCoreContentList = categoryCoreContentList;
	}
}
