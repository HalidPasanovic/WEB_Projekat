package Model.Memberships;

import java.util.*;

import Model.IDClass;

/**
 * 
 */
public class MembershipType extends IDClass {

	/**
	 * Default constructor
	 */
	public MembershipType() {

	}

	public MembershipType(int id) {
		this.id = id;
	}

	public MembershipType(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	private String name;

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		result.add(name);
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);
		name = values.get(i++);
		return i;
	}
}