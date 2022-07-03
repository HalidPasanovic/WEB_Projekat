package Repository.Facilities;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.FacilityType;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;
import Repository.CommentRepository;
import Repository.Repository;
import Repository.Interfaces.ICommentRepository;
import Repository.Interfaces.Facilities.ISportFacilityRepository;
import Repository.Interfaces.Trainings.ITrainingRepository;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Interfaces.Users.IManagerRepository;
import Repository.Trainings.TrainingRepository;
import Repository.Users.CustomerRepository;
import Repository.Users.ManagerRepository;

public class SportFacilityRepository extends Repository<SportFacility> implements ISportFacilityRepository {

    private static SportFacilityRepository instance;
    private String contextString;

    public static SportFacilityRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new SportFacilityRepository(contextPath);
        }
        return instance;
    }

    public SportFacilityRepository(String contextPath) {
        super();
        contextString = contextPath;
        this.fileName = contextPath + "/data/sportFacilities.csv";
        InstantiateRepository();
    }
    
    @Override
    public int CreateAndReturn(SportFacility element)
    {
    	try {
			super.Create(element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	List<SportFacility> f = GetAll();
    	for(SportFacility s : f)
    	{
    		if(s.getName().equals(element.getName()))
    		{
    			return s.getId();
    		}
    	}
    	return -1;
    }

    @Override
    public List<SportFacility> GetAll() {
    	ArrayList<SportFacility> result = new ArrayList<SportFacility>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            SportFacility element = new SportFacility();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<SportFacility> GetAllWithLogicalyDeleted() {
        ArrayList<SportFacility> result = new ArrayList<SportFacility>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            SportFacility element = new SportFacility();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(SportFacility element) throws Exception {
        ITrainingRepository trainingRepository = TrainingRepository.getInstance(contextString);
        trainingRepository.CheckIfSportFacilityIsUsed(element);
        
    }

    @Override
    protected void DeleteDependanciesInOtherRepositories(SportFacility element) throws Exception {
        DeleteDependanciesInOtherRepositoriesPhysically(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(SportFacility element) throws Exception {
        IManagerRepository managerRepository = ManagerRepository.getInstance(contextString);
        ICommentRepository commentRepository = CommentRepository.getInstance(contextString);
        ICustomerRepository customerRepository = CustomerRepository.getInstance(contextString);
        managerRepository.RemoveManagedSportFacilityForDeletedFacility(element);
        commentRepository.RemoveCommentsForDeletedFacility(element);
        customerRepository.RemoveVisitedSportFacilityForDeletedFacility(element);
    }

    @Override
    public void CheckIfFacilityTypeIsUsed(FacilityType type) throws Exception {
        List<SportFacility> facilities = GetAll();
        for (SportFacility sportFacility : facilities) {
            if(sportFacility.getType().getId() == type.getId()){
                throw new Exception("FacilityType is used");
            }
        }
        
    }

    @Override
    public void CheckIfRecreationTypeIsUsed(RecreationType type) throws Exception {
        List<SportFacility> facilities = GetAll();
        for (SportFacility sportFacility : facilities) {
            for (RecreationType recreationType : sportFacility.getRecreationTypes()) {
                if(recreationType.getId() == type.getId()){
                    throw new Exception("RecreationType is used");
                }
            }
        }
    }
}
