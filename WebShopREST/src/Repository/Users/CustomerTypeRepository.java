package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.CustomerType;
import Repository.Repository;
import Repository.Interfaces.Users.ICustomerTypeRepository;

public class CustomerTypeRepository extends Repository<CustomerType> implements ICustomerTypeRepository {

    private static CustomerTypeRepository instance;

    public static CustomerTypeRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new CustomerTypeRepository(contextPath);
        }
        return instance;
    }

    public CustomerTypeRepository(String contextPath) {
        super();
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
            result.add(element);
        }
        return result;
    }
    
}
