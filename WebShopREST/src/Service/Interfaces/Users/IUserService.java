package Service.Interfaces.Users;

import java.util.HashMap;
import Model.Users.User;
import Service.Interfaces.ICrud;
import Service.Interfaces.ICrudUser;

public interface IUserService<T extends User> extends ICrudUser<T> {

    public HashMap<String, User> GetUsers();
}
