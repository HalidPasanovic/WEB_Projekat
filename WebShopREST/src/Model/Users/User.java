package Model.Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Model.IDClass;

/**
 * 
 */
public class User extends IDClass {

	/**
	 * Default constructor
	 */
	public User() {
	}

	public User(String username, String password, String name, String surname, Gender gender,
			UserRole role, String dateOfBirth) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
	}


	/**
	 * 
	 */
	protected String username;
	
	/**
	 * 
	 */
	protected String password;

	/**
	 * 
	 */
	protected String name;

	/**
	 * 
	 */
	protected String surname;

	/**
	 * 
	 */
	protected Gender gender;

	/**
	 * 
	 */
	protected UserRole role;


	/**
	 * 
	 */
	protected String dateOfBirth;


	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);
		this.username = values.get(i++);
		this.password = values.get(i++);
		this.name = values.get(i++);
		this.surname = values.get(i++);
		this.gender = Gender.valueOf(values.get(i++));
		this.role = UserRole.valueOf(values.get(i++));
		this.dateOfBirth = values.get(i++);
		return i;
	}

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		result.add(username);
		result.add(password);
		result.add(name);
		result.add(surname);
		result.add(gender.toString());
		result.add(role.toString());
		result.add(dateOfBirth);
		return result;
	}
}