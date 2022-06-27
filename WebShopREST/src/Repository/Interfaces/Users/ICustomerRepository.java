package Repository.Interfaces.Users;

import Model.Facilities.SportFacility;
import Model.Memberships.Membership;
import Model.Users.Customer;
import Model.Users.CustomerType;

public interface ICustomerRepository extends IUserRepository<Customer> {
    
    public void RemoveCustomerMembershipsForDeletedMemberhip(Membership deletedMembership) throws Exception;

    public void CheckIfCustomerTypeIsUsed(CustomerType type) throws Exception;

    public void RemoveVisitedSportFacilityForDeletedFacility(SportFacility deletedFacility) throws Exception;
    
}
