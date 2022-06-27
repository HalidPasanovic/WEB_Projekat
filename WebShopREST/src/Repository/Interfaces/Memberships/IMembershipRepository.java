package Repository.Interfaces.Memberships;

import Model.Memberships.Membership;
import Model.Memberships.MembershipType;
import Model.Users.Customer;
import Repository.Interfaces.ICrud;

public interface IMembershipRepository extends ICrud<Membership> {
    
    public void CheckIfMembershipTypeIsUsed(MembershipType type) throws Exception;

    public void RemoveMembershipsForDeletedCustomer(Customer deletedCustomer) throws Exception;
}
