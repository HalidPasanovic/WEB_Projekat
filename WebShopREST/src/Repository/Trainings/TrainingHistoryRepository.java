package Repository.Trainings;

import java.util.ArrayList;
import java.util.List;
import Model.Trainings.Training;
import Model.Trainings.TrainingHistory;
import Model.Users.Customer;
import Model.Users.Trainer;
import Repository.Repository;
import Repository.Interfaces.Trainings.ITrainingHistoryRepository;

public class TrainingHistoryRepository extends Repository<TrainingHistory> implements ITrainingHistoryRepository {

    private static TrainingHistoryRepository instance;
    private String contextString;

    public static TrainingHistoryRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new TrainingHistoryRepository(contextPath);
        }
        return instance;
    }

    public TrainingHistoryRepository(String contextPath) {
        super();
        contextString = contextPath;
        this.fileName = contextPath + "/data/trainingHistories.csv";
        InstantiateRepository();
    }

    @Override
    public List<TrainingHistory> GetAll() {
        List<TrainingHistory> result = new ArrayList<TrainingHistory>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            TrainingHistory element = new TrainingHistory();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<TrainingHistory> GetAllWithLogicalyDeleted() {
        List<TrainingHistory> result = new ArrayList<TrainingHistory>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            TrainingHistory element = new TrainingHistory();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(TrainingHistory element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositories(TrainingHistory element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(TrainingHistory element) throws Exception {}

    @Override
    public void CheckIfTrainingIsUsed(Training training) throws Exception {
        List<TrainingHistory> trainingHistories = GetAll();
        for (TrainingHistory trainingHistory : trainingHistories) {
            if(trainingHistory.getTraining().getId() == training.getId()){
                throw new Exception("Training is used");
            }
        }
        
    }

    @Override
    public void RemoveHistoryForDeletedCustomer(Customer deletedCustomer) throws Exception {
        List<TrainingHistory> trainingHistories = GetAll();
        List<TrainingHistory> deletedHistories = new ArrayList<>();
        for (TrainingHistory trainingHistory : trainingHistories) {
            if(trainingHistory.getCustomer().getId() == deletedCustomer.getId()){
                deletedHistories.add(trainingHistory);
            }
        }
        trainingHistories.removeAll(deletedHistories);
        serializer.ToCSV(fileName, trainingHistories);
    }

    @Override
    public void CheckIfTrainerHasHistory(Trainer trainer) throws Exception {
        List<TrainingHistory> trainingHistories = GetAll();
        for (TrainingHistory trainingHistory : trainingHistories) {
            if(trainingHistory.getTrainer().getId() == trainer.getId()){
                throw new Exception("Trainer is used");
            }
        }
    }
}
