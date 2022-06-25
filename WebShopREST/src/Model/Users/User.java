package Model.Users;

import Model.IDClass;

/**
 * 
 */
public abstract class User extends IDClass {

	/**
	 * Default constructor
	 */
	public User() {
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
}