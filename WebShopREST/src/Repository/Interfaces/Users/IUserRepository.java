package Repository.Interfaces.Users;

import java.util.HashMap;
import Model.Users.User;
import Repository.Interfaces.ICrudUser;

public interface IUserRepository<T extends User> extends ICrudUser<T> {

    public HashMap<String, User> GetUsers();
}
