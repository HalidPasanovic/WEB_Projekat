package Repository.Interfaces.Trainings;

import Model.Trainings.Training;
import Model.Trainings.TrainingHistory;
import Model.Users.Customer;
import Model.Users.Trainer;
import Repository.Interfaces.ICrud;

public interface ITrainingHistoryRepository extends ICrud<TrainingHistory> {

    public void CheckIfTrainingIsUsed(Training training) throws Exception;

    public void RemoveHistoryForDeletedCustomer(Customer deletedCustomer) throws Exception;

    public void CheckIfTrainerHasHistory(Trainer trainer) throws Exception;
    
}
