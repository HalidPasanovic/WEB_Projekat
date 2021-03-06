package Service.Facilities;

import java.util.List;
import Model.Facilities.RecreationType;
import Repository.Facilities.RecreationTypeRepository;
import Repository.Interfaces.Facilities.IRecreationTypeRepository;
import Service.Interfaces.Facilities.IRecreationTypeService;

public class RecreationTypeService implements IRecreationTypeService  {

    private IRecreationTypeRepository repository;

    public RecreationTypeService(String contextPath) {
    	repository = RecreationTypeRepository.getInstance(contextPath);
	}

	@Override
    public void Create(RecreationType element) throws Exception {
        repository.Create(element);
    }

    @Override
    public RecreationType Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(RecreationType element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<RecreationType> GetAll() {
        return repository.GetAll();
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        repository.DeletePhysically(id);
    }

    @Override
    public List<RecreationType> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }

	@Override
	public int CreateAndReturn(RecreationType el) throws Exception {
		return repository.CreateAndReturn(el);
	}
    

}
