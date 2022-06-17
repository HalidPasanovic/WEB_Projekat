package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.CustomerType;
import Repository.Repository;
import Repository.Interfaces.Users.ICustomerTypeRepository;

public class CustomerTypeRepository extends Repository<CustomerType> implements ICustomerTypeRepository {

    public CustomerTypeRepository(String fileName) {
        super(fileName);
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
