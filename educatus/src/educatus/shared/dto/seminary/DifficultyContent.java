package educatus.shared.dto.seminary;

import java.io.Serializable;

public class DifficultyContent implements Serializable {

	private static final long serialVersionUID = -7453358322245370163L;

	private int level;
	private String name;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
