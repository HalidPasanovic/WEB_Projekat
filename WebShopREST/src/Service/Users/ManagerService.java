package Service.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.Manager;
import Repository.Interfaces.Users.IManagerRepository;
import Repository.Users.ManagerRepository;
import Service.Interfaces.Users.IManagerService;

public class ManagerService implements IManagerService {

    private IManagerRepository repository = new ManagerRepository("");

    @Override
    public void Create(Manager element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Manager Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(Manager element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Manager> GetAll() {
        return repository.GetAll();
    }

}
