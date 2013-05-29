package educatus.client.ui.dataGrids;

public class Seminary {
	private int id;
	private String name;
	private String description;	
	private String author;
	private String date;
	private String editor;
	
	public Seminary(int id, String name, String description, String author,
			String date, String editor) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.author = author;
		this.date = date;
		this.editor = editor;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
}
