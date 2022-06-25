package Service.Trainings;

import java.util.List;
import Model.Trainings.TrainingType;
import Repository.Interfaces.Trainings.ITrainingTypeRepository;
import Repository.Trainings.TrainingTypeRepository;
import Service.Interfaces.Trainings.ITrainingTypeService;

public class TrainingTypeService implements ITrainingTypeService {

    private ITrainingTypeRepository repository;

    public TrainingTypeService(String fileName) {
        repository = new TrainingTypeRepository(fileName);
    }

    @Override
    public void Create(TrainingType element) throws Exception {
        repository.Create(element);
    }

    @Override
    public TrainingType Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(TrainingType element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<TrainingType> GetAll() {
        return repository.GetAll();
    }
    
}
