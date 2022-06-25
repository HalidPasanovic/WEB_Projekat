package Repository.Facilities;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.SportFacility;
import Repository.Repository;
import Repository.Interfaces.Facilities.ISportFacilityRepository;

public class SportFacilityRepository extends Repository<SportFacility> implements ISportFacilityRepository {

    private static SportFacilityRepository instance;

    public static SportFacilityRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new SportFacilityRepository(contextPath);
        }
        return instance;
    }

    public SportFacilityRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/sportFacilities.csv";
        InstantiateRepository();
    }

    @Override
    public List<SportFacility> GetAll() {
    	ArrayList<SportFacility> result = new ArrayList<SportFacility>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            SportFacility element = new SportFacility();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

}
