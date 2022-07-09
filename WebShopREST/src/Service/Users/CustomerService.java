package Service.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Model.Facilities.SportFacility;
import Model.Memberships.Membership;
import Model.Trainings.TrainingHistory;
import Model.Users.Customer;
import Model.Users.User;
import Repository.Interfaces.Memberships.IMembershipRepository;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Memberships.MembershipRepository;
import Repository.Users.CustomerRepository;
import Service.Interfaces.Users.ICustomerService;
import Service.Trainings.TrainingHistoryService;

public class CustomerService implements ICustomerService {

    private ICustomerRepository repository;
    private IMembershipRepository membershipRepository;
    private String contexString;

    public CustomerService(String contextPath) {
        contexString = contextPath;
        repository = CustomerRepository.getInstance(contextPath);
        membershipRepository = MembershipRepository.getInstance(contextPath);
    }

    @Override
    public void Create(Customer element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Customer Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(Customer element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Customer> GetAll() {
        return repository.GetAll();
    }

    @Override
    public HashMap<String, User> GetUsers() {
        return repository.GetUsers();
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        repository.DeletePhysically(id);
    }

    @Override
    public List<Customer> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }

    @Override
    public void AddMembershipToCustomer(Membership membership) throws Exception{
        Customer customer = Read(membership.getBuyer().getId());
        try {
            MembershipRepository.getInstance(contexString).DeletePhysically(customer.getMembership().getId());
        } catch (Exception e) {
            //TODO: handle exception
        }
        customer.setMembership(membership);
        Update(customer);
    }

    @Override
    public void CheckIfVisitedFacilityAndUpdateMembership(int id, Customer customer) throws Exception {
    	Customer customer1 = Read(customer.getId());
        Membership membership = membershipRepository.Read(customer1.getMembership().getId());
        membership.incrementUsedVisits();
        if(membership.getUsedVisits() >= membership.getType().getVisitationCount()){
            membership.setStatus(false);
        }
        membershipRepository.Update(membership);
        boolean found = false;
        for (SportFacility it : customer1.getVisitedFacilities()) {
            if(it.getId() == id){
                found = true;
                break;
            }
        }
        if(!found){
            customer1.getVisitedFacilities().add(new SportFacility(id));
            Update(customer1);
        }
    }

    @Override
    public void CheckIfAnotherVisitExistsAndUpdateMembership(int id, TrainingHistory history)throws Exception {
        Customer customer1 = Read(history.getCustomer().getId());
        Membership membership = membershipRepository.Read(customer1.getMembership().getId());
        membership.reduceUsedVisits();
        if(membership.getUsedVisits() < membership.getType().getVisitationCount()){
            membership.setStatus(true);
        }
        membershipRepository.Update(membership);

        boolean found = false;
        TrainingHistoryService trainingHistoryService = new TrainingHistoryService(contexString);
        List<TrainingHistory> list = trainingHistoryService.GetAll();
        for (TrainingHistory trainingHistory : list) {
            if(trainingHistory.getCustomer().getId() == customer1.getId()){
                if(trainingHistory.getId() != history.getId()){
                    if(trainingHistory.getTraining().getFacility().getId() == history.getTraining().getFacility().getId()){
                        found = true;
                        break;
                    }
                }
            }
        }
        if(!found){
            for (SportFacility sportFacility : customer1.getVisitedFacilities()) {
                if(sportFacility.getId() == history.getTraining().getFacility().getId()){
                    customer1.getVisitedFacilities().remove(sportFacility);
                    break;
                }
            }
            Update(customer1);
        }
    }
    
    public List<Customer> GetAllForFacility(int id) {
    	List<Customer> all = repository.GetAll();
    	List<Customer> list = new ArrayList<Customer>();
    	for(Customer c : all)
    	{
    		for(SportFacility s : c.getVisitedFacilities())
    		{
    			if ( s.getId() == id)
    			{
    				list.add(c);
    				break;
    			}
    		}
    	}
    	
    	return list;
    }
    
}
