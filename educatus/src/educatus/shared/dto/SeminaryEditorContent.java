package educatus.shared.dto;

import java.io.Serializable;

public class SeminaryEditorContent implements Serializable {

	private static final long serialVersionUID = 2851914737482481079L;

	private String confirmButtonText;
	private String cancelButtonText;
	private String browseButtonText;
	private String pathText;

	private String difficultyText;
	private String categoryText;

	private String titleText;
	private String descriptionText;

	public String getConfirmButtonText() {
		return confirmButtonText;
	}

	public void setConfirmButtonText(String confirmButtonText) {
		this.confirmButtonText = confirmButtonText;
	}

	public String getCancelButtonText() {
		return cancelButtonText;
	}

	public void setCancelButtonText(String cancelButtonText) {
		this.cancelButtonText = cancelButtonText;
	}

	public String getBrowseButtonText() {
		return browseButtonText;
	}

	public void setBrowseButtonText(String browseButtonText) {
		this.browseButtonText = browseButtonText;
	}

	public String getPathText() {
		return pathText;
	}

	public void setPathText(String pathText) {
		this.pathText = pathText;
	}

	public String getDifficultyText() {
		return difficultyText;
	}

	public void setDifficultyText(String difficultyText) {
		this.difficultyText = difficultyText;
	}

	public String getCategoryText() {
		return categoryText;
	}

	public void setCategoryText(String categoryText) {
		this.categoryText = categoryText;
	}

	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
}
