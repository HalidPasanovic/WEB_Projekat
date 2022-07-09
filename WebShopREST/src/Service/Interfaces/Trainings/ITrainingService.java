package Service.Interfaces.Trainings;

import java.util.List;

import Model.Trainings.Training;
import Model.Users.Trainer;
import Service.Interfaces.ICrud;

public interface ITrainingService extends ICrud<Training> {
	
	public List<Trainer> getTrainersFromFacility(int id) throws Exception;
    
	public List<Training> getTrainingsForFacility(int id) throws Exception;
}
