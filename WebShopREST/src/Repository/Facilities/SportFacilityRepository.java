package Repository.Facilities;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.SportFacility;
import Repository.Repository;
import Repository.Interfaces.Facilities.ISportFacilityRepository;

public class SportFacilityRepository extends Repository<SportFacility> implements ISportFacilityRepository {

    public SportFacilityRepository(String fileName) {
        super(fileName);
    }

    @Override
    public List<SportFacility> GetAll() {
        List<SportFacility> result = new ArrayList<SportFacility>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            SportFacility element = new SportFacility();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

}
