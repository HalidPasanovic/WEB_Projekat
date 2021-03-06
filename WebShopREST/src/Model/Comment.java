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

	public Comment(Customer customer, SportFacility facility, String content, float rating) {
		this.customer = customer;
		this.facility = facility;
		this.content = content;
		this.rating = rating;
		this.status = CommentStatus.Pending;
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
	
	private CommentStatus status = CommentStatus.Pending;

	public CommentStatus getStatus() {
		return status;
	}

	public void setStatus(CommentStatus status) {
		this.status = status;
	}

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
		result.add(String.valueOf(status));
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
		if(values.get(i).equals(CommentStatus.Accepted.toString()))
		{
			status = CommentStatus.Accepted;
		}
		else if (values.get(i).equals(CommentStatus.Rejected.toString()))
		{
			status = CommentStatus.Rejected;
		}
		else
		{
			status = CommentStatus.Pending;
		}
		return i;
	}

}
