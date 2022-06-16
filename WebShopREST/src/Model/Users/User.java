package Model.Users;

import java.util.*;

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

}