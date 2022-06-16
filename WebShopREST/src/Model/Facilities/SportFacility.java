package Model.Facilities;

import java.util.*;

import Model.IDClass;
import Model.Location;

/**
 * 
 */
public class SportFacility extends IDClass {

	/**
	 * Default constructor
	 */
	public SportFacility() {
	}

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private FacilityType type;

	/**
	 * 
	 */
	private Set<RecreationType> recreationTypes;

	/**
	 * 
	 */
	private boolean status;

	/**
	 * 
	 */
	private Location location;

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