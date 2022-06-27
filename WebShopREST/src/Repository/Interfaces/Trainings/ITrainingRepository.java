package Repository.Interfaces.Trainings;

import Model.Facilities.SportFacility;
import Model.Trainings.Training;
import Model.Trainings.TrainingType;
import Model.Users.Trainer;
import Repository.Interfaces.ICrud;

public interface ITrainingRepository extends ICrud<Training> {
    
    public void CheckIfTrainingTypeIsUsed(TrainingType type) throws Exception;

    public void CheckIfSportFacilityIsUsed(SportFacility facility) throws Exception;

    public void CheckIfTrainerIsUsed(Trainer trainer) throws Exception;
}
