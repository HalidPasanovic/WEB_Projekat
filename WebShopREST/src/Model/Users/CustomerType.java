package Model.Users;

import java.util.*;

import Model.IDClass;

/**
 * 
 */
public class CustomerType extends IDClass {

	/**
	 * Default constructor
	 */
	public CustomerType() {
	}

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private float discount;

	/**
	 * 
	 */
	private float pointsRequired;

	@Override
	public List<String> ToCSV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int FromCSV(List<String> values) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}