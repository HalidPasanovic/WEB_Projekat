package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.Administrator;
import Repository.Interfaces.Users.IAdministratorRepository;

public class AdministratorRepository extends UserRepository<Administrator> implements IAdministratorRepository {

    public AdministratorRepository(String fileName) {
        super(fileName);
    }

    @Override
    public List<Administrator> GetAll() {
        List<Administrator> result = new ArrayList<Administrator>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Administrator element = new Administrator();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
