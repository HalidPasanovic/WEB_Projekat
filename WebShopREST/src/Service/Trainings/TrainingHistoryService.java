package Service.Trainings;

import java.util.List;
import Model.Trainings.TrainingHistory;
import Repository.Interfaces.Trainings.ITrainingHistoryRepository;
import Repository.Trainings.TrainingHistoryRepository;
import Service.Interfaces.Trainings.ITrainingHistoryService;

public class TrainingHistoryService implements ITrainingHistoryService {

    private ITrainingHistoryRepository repository;

    public TrainingHistoryService(String fileName) {
        repository = new TrainingHistoryRepository(fileName);
    }

    @Override
    public void Create(TrainingHistory element) throws Exception {
        repository.Create(element);
    }

    @Override
    public TrainingHistory Read(int id) throws Exception {
        return repository.Read(id);
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
        return repository.GetAll();
    }
    
}
