package Service.Users;

import java.util.List;
import Model.Users.Trainer;
import Repository.Interfaces.Users.ITrainerRepository;
import Repository.Users.TrainerRepository;
import Service.Interfaces.Users.ITrainerService;

public class TrainerService implements ITrainerService {

    private ITrainerRepository repository;

    public TrainerService(String contextPath) {
        repository = new TrainerRepository(contextPath + "/data/trainers.csv");
    }

    @Override
    public void Create(Trainer element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Trainer Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(Trainer element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Trainer> GetAll() {
        return repository.GetAll();
    }
    
}
