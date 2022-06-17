package Service.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.CustomerType;
import Repository.Interfaces.Users.ICustomerTypeRepository;
import Repository.Users.CustomerTypeRepository;
import Service.Interfaces.Users.ICustomerTypeService;

public class CustomerTypeService implements ICustomerTypeService {

    private ICustomerTypeRepository repository = new CustomerTypeRepository("");

    @Override
    public void Create(CustomerType element) throws Exception {
        repository.Create(element);
    }

    @Override
    public CustomerType Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(CustomerType element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<CustomerType> GetAll() {
        return repository.GetAll();
    }

}
