package Model.Users;

import java.time.LocalDate;
import java.util.*;

import Model.Facilities.SportFacility;

/**
 * 
 */
public class Manager extends User {

	/**
	 * Default constructor
	 */
	public Manager() {
	}

	/**
	 * 
	 */
	private Set<SportFacility> facilities = new HashSet<>();

	public Manager(String username, String password, String name, String surname, Gender gender,
			UserRole role, String dateOfBirth, Set<SportFacility> facilities) {
		super(username, password, name, surname, gender, role, dateOfBirth);
		this.facilities = facilities;
	}

	public Set<SportFacility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<SportFacility> facilities) {
		this.facilities = facilities;
	}
	
	public void addFacility(SportFacility f)
	{
		this.facilities.add(f);
	}

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();

		result.add(String.valueOf(facilities.size()));
		for (SportFacility facility : facilities) {
			result.add(String.valueOf(facility.getId()));
		}

		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);

		int count = i + Integer.parseInt(values.get(i++)) + 1;
		while (i < count) {
			SportFacility facility = new SportFacility();
			facility.setId(Integer.parseInt(values.get(i++)));
			facilities.add(facility);
		}

		return i;
	}

	

}