package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.Trainer;
import Repository.Interfaces.Users.ITrainerRepository;

public class TrainerRepository extends UserRepository<Trainer> implements ITrainerRepository {

    public TrainerRepository(String fileName) {
        super(fileName);
    }

    @Override
    public List<Trainer> GetAll() {
        List<Trainer> result = new ArrayList<Trainer>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Trainer element = new Trainer();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
