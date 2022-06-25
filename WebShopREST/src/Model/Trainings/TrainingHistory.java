package Model.Trainings;

import java.time.LocalDateTime;
import java.util.*;

import Model.IDClass;
import Model.Users.Customer;
import Model.Users.Trainer;

/**
 * 
 */
public class TrainingHistory extends IDClass {

	/**
	 * Default constructor
	 */
	public TrainingHistory() {
	}

	/**
	 * 
	 */
	private LocalDateTime applicationDateTime;

	/**
	 * 
	 */
	private Training training;

	/**
	 * 
	 */
	private Customer customer;

	/**
	 * 
	 */
	private Trainer trainer;

	public LocalDateTime getApplicationDateTime() {
		return applicationDateTime;
	}

	public void setApplicationDateTime(LocalDateTime applicationDateTime) {
		this.applicationDateTime = applicationDateTime;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
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