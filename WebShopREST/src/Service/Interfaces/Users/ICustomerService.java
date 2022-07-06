package Service.Interfaces.Users;

import Model.Memberships.Membership;
import Model.Trainings.TrainingHistory;
import Model.Users.Customer;

public interface ICustomerService extends IUserService<Customer> {
    
    public void AddMembershipToCustomer(Membership membership) throws Exception;

    public void CheckIfVisitedFacilityAndUpdateMembership(int id, Customer customer) throws Exception;

    public void CheckIfAnotherVisitExistsAndUpdateMembership(int id, TrainingHistory history) throws Exception;
}
