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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int FromCSV(List<String> values) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}