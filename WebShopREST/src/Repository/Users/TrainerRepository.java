package Repository.Users;

import java.util.ArrayList;
import java.util.List;
import Model.Trainings.Training;
import Model.Users.Trainer;
import Repository.Interfaces.Trainings.ITrainingHistoryRepository;
import Repository.Interfaces.Trainings.ITrainingRepository;
import Repository.Interfaces.Users.ITrainerRepository;
import Repository.Trainings.TrainingHistoryRepository;
import Repository.Trainings.TrainingRepository;

public class TrainerRepository extends UserRepository<Trainer> implements ITrainerRepository {

    private static TrainerRepository instance;
    private String contextString;

    public static TrainerRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new TrainerRepository(contextPath);
        }
        return instance;
    }

    public TrainerRepository(String contextPath) {
        super();
        contextString = contextPath;
        this.fileName = contextPath + "/data/trainers.csv";
        InstantiateRepository();
    }

    @Override
    public List<Trainer> GetAll() {
        List<Trainer> result = new ArrayList<Trainer>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Trainer element = new Trainer();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<Trainer> GetAllWithLogicalyDeleted() {
        List<Trainer> result = new ArrayList<Trainer>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Trainer element = new Trainer();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(Trainer element) throws Exception {
        ITrainingRepository trainingRepository = TrainingRepository.getInstance(contextString);
        ITrainingHistoryRepository trainingHistoryRepository = TrainingHistoryRepository.getInstance(contextString);
        trainingRepository.CheckIfTrainerIsUsed(element);
        trainingHistoryRepository.CheckIfTrainerHasHistory(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositories(Trainer element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(Trainer element) throws Exception {}

    @Override
    public void RemoveTrainingForDeletedTraining(Training deletedTraining) throws Exception {
        List<Trainer> trainers = GetAll();
        for (Trainer trainer : trainers) {
            for (Training training : trainer.getTrainings()) {
                if(training.getId() == deletedTraining.getId()){
                    trainer.getTrainings().remove(training);
                    break;
                }
            }
        }
        serializer.ToCSV(fileName, trainers);
    }
}
