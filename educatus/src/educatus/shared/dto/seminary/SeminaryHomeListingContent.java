package educatus.shared.dto.seminary;

import java.io.Serializable;
import java.util.ArrayList;

import educatus.shared.dto.seminary.SeminaryHomeCategoryContent.CategoryContent;

public class SeminaryHomeListingContent implements Serializable {

	public static class SeminaryContent {

		private int id;
		private String title;
		private String description;

		private String author;
		private String lastEditor;

		private String difficulty;

		private String createdDate;
		private String editedDate;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getLastEditor() {
			return lastEditor;
		}

		public void setLastEditor(String lastEditor) {
			this.lastEditor = lastEditor;
		}

		public String getDifficulty() {
			return difficulty;
		}

		public void setDifficulty(String difficulty) {
			this.difficulty = difficulty;
		}

		public String getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}

		public String getEditedDate() {
			return editedDate;
		}

		public void setEditedDate(String editedDate) {
			this.editedDate = editedDate;
		}

	}

	private static final long serialVersionUID = 6160737635292640612L;

	private CategoryContent commonParent = null;
	private ArrayList<SeminaryContent> seminariesChildren = new ArrayList<SeminaryContent>();

	public CategoryContent getCommonParent() {
		return commonParent;
	}

	public void setCommonParent(CategoryContent commonParent) {
		this.commonParent = commonParent;
	}

	public ArrayList<SeminaryContent> getSeminariesChildren() {
		return seminariesChildren;
	}

	public void setSeminariesChildren(
			ArrayList<SeminaryContent> seminariesChildren) {
		this.seminariesChildren = seminariesChildren;
	}

}
