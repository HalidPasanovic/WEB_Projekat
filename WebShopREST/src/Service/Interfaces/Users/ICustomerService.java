package Service.Interfaces.Users;

import Model.Memberships.Membership;
import Model.Users.Customer;

public interface ICustomerService extends IUserService<Customer> {
    
    public void AddMembershipToCustomer(Membership membership) throws Exception;
}
