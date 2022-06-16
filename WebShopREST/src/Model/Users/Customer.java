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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int FromCSV(List<String> values) {
		// TODO Auto-generated method stub
		return 0;
	}

}