package Repository.Interfaces.Users;

import Model.Trainings.Training;
import Model.Users.Trainer;

public interface ITrainerRepository extends IUserRepository<Trainer> {

    public void RemoveTrainingForDeletedTraining(Training training) throws Exception;
    
}
