package Repository.Facilities;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;
import Repository.Repository;
import Repository.Interfaces.Facilities.IRecreationTypeRepository;
import Repository.Interfaces.Facilities.ISportFacilityRepository;

public class RecreationTypeRepository extends Repository<RecreationType> implements IRecreationTypeRepository  {

    private static RecreationTypeRepository instance;
    private String contextString;

    public static RecreationTypeRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new RecreationTypeRepository(contextPath);
        }
        return instance;
    }

    public RecreationTypeRepository(String contextPath) {
        super();
        contextString = contextPath;
        this.fileName = contextPath + "/data/recreationTypes.csv";
        InstantiateRepository();
    }

    @Override
    public List<RecreationType> GetAll() {
        List<RecreationType> result = new ArrayList<RecreationType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            RecreationType element = new RecreationType();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }
    
    
    public int CreateAndReturn(RecreationType element)
    {
    	try {
			super.Create(element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	List<RecreationType> f = GetAll();
    	for(RecreationType s : f)
    	{
    		if(s.getName().equals(element.getName()))
    		{
    			return s.getId();
    		}
    	}
    	return -1;
    }

    @Override
    public List<RecreationType> GetAllWithLogicalyDeleted() {
        List<RecreationType> result = new ArrayList<RecreationType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            RecreationType element = new RecreationType();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(RecreationType element) throws Exception {
        ISportFacilityRepository sportFacilityRepository = SportFacilityRepository.getInstance(contextString);
        sportFacilityRepository.CheckIfRecreationTypeIsUsed(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositories(RecreationType element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(RecreationType element) throws Exception {}
    
}
