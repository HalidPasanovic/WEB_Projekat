package Service.Trainings;

import java.util.List;
import Model.Trainings.Training;
import Repository.Interfaces.Trainings.ITrainingRepository;
import Repository.Trainings.TrainingRepository;
import Service.Interfaces.Trainings.ITrainingService;

public class TrainingService implements ITrainingService {

    private ITrainingRepository repository;

    public TrainingService(String fileName) {
        repository = new TrainingRepository(fileName);
    }

    @Override
    public void Create(Training element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Training Read(int id) throws Exception {
        return repository.Read(id);
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
        return repository.GetAll();
    }
    
}
