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
