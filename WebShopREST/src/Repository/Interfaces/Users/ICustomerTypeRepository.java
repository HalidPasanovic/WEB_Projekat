package Repository.Interfaces.Users;

import java.util.List;
import Model.Users.CustomerType;
import Repository.Interfaces.ICrud;

public interface ICustomerTypeRepository extends ICrud<CustomerType> {

    public List<CustomerType> GetAllSortedAscending();
 
}
