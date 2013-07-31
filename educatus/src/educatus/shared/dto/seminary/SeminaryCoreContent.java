package educatus.shared.dto.seminary;

import java.io.Serializable;

import educatus.shared.dto.user.UserCoreContent;

public class SeminaryCoreContent implements Serializable {

	private static final long serialVersionUID = 1047401170857580293L;
	
	private int id;
	private String title;
	private String description;

	private UserCoreContent author;
	private UserCoreContent lastEditor;

	private String difficulty;
	private int difficultyValue;

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

	public UserCoreContent getAuthor() {
		return author;
	}

	public void setAuthor(UserCoreContent author) {
		this.author = author;
	}

	public UserCoreContent getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(UserCoreContent lastEditor) {
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

	public int getDifficultyValue() {
		return difficultyValue;
	}

	public void setDifficultyValue(int difficultyValue) {
		this.difficultyValue = difficultyValue;
	}
}
