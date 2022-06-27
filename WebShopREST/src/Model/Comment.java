package Model;

import java.util.*;

import Model.Facilities.SportFacility;
import Model.Users.Customer;

/**
 * 
 */
public class Comment extends IDClass {

	/**
	 * Default constructor
	 */
	public Comment() {
	}

	/**
	 * 
	 */
	private Customer customer;

	/**
	 * 
	 */
	private SportFacility facility;

	/**
	 * 
	 */
	private String content;

	/**
	 * 
	 */
	private float rating;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SportFacility getFacility() {
		return facility;
	}

	public void setFacility(SportFacility facility) {
		this.facility = facility;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		result.add(String.valueOf(customer.getId()));
		result.add(String.valueOf(facility.getId()));
		result.add(content);
		result.add(String.valueOf(rating));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);

		customer = new Customer();
		customer.setId(Integer.parseInt(values.get(i++)));

		facility = new SportFacility();
		facility.setId(Integer.parseInt(values.get(i++)));
		
		content = values.get(i++);
		rating = Float.parseFloat(values.get(i++));
		return i;
	}

}
