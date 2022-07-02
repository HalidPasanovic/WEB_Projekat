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

	public MembershipType(MembershipTypeEnum type) {
		this.type = type;
	}

	/**
	 * 
	 */
	private MembershipTypeEnum type;

	/**
	 * 
	 */
	private float price;

	/**
	 * 
	 */
	private int visitationCount;

	

	public MembershipTypeEnum gettype() {
		return type;
	}

	public void settype(MembershipTypeEnum type) {
		this.type = type;
	}

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		result.add(String.valueOf(type));
		result.add(String.valueOf(price));
		result.add(String.valueOf(visitationCount));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);
		type = MembershipTypeEnum.valueOf(values.get(i++));
		price = Float.parseFloat(values.get(i++));
		visitationCount = Integer.parseInt(values.get(i++));
		return i;
	}
}