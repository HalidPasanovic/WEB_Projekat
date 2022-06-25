package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.Manager;
import Repository.Interfaces.Users.IManagerRepository;

public class ManagerRepository extends UserRepository<Manager> implements IManagerRepository {

    private static ManagerRepository instance;

    public static ManagerRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new ManagerRepository(contextPath);
        }
        return instance;
    }

    public ManagerRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/managers.csv";
        InstantiateRepository();
    }

    @Override
    public List<Manager> GetAll() {
        List<Manager> result = new ArrayList<Manager>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Manager element = new Manager();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
