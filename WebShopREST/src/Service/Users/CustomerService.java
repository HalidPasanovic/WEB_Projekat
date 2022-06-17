package Service.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.Customer;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Users.CustomerRepository;
import Service.Interfaces.Users.ICustomerService;

public class CustomerService implements ICustomerService {

    private ICustomerRepository repository = new CustomerRepository("");

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
 
}
