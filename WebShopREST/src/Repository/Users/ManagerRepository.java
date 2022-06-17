package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.Manager;
import Repository.Interfaces.Users.IManagerRepository;

public class ManagerRepository extends UserRepository<Manager> implements IManagerRepository {

    public ManagerRepository(String fileName) {
        super(fileName);
        //TODO Auto-generated constructor stub
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
