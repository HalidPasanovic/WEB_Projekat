package Service.Users;

import java.util.HashMap;
import Model.Users.User;
import Service.Interfaces.Users.IAdministratorService;
import Service.Interfaces.Users.ICustomerService;
import Service.Interfaces.Users.IManagerService;
import Service.Interfaces.Users.ITrainerService;

public class UserService {

    private IAdministratorService administratorService;
    private ICustomerService customerService;
    private ITrainerService trainerService;
    private IManagerService managerService;
    private HashMap<String, User> users;

    public UserService(String contextPath) {
        administratorService = new AdministratorService(contextPath);
        customerService = new CustomerService(contextPath);
        trainerService = new TrainerService(contextPath);
        managerService = new ManagerService(contextPath);
        users = administratorService.GetUsers();
        users.putAll(customerService.GetUsers());
        users.putAll(trainerService.GetUsers());
        users.putAll(managerService.GetUsers());
    }

    public void CheckIfUsernameExists(String username) throws Exception {
        if(users.containsKey(username)){
            throw new Exception("Username already exists");
        }
    }

    public User Login(String username, String password) throws Exception {
        if(users.containsKey(username)){
            User user = users.get(username);
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        throw new Exception("Username or password isn't correct");
    }
}
