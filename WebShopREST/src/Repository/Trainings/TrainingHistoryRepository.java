package Repository.Trainings;

import java.util.ArrayList;
import java.util.List;
import Model.Trainings.TrainingHistory;
import Repository.Repository;
import Repository.Interfaces.Trainings.ITrainingHistoryRepository;

public class TrainingHistoryRepository extends Repository<TrainingHistory> implements ITrainingHistoryRepository {

    private static TrainingHistoryRepository instance;

    public static TrainingHistoryRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new TrainingHistoryRepository(contextPath);
        }
        return instance;
    }

    public TrainingHistoryRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/trainingHistories.csv";
        InstantiateRepository();
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
