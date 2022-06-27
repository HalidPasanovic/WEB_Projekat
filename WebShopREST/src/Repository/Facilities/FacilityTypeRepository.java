package Repository.Facilities;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.FacilityType;
import Repository.Repository;
import Repository.Interfaces.Facilities.IFacilityTypeRepository;
import Repository.Interfaces.Facilities.ISportFacilityRepository;

public class FacilityTypeRepository extends Repository<FacilityType> implements IFacilityTypeRepository  { 

    private static FacilityTypeRepository instance;
    private String contextString;

    public static FacilityTypeRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new FacilityTypeRepository(contextPath);
        }
        return instance;
    }

    public FacilityTypeRepository(String contextPath) {
        super();
        contextString = contextPath;
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
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<FacilityType> GetAllWithLogicalyDeleted() {
        List<FacilityType> result = new ArrayList<FacilityType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            FacilityType element = new FacilityType();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(FacilityType element) throws Exception {
        ISportFacilityRepository sportFacilityRepository = SportFacilityRepository.getInstance(contextString);
        sportFacilityRepository.CheckIfFacilityTypeIsUsed(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositories(FacilityType element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(FacilityType element) throws Exception {}

    
    
}
