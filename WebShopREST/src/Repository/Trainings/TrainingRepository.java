package Repository.Trainings;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.SportFacility;
import Model.Trainings.Training;
import Model.Trainings.TrainingType;
import Model.Users.Trainer;
import Repository.Repository;
import Repository.Interfaces.Trainings.ITrainingHistoryRepository;
import Repository.Interfaces.Trainings.ITrainingRepository;
import Repository.Interfaces.Users.ITrainerRepository;
import Repository.Users.TrainerRepository;

public class TrainingRepository extends Repository<Training> implements ITrainingRepository {

    private static TrainingRepository instance;
    private String contextString;

    public static TrainingRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new TrainingRepository(contextPath);
        }
        return instance;
    }

    public TrainingRepository(String contextPath) {
        super();
        contextString = contextPath;
        this.fileName = contextPath + "/data/trainings.csv";
        InstantiateRepository();
    }

    @Override
    public List<Training> GetAll() {
        List<Training> result = new ArrayList<Training>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Training element = new Training();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<Training> GetAllWithLogicalyDeleted() {
        List<Training> result = new ArrayList<Training>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Training element = new Training();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(Training element) throws Exception {
        ITrainingHistoryRepository trainingHistoryRepository = TrainingHistoryRepository.getInstance(contextString);
        trainingHistoryRepository.CheckIfTrainingIsUsed(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositories(Training element) throws Exception {
        DeleteDependanciesInOtherRepositoriesPhysically(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(Training element) throws Exception {
        ITrainerRepository trainerRepository = TrainerRepository.getInstance(contextString);
        trainerRepository.RemoveTrainingForDeletedTraining(element);
    }

    @Override
    public void CheckIfTrainingTypeIsUsed(TrainingType type) throws Exception {
        List<Training> trainings = GetAll();
        for (Training training : trainings) {
            if(training.getType().getId() == type.getId()){
                throw new Exception("TrainingType is used");
            }
        }
    }

    @Override
    public void CheckIfSportFacilityIsUsed(SportFacility facility) throws Exception {
        List<Training> trainings = GetAll();
        for (Training training : trainings) {
            if(training.getFacility().getId() == facility.getId()){
                throw new Exception("SportFacility is used");
            }
        }
    }

    @Override
    public void CheckIfTrainerIsUsed(Trainer trainer) throws Exception {
        List<Training> trainings = GetAll();
        for (Training training : trainings) {
            if(training.getTrainer().getId() == trainer.getId()){
                throw new Exception("Trainer is used");
            }
        }
    }
}
