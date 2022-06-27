package Service.Memberships;

import java.util.List;
import Model.Memberships.MembershipType;
import Repository.Interfaces.Memberships.IMembershipTypeRepository;
import Repository.Memberships.MembershipTypeRepository;
import Service.Interfaces.Memberships.IMembershipTypeService;

public class MembershipTypeService implements IMembershipTypeService {

    private IMembershipTypeRepository repository;

    public MembershipTypeService(String contextPath) {
        repository = MembershipTypeRepository.getInstance(contextPath);
    }

    @Override
    public void Create(MembershipType element) throws Exception {
        repository.Create(element);
    }

    @Override
    public MembershipType Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(MembershipType element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<MembershipType> GetAll() {
        return repository.GetAll();
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        repository.DeletePhysically(id);
    }

    @Override
    public List<MembershipType> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }
    
}
