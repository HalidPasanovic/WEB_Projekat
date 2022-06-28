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

	public Trainer(String username, String password, String name, String surname, Gender gender,
			UserRole role, Set<Training> trainings) {
		super(username, password, name, surname, gender, role);
		this.trainings = trainings;
	}

	public Set<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();

		result.add(String.valueOf(trainings.size()));
		for (Training training : trainings) {
			result.add(String.valueOf(training.getId()));
		}

		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);

		int count = i + Integer.parseInt(values.get(i++));
		while (i < count) {
			Training facility = new Training();
			facility.setId(Integer.parseInt(values.get(i++)));
			trainings.add(facility);
		}
		
		return i;
	}

	

}