package educatus.client.ui.dataGrids;

public class ListContent {
	private int id;
	private String name;
	private String description;	
	private String author;
	private String date;
	private int difficulty;
	private String nameToken;
	
	public ListContent(int id, String name, String description, String author,
			String date, int difficulty, String nameToken) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.author = author;
		this.date = date;
		this.difficulty = difficulty;
		this.nameToken = nameToken;
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

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getNameToken() {
		return nameToken;
	}

	public void setNameToken(String nameToken) {
		this.nameToken = nameToken;
	}	
}
