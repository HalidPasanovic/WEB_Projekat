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

	public Training(int id) {
		this.id = id;
	}

	public Training(String name, TrainingType type, SportFacility facility, String duration,
			Trainer trainer, String description) {
		this.name = name;
		this.type = type;
		this.facility = facility;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
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
	private String duration;

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
	private float aditionalCost;

	public float getAditionalCost() {
		return aditionalCost;
	}

	public void setAditionalCost(float aditionalCost) {
		this.aditionalCost = aditionalCost;
	}

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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
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


	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		result.add(name);
		result.add(String.valueOf(type.getId()));
		result.add(String.valueOf(facility.getId()));
		result.add(duration);
		result.add(String.valueOf(trainer.getId()));
		result.add(description);
		result.add(String.valueOf(aditionalCost));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);
		name = values.get(i++);

		type = new TrainingType();
		type.setId(Integer.parseInt(values.get(i++)));

		facility = new SportFacility();
		facility.setId(Integer.parseInt(values.get(i++)));

		duration = values.get(i++);

		trainer = new Trainer();
		trainer.setId(Integer.parseInt(values.get(i++)));

		description = values.get(i++);

		aditionalCost = Float.parseFloat(values.get(i++));

		return i;
	}
}
