package Service.Users;

import java.util.HashMap;
import Model.Users.Administrator;
import Model.Users.Customer;
import Model.Users.Manager;
import Model.Users.Trainer;
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
        Instantite();
    }

    private void Instantite(){
        users = administratorService.GetUsers();
        users.putAll(customerService.GetUsers());
        users.putAll(trainerService.GetUsers());
        users.putAll(managerService.GetUsers());
    }

    public boolean CheckIfAdmin(String username){
        return administratorService.GetUsers().containsKey(username);
    }

    public boolean CheckIfCustomer(String username){
        return customerService.GetUsers().containsKey(username);
    }

    public boolean CheckIfTrainer(String username){
        return trainerService.GetUsers().containsKey(username);
    }

    public boolean CheckIfManager(String username){
        return managerService.GetUsers().containsKey(username);
    }

    public boolean ChangeCustomer(User user){
        try {
            customerService.Update((Customer) user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User Login(String username, String password) throws Exception {
        Instantite();
        if(users.containsKey(username)){
            User user = users.get(username);
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        throw new Exception("Username or password isn't correct");
    }
}
