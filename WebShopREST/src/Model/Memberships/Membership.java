package Model.Memberships;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import Model.IDClass;
import Model.Users.Customer;

/**
 * 
 */
public class Membership extends IDClass {

	/**
	 * Default constructor
	 */
	public Membership() {
	}

	/**
	 * 
	 */
	private String identifier;

	/**
	 * 
	 */
	private MembershipType type;

	/**
	 * 
	 */
	private LocalDate paymentDate;

	/**
	 * 
	 */
	private LocalDateTime validUntil;

	/**
	 * 
	 */
	private float price;

	/**
	 * 
	 */
	private Customer buyer;

	/**
	 * 
	 */
	private boolean status;

	/**
	 * 
	 */
	private int dailyVisitationCount;

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