package Repository.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Model.Facilities.SportFacility;
import Model.Memberships.Membership;
import Model.Users.Customer;
import Model.Users.CustomerType;
import Repository.CommentRepository;
import Repository.Interfaces.ICommentRepository;
import Repository.Interfaces.Memberships.IMembershipRepository;
import Repository.Interfaces.Trainings.ITrainingHistoryRepository;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Memberships.MembershipRepository;
import Repository.Trainings.TrainingHistoryRepository;
import Utilities.DataStructureConverter;

public class CustomerRepository extends UserRepository<Customer> implements ICustomerRepository {

    private static CustomerRepository instance;
    private String contextString;

    public static CustomerRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new CustomerRepository(contextPath);
        }
        return instance;
    }

    public CustomerRepository(String contextPath) {
        super();
        contextString = contextPath;
        this.fileName = contextPath  + "/data/customers.csv";
        InstantiateRepository();
    }

    @Override
    public List<Customer> GetAll() {
        List<Customer> result = new ArrayList<Customer>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Customer element = new Customer();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        result = InstantiateCustomerTypeForCustomers(result);
        return result;
    }

    @Override
    public List<Customer> GetAllWithLogicalyDeleted() {
        List<Customer> result = new ArrayList<Customer>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Customer element = new Customer();
            element.FromCSV(object);
            result.add(element);
        }
        result = InstantiateCustomerTypeForCustomers(result);
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(Customer element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositories(Customer element) throws Exception {
        DeleteDependanciesInOtherRepositoriesPhysically(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(Customer element) throws Exception {
        ICommentRepository commentRepository = CommentRepository.getInstance(contextString);
        IMembershipRepository membershipRepository = MembershipRepository.getInstance(contextString);
        ITrainingHistoryRepository trainingHistoryRepository = TrainingHistoryRepository.getInstance(contextString);
        commentRepository.RemoveCommentsForDeletedCustomer(element);
        membershipRepository.RemoveMembershipsForDeletedCustomer(element);
        trainingHistoryRepository.RemoveHistoryForDeletedCustomer(element);
        
    }

    @Override
    public void RemoveCustomerMembershipsForDeletedMemberhip(Membership deletedMembership) throws Exception {
        List<Customer> customers = GetAll();
        for (Customer customer : customers) {
            if(customer.getMembership().getId() == deletedMembership.getId()){
                customer.setMembership(new Membership());
            }
        }
        serializer.ToCSV(fileName, customers);
    }

    @Override
    public void CheckIfCustomerTypeIsUsed(CustomerType type) throws Exception {
        List<Customer> customers = GetAll();
        for (Customer customer : customers) {
            if(customer.getType().getId() == type.getId()){
                throw new Exception("CustomerType is used");
            }
        }
    }

    @Override
    public void RemoveVisitedSportFacilityForDeletedFacility(SportFacility deletedFacility) throws Exception {
        List<Customer> customers = GetAll();
        for (Customer customer : customers) {
            for (SportFacility facility : customer.getVisitedFacilities()) {
                if(facility.getId() == deletedFacility.getId()){
                    customer.getVisitedFacilities().remove(facility);
                    break;
                }
            }
        }
        serializer.ToCSV(fileName, customers);
    }

    private List<Customer> InstantiateCustomerTypeForCustomers(List<Customer> customers){
        CustomerTypeRepository typeRepository = CustomerTypeRepository.getInstance(contextString);
        DataStructureConverter<CustomerType> converter = new DataStructureConverter<CustomerType>();
        HashMap<Integer, CustomerType> types = converter.ConvertListToMap(typeRepository.GetAll());
        for (Customer customer : customers) {
            if(types.containsKey(customer.getType().getId())){
                customer.setType(types.get(customer.getType().getId()));
            }
        }
        return customers;
    }
}
