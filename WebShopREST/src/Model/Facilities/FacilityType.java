package Model.Facilities;

import java.util.*;

import Model.IDClass;

/**
 * 
 */
public class FacilityType extends IDClass {

	/**
	 * Default constructor
	 */
	public FacilityType() {
	}

	public FacilityType(int id) {
		this.id = id;
	}

	public FacilityType(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<String> ToCSV() {
		ArrayList<String> result = new ArrayList<>();
		result.add(String.valueOf(id));
		result.add(name);
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = 0;
		id = Integer.parseInt(values.get(i++));
		name = values.get(i++);
		return i;
	}


}