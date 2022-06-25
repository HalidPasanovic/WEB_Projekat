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

	public SportFacility(String name, FacilityType type, List<RecreationType> recreationTypes,
			boolean status, Location location) {
		this.name = name;
		this.type = type;
		this.recreationTypes = recreationTypes;
		this.status = status;
		this.location = location;
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
	private List<RecreationType> recreationTypes;

	/**
	 * 
	 */
	private boolean status;

	/**
	 * 
	 */
	private Location location;

	/**
	 * 
	 */
	private String workRange;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FacilityType getType() {
		return type;
	}

	public void setType(FacilityType type) {
		this.type = type;
	}

	public List<RecreationType> getRecreationTypes() {
		return recreationTypes;
	}

	public void setRecreationTypes(List<RecreationType> recreationTypes) {
		this.recreationTypes = recreationTypes;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getWorkRange() {
		return workRange;
	}

	public void setWorkRange(String workRange) {
		this.workRange = workRange;
	}

	@Override
	public List<String> ToCSV() {
		ArrayList<String> result = new ArrayList<>();
		result.add(String.valueOf(id));
		result.add(name);
		result.add(String.valueOf(type.getId()));
		result.add(String.valueOf(recreationTypes.size()));
		for (RecreationType it : recreationTypes) {
			result.add(String.valueOf(it.getId()));
		}
		result.add(String.valueOf(status));
		result.add(workRange);
		result.addAll(location.ToCSV());
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = 0;
		id = Integer.parseInt(values.get(i++));
		name = values.get(i++);
		type = new FacilityType(Integer.parseInt(values.get(i++)));

		int count = Integer.parseInt(values.get(i++)) + i;
		recreationTypes = new ArrayList<RecreationType>();
		while(i < count) {
			recreationTypes.add(new RecreationType(Integer.parseInt(values.get(i++))));
		}

		status = Boolean.getBoolean(values.get(i++));
		workRange = values.get(i++);

		values = RemoveNElements(i, values);
		location = new Location();
		i = i + location.FromCSV(values);
		return i;
	}

	private List<String> RemoveNElements(int i, List<String> values){
		ArrayList<String> result = new ArrayList<>();
		for (int j = 0; j < values.size(); j++) {
			if(j < i){
				continue;
			}
			result.add(values.get(j));
		}
		return result;
	}
	
}