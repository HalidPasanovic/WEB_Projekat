package Service.Interfaces.Facilities;

import Model.Facilities.RecreationType;
import Service.Interfaces.ICrud;

public interface IRecreationTypeService extends ICrud<RecreationType> {
    
	public int CreateAndReturn(RecreationType el) throws Exception;
}
