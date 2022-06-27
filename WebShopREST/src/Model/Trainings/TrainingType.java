package Model.Trainings;

import java.util.*;

import Model.IDClass;

/**
 * 
 */
public class TrainingType extends IDClass {

	/**
	 * Default constructor
	 */
	public TrainingType() {

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
		List<String> result = super.ToCSV();
		result.add(name);
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);
		name = values.get(i++);
		return i;
	}
}