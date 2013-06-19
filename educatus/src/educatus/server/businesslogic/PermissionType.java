package educatus.server.businesslogic;

public enum PermissionType {
	internationalization(1),
	security(2),
	seminary(3),
	exercice(4),
	certificate(5),
	achievement(6);
	
	/* Members */
	private final int id;

	/* Constructors */
	private PermissionType(int id) {
		this.id = id;
	}
	
	/* Getters/Setters */
	public int getId() {
		return id;
	}

}
