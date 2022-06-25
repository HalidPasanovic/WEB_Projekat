package Repository.Trainings;

import java.util.ArrayList;
import java.util.List;
import Model.Trainings.Training;
import Repository.Repository;
import Repository.Interfaces.Trainings.ITrainingRepository;

public class TrainingRepository extends Repository<Training> implements ITrainingRepository {

    private static TrainingRepository instance;

    public static TrainingRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new TrainingRepository(contextPath);
        }
        return instance;
    }

    public TrainingRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/trainings.csv";
        InstantiateRepository();
    }

    @Override
    public List<Training> GetAll() {
        List<Training> result = new ArrayList<Training>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Training element = new Training();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
