package Model.Users;

/**
 * 
 */
public class Administrator extends User {

	public Administrator(String username, String password, String name, String surname,
			Gender gender, UserRole role) {
		super(username, password, name, surname, gender, role);
	}

	/**
	 * Default constructor
	 */
	public Administrator() {
	}
}