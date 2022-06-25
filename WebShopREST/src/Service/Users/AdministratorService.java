package Service.Users;

import java.util.List;
import Model.Users.Administrator;
import Repository.Interfaces.Users.IAdministratorRepository;
import Repository.Users.AdministratorRepository;
import Service.Interfaces.Users.IAdministratorService;

public class AdministratorService implements IAdministratorService {

    private IAdministratorRepository repository;

    public AdministratorService(String contextPath) {
        repository = new AdministratorRepository(contextPath + "/data/admins.csv");
    }

    @Override
    public void Create(Administrator element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Administrator Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(Administrator element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Administrator> GetAll() {
        return repository.GetAll();
    }
    
}
