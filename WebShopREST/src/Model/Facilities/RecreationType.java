package Model.Facilities;

import java.util.*;

import Model.IDClass;

/**
 * 
 */
public class RecreationType extends IDClass {

	/**
	 * Default constructor
	 */
	public RecreationType() {}

	public RecreationType(String name) {
		this.name = name;
	}

	public void Change(RecreationType type) {
		this.id = type.id;
		this.name = type.name;
	}

	public RecreationType(int id) {
		this.id = id;
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