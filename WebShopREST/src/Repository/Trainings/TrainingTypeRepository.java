package Repository.Trainings;

import java.util.ArrayList;
import java.util.List;
import Model.Trainings.TrainingType;
import Repository.Repository;
import Repository.Interfaces.Trainings.ITrainingTypeRepository;

public class TrainingTypeRepository extends Repository<TrainingType> implements ITrainingTypeRepository {

    public TrainingTypeRepository(String fileName) {
        super(fileName);
    }

    @Override
    public List<TrainingType> GetAll() {
        List<TrainingType> result = new ArrayList<TrainingType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            TrainingType element = new TrainingType();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
