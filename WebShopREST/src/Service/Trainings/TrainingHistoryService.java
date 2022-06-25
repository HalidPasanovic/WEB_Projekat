package Service.Trainings;

import java.util.HashMap;
import java.util.List;
import Model.Trainings.Training;
import Model.Trainings.TrainingHistory;
import Model.Users.Customer;
import Model.Users.Trainer;
import Repository.Interfaces.Trainings.ITrainingHistoryRepository;
import Repository.Trainings.TrainingHistoryRepository;
import Service.Interfaces.Trainings.ITrainingHistoryService;
import Service.Interfaces.Trainings.ITrainingService;
import Service.Interfaces.Users.ICustomerService;
import Service.Interfaces.Users.ITrainerService;
import Service.Users.CustomerService;
import Service.Users.TrainerService;
import Utilities.DataStructureConverter;

public class TrainingHistoryService implements ITrainingHistoryService {

    private ITrainingHistoryRepository repository;
    private ITrainingService trainingService;
    private ICustomerService customerService;
    private ITrainerService trainerService;

    public TrainingHistoryService(String contextPath) {
        repository = new TrainingHistoryRepository(contextPath + "/data/trainingHistories.csv");
        trainingService = new TrainingService(contextPath);
        customerService = new CustomerService(contextPath);
        trainerService = new TrainerService(contextPath);
    }

    @Override
    public void Create(TrainingHistory element) throws Exception {
        repository.Create(element);
    }

    @Override
    public TrainingHistory Read(int id) throws Exception {
        return FindByID(GetAll(), id);
    }

    @Override
    public void Update(TrainingHistory element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<TrainingHistory> GetAll() {
        List<TrainingHistory> histories = repository.GetAll();
        histories = SetTrainingForTrainingHistories(histories);
        histories = SetTrainerForTrainingHistories(histories);
        return SetCustomerForTrainingHistories(histories);
    }

    private List<TrainingHistory> SetTrainingForTrainingHistories(List<TrainingHistory> histories){
        DataStructureConverter<Training> converter = new DataStructureConverter<Training>();
        HashMap<Integer, Training> trainings = converter.ConvertListToMap(trainingService.GetAll());
        for (TrainingHistory trainingHistory : histories) {
            if(trainings.containsKey(trainingHistory.getTraining().getId())){
                trainingHistory.setTraining(trainings.get(trainingHistory.getTraining().getId()));
            }
        }
        return histories;
    }

    private List<TrainingHistory> SetCustomerForTrainingHistories(List<TrainingHistory> histories){
        DataStructureConverter<Customer> converter = new DataStructureConverter<Customer>();
        HashMap<Integer, Customer> customers = converter.ConvertListToMap(customerService.GetAll());
        for (TrainingHistory trainingHistory : histories) {
            if(customers.containsKey(trainingHistory.getCustomer().getId())){
                trainingHistory.setCustomer(customers.get(trainingHistory.getCustomer().getId()));
            }
        }
        return histories;
    }

    private List<TrainingHistory> SetTrainerForTrainingHistories(List<TrainingHistory> histories){
        DataStructureConverter<Trainer> converter = new DataStructureConverter<Trainer>();
        HashMap<Integer, Trainer> trainers = converter.ConvertListToMap(trainerService.GetAll());
        for (TrainingHistory trainingHistory : histories) {
            if(trainers.containsKey(trainingHistory.getTrainer().getId())){
                trainingHistory.setTrainer(trainers.get(trainingHistory.getTrainer().getId()));
            }
        }
        return histories;
    }

    private TrainingHistory FindByID(List<TrainingHistory> histories, int id) throws Exception{
        for (TrainingHistory trainingHistory : histories) {
            if(trainingHistory.getId() == id){
                return trainingHistory;
            }
        }
        throw new Exception("Element not found");
    }
    
}
