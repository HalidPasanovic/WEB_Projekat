package Model.Trainings;

import java.time.LocalTime;
import java.util.*;

import Model.IDClass;
import Model.Facilities.SportFacility;
import Model.Users.Trainer;

/**
 * 
 */
public class Training extends IDClass {

	/**
	 * Default constructor
	 */
	public Training() {
	}

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private TrainingType type;

	/**
	 * 
	 */
	private SportFacility facility;

	/**
	 * 
	 */
	private LocalTime duration;

	/**
	 * 
	 */
	private Trainer trainer;

	/**
	 * 
	 */
	private String description;

	/**
	 * 
	 */
	private String pictureLocation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrainingType getType() {
		return type;
	}

	public void setType(TrainingType type) {
		this.type = type;
	}

	public SportFacility getFacility() {
		return facility;
	}

	public void setFacility(SportFacility facility) {
		this.facility = facility;
	}

	public LocalTime getDuration() {
		return duration;
	}

	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureLocation() {
		return pictureLocation;
	}

	public void setPictureLocation(String pictureLocation) {
		this.pictureLocation = pictureLocation;
	}

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