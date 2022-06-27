package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.SportFacility;
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
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<Manager> GetAllWithLogicalyDeleted() {
        List<Manager> result = new ArrayList<Manager>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Manager element = new Manager();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(Manager element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositories(Manager element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(Manager element) throws Exception {}

    @Override
    public void RemoveManagedSportFacilityForDeletedFacility(SportFacility deletedFacility) throws Exception {
        List<Manager> managers = GetAll();
        for (Manager manager : managers) {
            for (SportFacility facility : manager.getFacilities()) {
                if(facility.getId() == deletedFacility.getId()){
                    manager.getFacilities().remove(facility);
                    break;
                }
            }
        }
        serializer.ToCSV(fileName, managers);
    }
}
