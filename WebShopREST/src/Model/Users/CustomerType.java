package Model.Users;

import java.util.*;

import Model.IDClass;

/**
 * 
 */
public class CustomerType extends IDClass {

	/**
	 * Default constructor
	 */
	public CustomerType() {
	}

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private float discount;

	/**
	 * 
	 */
	private float pointsRequired;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public float getPointsRequired() {
		return pointsRequired;
	}

	public void setPointsRequired(float pointsRequired) {
		this.pointsRequired = pointsRequired;
	}

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		result.add(name);
		result.add(String.valueOf(discount));
		result.add(String.valueOf(pointsRequired));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);
		name = values.get(i++);
		discount = Float.parseFloat(values.get(i++));
		pointsRequired = Float.parseFloat(values.get(i++));
		return i;
	}

	
}