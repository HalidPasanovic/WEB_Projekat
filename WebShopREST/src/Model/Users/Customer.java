package Model.Users;

import java.util.*;

import Model.Facilities.SportFacility;
import Model.Memberships.Membership;

/**
 * 
 */
public class Customer extends User {

	/**
	 * Default constructor
	 */
	public Customer() {
	}

	/**
	 * 
	 */
	private Membership membership;

	/**
	 * 
	 */
	private Set<SportFacility> visitedFacilities;

	/**
	 * 
	 */
	private float points;

	/**
	 * 
	 */
	private CustomerType type;

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		result.add(String.valueOf(membership.getId()));

		result.add(String.valueOf(visitedFacilities.size()));
		for (SportFacility facility : visitedFacilities) {
			result.add(String.valueOf(facility.getId()));
		}

		result.add(String.valueOf(points));
		result.add(String.valueOf(type.getId()));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);

		membership = new Membership();
		membership.setId(Integer.parseInt(values.get(i++)));

		int count = i + Integer.parseInt(values.get(i++));
		while (i < count) {
			SportFacility facility = new SportFacility();
			facility.setId(Integer.parseInt(values.get(i++)));
			visitedFacilities.add(facility);
		}

		points = Float.parseFloat(values.get(i++));

		type = new CustomerType();
		type.setId(Integer.parseInt(values.get(i++)));
		
		return i;
	}

}