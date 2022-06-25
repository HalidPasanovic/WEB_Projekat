package Service.Interfaces.Users;

import java.util.HashMap;
import Model.Users.User;
import Service.Interfaces.ICrud;

public interface IUserService<T extends User> extends ICrud<T> {

    public HashMap<String, User> GetUsers();
}
