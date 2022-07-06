package Repository.Interfaces.Facilities;

import Model.Facilities.FacilityType;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;
import Repository.Interfaces.ICrud;

public interface ISportFacilityRepository extends ICrud<SportFacility> {

    public void CheckIfFacilityTypeIsUsed(FacilityType type) throws Exception;

    public void CheckIfRecreationTypeIsUsed(RecreationType type) throws Exception;

    public int CreateAndReturn(SportFacility element) throws Exception;
    
}
