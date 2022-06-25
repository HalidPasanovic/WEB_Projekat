package Service.Memberships;

import java.util.List;
import Model.Memberships.Membership;
import Repository.Interfaces.Memberships.IMembershipRepository;
import Repository.Memberships.MembershipRepository;
import Service.Interfaces.Memberships.IMembershipService;

public class MembershipService implements IMembershipService {

    private IMembershipRepository repository;

    public MembershipService(String fileName) {
        repository = new MembershipRepository(fileName);
    }

    @Override
    public void Create(Membership element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Membership Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(Membership element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Membership> GetAll() {
        return repository.GetAll();
    }
    
}
