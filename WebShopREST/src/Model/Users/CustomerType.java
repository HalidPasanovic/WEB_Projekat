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
		ArrayList<String> result = new ArrayList<>();
		result.add(String.valueOf(id));
		result.add(name);
		result.add(String.valueOf(discount));
		result.add(String.valueOf(pointsRequired));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = 0;
		id = Integer.parseInt(values.get(i++));
		name = values.get(i++);
		discount = Float.parseFloat(values.get(i++));
		pointsRequired = Float.parseFloat(values.get(i++));
		return i;
	}

	
}