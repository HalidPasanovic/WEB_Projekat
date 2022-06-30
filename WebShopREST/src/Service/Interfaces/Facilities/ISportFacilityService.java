package Service.Interfaces.Facilities;

import java.util.List;
import Model.Comment;
import Model.Facilities.SportFacility;
import Model.Trainings.Training;
import Service.Interfaces.ICrud;

public interface ISportFacilityService extends ICrud<SportFacility> {
    
    public List<Training> GetAllTrainingsForFacility(int id) throws Exception;

    public List<Comment> GetAllCommentsForFacility(int id) throws Exception;
}
