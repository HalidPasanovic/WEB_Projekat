package Repository.Interfaces.Users;

import java.util.HashMap;
import Model.Users.User;
import Repository.Interfaces.ICrud;

public interface IUserRepository<T extends User> extends ICrud<T> {

    public HashMap<String, User> GetUsers();
}
