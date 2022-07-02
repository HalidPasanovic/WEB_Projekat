package Model.Users;

import java.time.LocalDate;
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

	public Trainer(int id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private Set<Training> trainings = new HashSet<>();

	/**
	 * 
	 */
	private String pictureLocation;

	public String getPictureLocation() {
		return pictureLocation;
	}

	public void setPictureLocation(String pictureLocation) {
		this.pictureLocation = pictureLocation;
	}

	public Trainer(String username, String password, String name, String surname, Gender gender,
			UserRole role, String dateOfBirth, Set<Training> trainings, String pictureLocation) {
		super(username, password, name, surname, gender, role, dateOfBirth);
		this.trainings = trainings;
		this.pictureLocation = pictureLocation;
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
		result.add(pictureLocation);

		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);

		int count = i + Integer.parseInt(values.get(i++)) + 1;
		while (i < count) {
			Training facility = new Training();
			facility.setId(Integer.parseInt(values.get(i++)));
			trainings.add(facility);
		}
		pictureLocation = values.get(i++);
		
		return i;
	}

	

}