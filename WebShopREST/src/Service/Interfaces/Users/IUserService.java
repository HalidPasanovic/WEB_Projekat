package Service.Interfaces.Users;

import Model.Users.User;
import Service.Interfaces.ICrud;

public interface IUserService<T extends User> extends ICrud<T> {

}
