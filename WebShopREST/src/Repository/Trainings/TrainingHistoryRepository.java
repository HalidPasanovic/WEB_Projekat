package Repository.Trainings;

import java.util.ArrayList;
import java.util.List;
import Model.Trainings.TrainingHistory;
import Repository.Repository;
import Repository.Interfaces.Trainings.ITrainingHistoryRepository;

public class TrainingHistoryRepository extends Repository<TrainingHistory> implements ITrainingHistoryRepository {

    public TrainingHistoryRepository(String fileName) {
        super(fileName);
    }

    @Override
    public List<TrainingHistory> GetAll() {
        List<TrainingHistory> result = new ArrayList<TrainingHistory>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            TrainingHistory element = new TrainingHistory();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
