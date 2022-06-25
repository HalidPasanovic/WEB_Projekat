package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.Trainer;
import Repository.Interfaces.Users.ITrainerRepository;

public class TrainerRepository extends UserRepository<Trainer> implements ITrainerRepository {

    private static TrainerRepository instance;

    public static TrainerRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new TrainerRepository(contextPath);
        }
        return instance;
    }

    public TrainerRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/trainers.csv";
        InstantiateRepository();
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
