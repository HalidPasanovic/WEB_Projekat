package Model;

import java.util.*;

/**
 * 
 */
public class Adress extends IDClass {

	/**
	 * Default constructor
	 */
	public Adress() {
		super();
		this.id = 1;
	}

	/**
	 * 
	 */
	private String street;

	/**
	 * 
	 */
	private int number;

	/**
	 * 
	 */
	private String place;

	/**
	 * 
	 */
	private int shipingCode;

	@Override
	public List<String> ToCSV() {
		List<String> result = new ArrayList<String>();
		result.add(Integer.toString(id));
		result.add(street);
		result.add(Integer.toString(number));
		result.add(place);
		result.add(Integer.toString(shipingCode));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = 0;
		this.id = Integer.parseInt(values.get(i++));
		this.street = values.get(i++);
		this.number = Integer.parseInt(values.get(i++));
		this.place = values.get(i++);
		this.shipingCode = Integer.parseInt(values.get(i++));
		return i;
	}

}