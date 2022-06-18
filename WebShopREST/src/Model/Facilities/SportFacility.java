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