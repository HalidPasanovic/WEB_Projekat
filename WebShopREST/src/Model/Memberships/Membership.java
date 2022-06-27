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

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDateTime getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(LocalDateTime validUntil) {
		this.validUntil = validUntil;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public int getDailyVisitationCount() {
		return dailyVisitationCount;
	}

	public void setDailyVisitationCount(int dailyVisitationCount) {
		this.dailyVisitationCount = dailyVisitationCount;
	}

	@Override
	public List<String> ToCSV() {
		List<String> result = super.ToCSV();
		result.add(identifier);
		result.add(String.valueOf(type.getId()));
		result.add(String.valueOf(paymentDate));
		result.add(String.valueOf(validUntil));
		result.add(String.valueOf(price));
		result.add(String.valueOf(buyer.getId()));
		result.add(String.valueOf(status));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = super.FromCSV(values);
		identifier = values.get(i++);

		type = new MembershipType();
		type.setId(Integer.parseInt(values.get(i++)));

		paymentDate = LocalDate.parse(values.get(i++));
		validUntil = LocalDateTime.parse(values.get(i++));
		price = Float.parseFloat(values.get(i++));

		buyer = new Customer();
		buyer.setId(Integer.parseInt(values.get(i++)));

		status = Boolean.parseBoolean(values.get(i++));
		return i;
	}

	
}