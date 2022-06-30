package Model.Users;

import java.time.LocalDate;

/**
 * 
 */
public class Administrator extends User {

	public Administrator(String username, String password, String name, String surname,
			Gender gender, String dateOfBirth, UserRole role) {
		super(username, password, name, surname, gender, role, dateOfBirth);
	}

	/**
	 * Default constructor
	 */
	public Administrator() {
	}
}