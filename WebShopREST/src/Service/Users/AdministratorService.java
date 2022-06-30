package Service.Users;

import java.util.HashMap;
import java.util.List;
import Model.Users.Administrator;
import Model.Users.User;
import Repository.Interfaces.Users.IAdministratorRepository;
import Repository.Users.AdministratorRepository;
import Service.Interfaces.Users.IAdministratorService;

public class AdministratorService implements IAdministratorService {

    private IAdministratorRepository repository;
    private String contexString;

    public AdministratorService(String contextPath) {
        contexString = contextPath;
        repository = AdministratorRepository.getInstance(contextPath);
    }

    @Override
    public void Create(Administrator element) throws Exception {
        UserService usernameService = new UserService(contexString);
        usernameService.CheckIfUsernameExists(element.getUsername(), "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        repository.Create(element);
    }

    @Override
    public Administrator Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(Administrator element, String usernameBefore) throws Exception {
        UserService usernameService = new UserService(contexString);
        usernameService.CheckIfUsernameExists(element.getUsername(), usernameBefore);
        repository.Update(element, usernameBefore);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Administrator> GetAll() {
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
    public List<Administrator> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }
    
}
