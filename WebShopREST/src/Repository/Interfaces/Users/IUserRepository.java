package Repository.Interfaces.Users;

import Model.Users.User;
import Repository.Interfaces.ICrud;

public interface IUserRepository<T extends User> extends ICrud<T> {

}
