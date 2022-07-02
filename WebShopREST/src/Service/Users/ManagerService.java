package Service.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Facilities.SportFacility;
import Model.Users.Manager;
import Model.Users.User;
import Repository.Interfaces.Users.IManagerRepository;
import Repository.Users.ManagerRepository;
import Service.Interfaces.Users.IManagerService;

public class ManagerService implements IManagerService {

    private IManagerRepository repository;
    private String contexString;

    public ManagerService(String contextPath) {
        contexString = contextPath;
        repository = ManagerRepository.getInstance(contextPath);
    }

    @Override
    public void Create(Manager element) throws Exception {
        UserService usernameService = new UserService(contexString);
        usernameService.CheckIfUsernameExists(element.getUsername(), "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        repository.Create(element);
    }
    
    public int CreateAndReturn(Manager element) throws Exception {
        return repository.CreateAndReturn(element);
    }

    @Override
    public Manager Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(Manager element, String usernameBefore) throws Exception {
        UserService usernameService = new UserService(contexString);
        usernameService.CheckIfUsernameExists(element.getUsername(), usernameBefore);
        repository.Update(element, usernameBefore);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Manager> GetAll() {
        return repository.GetAll();
    }
    
    public List<Manager> GetAllEmpty() {
    	List<Manager> temp =  repository.GetAll();
    	List<Manager> f = new ArrayList<Manager>();
    	for(Manager m : temp)
    	{
    		if(m.getFacilities().size() == 0)
    		{
    			f.add(m);
    		}
    	}
    	return f;
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
    public List<Manager> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }
    
}
