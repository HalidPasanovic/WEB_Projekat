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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Adress(String street, int number, String place, int shipingCode) {
		this.street = street;
		this.number = number;
		this.place = place;
		this.shipingCode = shipingCode;
	}

	/**
	 * 
	 */
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * 
	 */
	private String place;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * 
	 */
	private int shipingCode;

	public int getShipingCode() {
		return shipingCode;
	}

	public void setShipingCode(int shipingCode) {
		this.shipingCode = shipingCode;
	}

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