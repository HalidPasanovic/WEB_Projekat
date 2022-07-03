package Service.Facilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Model.Comment;
import Model.Facilities.FacilityType;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;
import Model.Trainings.Training;
import Repository.CommentRepository;
import Repository.Facilities.FacilityTypeRepository;
import Repository.Facilities.RecreationTypeRepository;
import Repository.Facilities.SportFacilityRepository;
import Repository.Interfaces.ICommentRepository;
import Repository.Interfaces.Facilities.IFacilityTypeRepository;
import Repository.Interfaces.Facilities.IRecreationTypeRepository;
import Repository.Interfaces.Facilities.ISportFacilityRepository;
import Repository.Interfaces.Trainings.ITrainingRepository;
import Repository.Trainings.TrainingRepository;
import Service.Interfaces.Facilities.IFacilityTypeService;
import Service.Interfaces.Facilities.IRecreationTypeService;
import Service.Interfaces.Facilities.ISportFacilityService;
import Service.Interfaces.Trainings.ITrainingService;
import Service.Trainings.TrainingService;
import Utilities.DataStructureConverter;

public class SportFacilityService implements ISportFacilityService {

    private ISportFacilityRepository repository;
    private IRecreationTypeRepository typeRepository;
    private IFacilityTypeRepository facilityTypeRepository;
    private ICommentRepository commentRepository;
    private ITrainingRepository trainingRepository;
    private String contextString;

    public SportFacilityService(String contextPath) {
        contextString = contextPath;
        repository = SportFacilityRepository.getInstance(contextPath);
        typeRepository = RecreationTypeRepository.getInstance(contextPath);
        facilityTypeRepository = FacilityTypeRepository.getInstance(contextPath);
        commentRepository = CommentRepository.getInstance(contextPath);
        trainingRepository = TrainingRepository.getInstance(contextPath);
    }

    @Override
    public void Create(SportFacility element) throws Exception {
        repository.Create(element);
    }
    
    
    public int CreateAndReturn(SportFacility element) throws Exception {
        return repository.CreateAndReturn(element);
    }

    @Override
    public SportFacility Read(int id) throws Exception {
        List<SportFacility> facilities = GetAll();
        for (SportFacility sportFacility : facilities) {
            if(sportFacility.getId() == id){
                return sportFacility;
            }
        }
        throw new Exception("Element not found");
    }

    @Override
    public void Update(SportFacility element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

	@Override
    public List<SportFacility> GetAll() {
        List<SportFacility> facilities = repository.GetAll();
        facilities = SetRecreationTypesForFacilities(facilities);
        return SetFacilityTypeForFacilities(facilities);
    }

    private List<SportFacility> SetRecreationTypesForFacilities(List<SportFacility> facilities){
        DataStructureConverter<RecreationType> converter = new DataStructureConverter<RecreationType>();
        HashMap<Integer, RecreationType> types = converter.ConvertListToMap(typeRepository.GetAll());
        for (SportFacility facility : facilities) {
            for (RecreationType type : facility.getRecreationTypes()) {
                if(types.containsKey(type.getId())){
                    type.Change(types.get(type.getId()));
                }
            }
        }
        return facilities;
    }

    private List<SportFacility> SetFacilityTypeForFacilities(List<SportFacility> facilities){
        DataStructureConverter<FacilityType> converter = new DataStructureConverter<FacilityType>();
        HashMap<Integer, FacilityType> types = converter.ConvertListToMap(facilityTypeRepository.GetAll());
        for (SportFacility facility : facilities) {
            if(types.containsKey(facility.getType().getId())){
                facility.setType(types.get(facility.getType().getId()));
            }
        }
        return facilities;
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        repository.DeletePhysically(id);
    }

    @Override
    public List<SportFacility> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }

    @Override
    public List<Training> GetAllTrainingsForFacility(int id) throws Exception {
        TrainingService trainingService = new TrainingService(contextString);
        List<Training> result = new ArrayList<>();
        for (Training training : trainingService.GetAll()) {
            if(training.getFacility().getId() == id){
                result.add(training);
            }
        }
        return result;
    }

    @Override
    public List<Comment> GetAllCommentsForFacility(int id) throws Exception {
        List<Comment> result = new ArrayList<>();
        for (Comment comment : commentRepository.GetAll()) {
            if(comment.getFacility().getId() == id){
                result.add(comment);
            }
        }
        return result;
    }

}
