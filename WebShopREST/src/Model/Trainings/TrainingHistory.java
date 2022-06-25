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
	public TrainingHistory() {}

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
		ArrayList<String> result = new ArrayList<>();
		result.add(String.valueOf(id));
		result.add(String.valueOf(applicationDateTime));
		result.add(String.valueOf(training.getId()));
		result.add(String.valueOf(customer.getId()));
		result.add(String.valueOf(trainer.getId()));
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = 0;
		id = Integer.parseInt(values.get(i++));
		applicationDateTime = LocalDateTime.parse(values.get(i++));

		training = new Training();
		training.setId(Integer.parseInt(values.get(i++)));

		customer = new Customer();
		customer.setId(Integer.parseInt(values.get(i++)));

		trainer = new Trainer();
		trainer.setId(Integer.parseInt(values.get(i++)));
		return i;
	}



}
