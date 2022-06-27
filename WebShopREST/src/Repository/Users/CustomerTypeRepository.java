package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.CustomerType;
import Repository.Repository;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Interfaces.Users.ICustomerTypeRepository;

public class CustomerTypeRepository extends Repository<CustomerType> implements ICustomerTypeRepository {

    private static CustomerTypeRepository instance;
    private String contextString;

    public static CustomerTypeRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new CustomerTypeRepository(contextPath);
        }
        return instance;
    }

    public CustomerTypeRepository(String contextPath) {
        super();
        contextString = contextPath;
        this.fileName = contextPath + "/data/customerTypes.csv";
        InstantiateRepository();
    }

    @Override
    public List<CustomerType> GetAll() {
        List<CustomerType> result = new ArrayList<CustomerType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            CustomerType element = new CustomerType();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<CustomerType> GetAllWithLogicalyDeleted() {
        List<CustomerType> result = new ArrayList<CustomerType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            CustomerType element = new CustomerType();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(CustomerType element) throws Exception {
        ICustomerRepository customerRepository = CustomerRepository.getInstance(contextString);
        customerRepository.CheckIfCustomerTypeIsUsed(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositories(CustomerType element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(CustomerType element) throws Exception {}
}
