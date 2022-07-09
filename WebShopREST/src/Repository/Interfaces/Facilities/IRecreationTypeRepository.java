package Repository.Interfaces.Facilities;

import Model.Facilities.RecreationType;
import Repository.Interfaces.ICrud;

public interface IRecreationTypeRepository extends ICrud<RecreationType> {

	int CreateAndReturn(RecreationType el);
    
}
