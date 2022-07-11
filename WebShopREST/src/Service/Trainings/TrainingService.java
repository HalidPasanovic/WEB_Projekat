package Service.Trainings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Model.Facilities.SportFacility;
import Model.Trainings.Training;
import Model.Trainings.TrainingType;
import Model.Users.Trainer;
import Repository.Interfaces.Trainings.ITrainingRepository;
import Repository.Trainings.TrainingRepository;
import Service.Facilities.SportFacilityService;
import Service.Interfaces.Facilities.ISportFacilityService;
import Service.Interfaces.Trainings.ITrainingService;
import Service.Interfaces.Trainings.ITrainingTypeService;
import Service.Interfaces.Users.ITrainerService;
import Service.Users.TrainerService;
import Utilities.DataStructureConverter;

public class TrainingService implements ITrainingService {

    private ITrainingRepository repository;
    private ITrainingTypeService trainingTypeService;
    private ISportFacilityService sportFacilityService;
    private ITrainerService trainerService;
    private String s;

    public TrainingService(String contextPath) {
        repository = TrainingRepository.getInstance(contextPath);
        trainingTypeService = new TrainingTypeService(contextPath);
        sportFacilityService = new SportFacilityService(contextPath);
        trainerService = new TrainerService(contextPath);
        s = contextPath;
    }

    @Override
    public void Create(Training element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Training Read(int id) throws Exception {
        return FindByID(GetAll(), id);
    }

    @Override
    public void Update(Training element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Training> GetAll() {
        List<Training> trainings = repository.GetAll();
        trainings = SetTrainingTypeForTrainings(trainings);
        trainings = SetFacilityForTrainings(trainings);
        return SetTrainerForTrainings(trainings);
    }

    private List<Training> SetTrainingTypeForTrainings(List<Training> trainings){
        DataStructureConverter<TrainingType> converter = new DataStructureConverter<TrainingType>();
        HashMap<Integer, TrainingType> types = converter.ConvertListToMap(trainingTypeService.GetAll());
        for (Training training : trainings) {
            if(types.containsKey(training.getType().getId())){
                training.setType(types.get(training.getType().getId()));
            }
        }
        return trainings;
    }

    private List<Training> SetFacilityForTrainings(List<Training> trainings){
        DataStructureConverter<SportFacility> converter = new DataStructureConverter<SportFacility>();
        HashMap<Integer, SportFacility> facilities = converter.ConvertListToMap(sportFacilityService.GetAll());
        for (Training training : trainings) {
            if(facilities.containsKey(training.getFacility().getId())){
                training.setFacility(facilities.get(training.getFacility().getId()));
            }
        }
        return trainings;
    }

    private List<Training> SetTrainerForTrainings(List<Training> trainings){
        DataStructureConverter<Trainer> converter = new DataStructureConverter<Trainer>();
        HashMap<Integer, Trainer> trainers = converter.ConvertListToMap(trainerService.GetAll());
        for (Training training : trainings) {
            if(trainers.containsKey(training.getTrainer().getId())){
                training.setTrainer(trainers.get(training.getTrainer().getId()));
            }
        }
        return trainings;
    }

    private Training FindByID(List<Training> trainings, int id) throws Exception{
        for (Training training : trainings) {
            if(training.getId() == id){
                return training;
            }
        }
        throw new Exception("Element not found");
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        repository.DeletePhysically(id);
    }

    @Override
    public List<Training> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }
    
    @Override
    public List<Trainer> getTrainersFromFacility(int id) throws Exception {
    	List<Training> tempt = repository.GetAll();
    	List<Trainer> lista = new ArrayList<Trainer>();
    	for(Training t :tempt)
    	{
    		if(t.getFacility().getId() == id)
    		{
    			if(t.getTrainer().getId() != -1)
    			{
    				boolean b = true;
    				TrainerService ts = new TrainerService(s);
    				Trainer ttt = ts.Read(t.getTrainer().getId());
    				for(Trainer trt : lista)
    				{
    					if(trt.getId() == ttt.getId())
    					{
    						b = false;
    					}
    				}
    				if(b)
    				{
    					lista.add(ttt);
    				}
    			}
    		}
    	}
    	return lista;
    }
    
    @Override
    public List<Training> getTrainingsForFacility(int id) throws Exception {
    	
    	List<Training> all = GetAll();
    	List<Training> list = new ArrayList<Training>();
    	
    	for(Training t : all)
    	{
    		if(t.getFacility().getId() == id)
    		{
    			list.add(t);
    		}
    	}
    	return list;
    }
    
}
