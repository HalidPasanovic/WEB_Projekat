package Repository.Trainings;

import java.util.ArrayList;
import java.util.List;
import Model.Trainings.Training;
import Repository.Repository;
import Repository.Interfaces.Trainings.ITrainingRepository;

public class TrainingRepository extends Repository<Training> implements ITrainingRepository {

    public TrainingRepository(String fileName) {
        super(fileName);
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
