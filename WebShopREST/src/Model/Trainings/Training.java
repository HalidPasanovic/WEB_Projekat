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