package Repository.Interfaces.Users;

import Model.Users.Administrator;

public interface IAdministratorRepository extends IUserRepository<Administrator> {
    
    public int CreateAndReturn(Administrator element) throws Exception;

}
