package Service.Facilities;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.SportFacility;
import Repository.Facilities.SportFacilityRepository;
import Repository.Interfaces.Facilities.ISportFacilityRepository;
import Service.Interfaces.Facilities.ISportFacilityService;

public class SportFacilityService implements ISportFacilityService {

    private ISportFacilityRepository repository = new SportFacilityRepository("");

    @Override
    public void Create(SportFacility element) throws Exception {
        repository.Create(element);
    }

    @Override
    public SportFacility Read(int id) throws Exception {
        return repository.Read(id);
    }

    @Override
    public void Update(SportFacility element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<SportFacility> GetAll() {
        return repository.GetAll();
    }

}
