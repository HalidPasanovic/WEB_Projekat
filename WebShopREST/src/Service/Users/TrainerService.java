package Service.Users;

import java.util.HashMap;
import java.util.List;
import Model.Users.Trainer;
import Model.Users.User;
import Repository.Interfaces.Users.ITrainerRepository;
import Repository.Users.TrainerRepository;
import Service.Interfaces.Users.ITrainerService;

public class TrainerService implements ITrainerService {

    private ITrainerRepository repository;
    private String contexString;

    public TrainerService(String contextPath) {
        contexString = contextPath;
        repository = TrainerRepository.getInstance(contextPath);
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

    @Override
    public HashMap<String, User> GetUsers() {
        return repository.GetUsers();
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        repository.DeletePhysically(id);
    }

    @Override
    public List<Trainer> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }
    
}
