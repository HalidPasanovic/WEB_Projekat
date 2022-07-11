package Model.Users;

import java.time.LocalDate;
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

	public Customer(int id) {
		this.id = id;
	}

	public Customer(String username, String password, String name, String surname, Gender gender,
			UserRole role, String dateOfBirth, Membership membership, Set<SportFacility> visitedFacilities,
			float points, CustomerType type) {
		super(username, password, name, surname, gender, role, dateOfBirth);
		this.membership = membership;
		this.visitedFacilities = visitedFacilities;
		this.points = points;
		this.type = type;
	}

	/**
	 * 
	 */
	private Membership membership;

	/**
	 * 
	 */
	private Set<SportFacility> visitedFacilities = new HashSet<>();

	/**
	 * 
	 */
	private float points;

	/**
	 * 
	 */
	private CustomerType type;


	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public Set<SportFacility> getVisitedFacilities() {
		return visitedFacilities;
	}

	public void setVisitedFacilities(Set<SportFacility> visitedFacilities) {
		this.visitedFacilities = visitedFacilities;
	}

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}

	public void addPoints(float points){
		this.points += points;
		if(this.points < 0){
			this.points = 0;
		}
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}


	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		if(membership == null)
		{
			result.add("-1");
		}
		else
		{
			result.add(String.valueOf(membership.getId()));
		}
		
		result.add(String.valueOf(visitedFacilities.size()));
		for (SportFacility facility : visitedFacilities) {
			result.add(String.valueOf(facility.getId()));
		}

		result.add(String.valueOf(points));
		if(type == null)
		{
			result.add("-1");
		}
		else
		{
			result.add(String.valueOf(type.getId()));
		}
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);

		membership = new Membership();
		membership.setId(Integer.parseInt(values.get(i++)));

		int count = i + Integer.parseInt(values.get(i++)) + 1;
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