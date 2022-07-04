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

	public Membership(String identifier, MembershipType type, String paymentDate,
	String validUntil, Customer buyer, boolean status,
			int usedVisits) {
		this.identifier = identifier;
		this.type = type;
		this.paymentDate = paymentDate;
		this.validUntil = validUntil;
		this.buyer = buyer;
		this.status = status;
		this.usedVisits = usedVisits;
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
	private String paymentDate;

	/**
	 * 
	 */
	private String validUntil;

	/**
	 * 
	 */
	private Customer buyer;

	/**
	 * 
	 */
	private boolean status = true;

	/**
	 * 
	 */
	private int usedVisits = 0;


	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public MembershipType getType() {
		return type;
	}

	public void setType(MembershipType type) {
		this.type = type;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}

	public Customer getBuyer() {
		return buyer;
	}

	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getUsedVisits() {
		return usedVisits;
	}

	public void incrementUsedVisits() {
		usedVisits++;
	}

	public void setUsedVisits(int usedVisits) {
		this.usedVisits = usedVisits;
	}

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		result.add(identifier);
		result.add(String.valueOf(type.getId()));
		result.add(paymentDate);
		result.add(validUntil);
		result.add(String.valueOf(buyer.getId()));
		result.add(String.valueOf(status));
		result.add(String.valueOf(usedVisits));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);
		identifier = values.get(i++);

		type = new MembershipType();
		type.setId(Integer.parseInt(values.get(i++)));

		paymentDate = values.get(i++);
		validUntil = values.get(i++);

		buyer = new Customer();
		buyer.setId(Integer.parseInt(values.get(i++)));

		status = Boolean.parseBoolean(values.get(i++));
		usedVisits = Integer.parseInt(values.get(i++));
		return i;
	}

	
}