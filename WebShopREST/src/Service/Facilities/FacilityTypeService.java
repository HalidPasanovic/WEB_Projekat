package Service.Facilities;

import java.util.List;
import Model.Facilities.FacilityType;
import Repository.Facilities.FacilityTypeRepository;
import Repository.Interfaces.Facilities.IFacilityTypeRepository;
import Service.Interfaces.Facilities.IFacilityTypeService;

public class FacilityTypeService implements IFacilityTypeService  {

    private IFacilityTypeRepository repository;

    public FacilityTypeService(String contextPath) {
    	repository = FacilityTypeRepository.getInstance(contextPath);
	}

	@Override
    public void Create(FacilityType element) throws Exception {
        repository.Create(element);
    }

    @Override
    public FacilityType Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(FacilityType element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<FacilityType> GetAll() {
        return repository.GetAll();
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        repository.DeletePhysically(id);
    }

    @Override
    public List<FacilityType> GetAllWithLogicalyDeleted() {
        return repository.GetAllWithLogicalyDeleted();
    }

}
