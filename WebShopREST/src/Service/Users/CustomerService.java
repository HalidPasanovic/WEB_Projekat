package Service.Users;

import java.util.HashMap;
import java.util.List;
import Model.Users.Customer;
import Model.Users.User;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Users.CustomerRepository;
import Service.Interfaces.Users.ICustomerService;

public class CustomerService implements ICustomerService {

    private ICustomerRepository repository;
    private String contexString;

    public CustomerService(String contextPath) {
        contexString = contextPath;
        repository = CustomerRepository.getInstance(contextPath);
    }

    @Override
    public void Create(Customer element) throws Exception {
        UserService usernameService = new UserService(contexString);
        usernameService.CheckIfUsernameExists(element.getUsername(), "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        repository.Create(element);
    }

    @Override
    public Customer Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(Customer element, String usernameBefore) throws Exception {
        UserService usernameService = new UserService(contexString);
        usernameService.CheckIfUsernameExists(element.getUsername(), usernameBefore);
        repository.Update(element, usernameBefore);
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
    
}
