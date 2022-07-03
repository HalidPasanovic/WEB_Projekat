package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Users.Administrator;
import Repository.Interfaces.Users.IAdministratorRepository;

public class AdministratorRepository extends UserRepository<Administrator> implements IAdministratorRepository {

    private static AdministratorRepository instance;

    public static AdministratorRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new AdministratorRepository(contextPath);
        }
        return instance;
    }

    public AdministratorRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/admins.csv";
        InstantiateRepository();
    }

    @Override
    public List<Administrator> GetAll() {
        List<Administrator> result = new ArrayList<Administrator>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Administrator element = new Administrator();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<Administrator> GetAllWithLogicalyDeleted() {
        List<Administrator> result = new ArrayList<Administrator>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Administrator element = new Administrator();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(Administrator element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositories(Administrator element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(Administrator element) throws Exception {}

    @Override
    public int CreateAndReturn(Administrator element) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }
}
