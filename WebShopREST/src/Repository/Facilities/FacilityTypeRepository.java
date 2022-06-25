package Repository.Facilities;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.FacilityType;
import Repository.Repository;
import Repository.Interfaces.Facilities.IFacilityTypeRepository;

public class FacilityTypeRepository extends Repository<FacilityType> implements IFacilityTypeRepository  { 

    private static FacilityTypeRepository instance;

    public static FacilityTypeRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new FacilityTypeRepository(contextPath);
        }
        return instance;
    }

    public FacilityTypeRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/facilityTypes.csv";
        InstantiateRepository();
    }

    @Override
    public List<FacilityType> GetAll() {
        List<FacilityType> result = new ArrayList<FacilityType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            FacilityType element = new FacilityType();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
