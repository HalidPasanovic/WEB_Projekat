package Model.Users;

import java.util.*;

import Model.Trainings.Training;

/**
 * 
 */
public class Trainer extends User {

	/**
	 * Default constructor
	 */
	public Trainer() {
	}

	/**
	 * 
	 */
	private Set<Training> trainings;

	@Override
	public List<String> ToCSV() {
		List<String> result = new ArrayList<String>();
		result.add(Integer.toString(id));
		result.add(username);
		result.add(password);
		result.add(name);
		result.add(surname);
		result.add(gender.toString());
		result.add(role.toString());
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = 0;
		this.id = Integer.parseInt(values.get(i++));
		this.username = values.get(i++);
		this.password = values.get(i++);
		this.name = values.get(i++);
		this.surname = values.get(i++);
		this.gender = Gender.valueOf(values.get(i++));
		this.role = UserRole.valueOf(values.get(i++));
		return i;
	}

	

}