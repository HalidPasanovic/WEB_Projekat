package Repository.Interfaces.Users;

import Model.Facilities.SportFacility;
import Model.Users.Manager;

public interface IManagerRepository extends IUserRepository<Manager> {

    public void RemoveManagedSportFacilityForDeletedFacility(SportFacility deletedFacility) throws Exception;
    
}
