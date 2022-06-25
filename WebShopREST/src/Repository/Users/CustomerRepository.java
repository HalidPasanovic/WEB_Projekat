package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.Customer;
import Repository.Interfaces.Users.ICustomerRepository;

public class CustomerRepository extends UserRepository<Customer> implements ICustomerRepository {

    private static CustomerRepository instance;

    public static CustomerRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new CustomerRepository(contextPath);
        }
        return instance;
    }

    public CustomerRepository(String contextPath) {
        super();
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
            result.add(element);
        }
        return result;
    }
    
}
