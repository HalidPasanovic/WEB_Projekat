package Service.Users;

import java.util.HashMap;
import java.util.List;
import Model.Memberships.Membership;
import Model.Users.Customer;
import Model.Users.User;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Memberships.MembershipRepository;
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
    
}
