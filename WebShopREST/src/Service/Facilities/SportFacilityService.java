package Service.Facilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Model.Facilities.FacilityType;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;
import Repository.Facilities.FacilityTypeRepository;
import Repository.Facilities.RecreationTypeRepository;
import Repository.Facilities.SportFacilityRepository;
import Repository.Interfaces.Facilities.IFacilityTypeRepository;
import Repository.Interfaces.Facilities.IRecreationTypeRepository;
import Repository.Interfaces.Facilities.ISportFacilityRepository;
import Service.Interfaces.Facilities.ISportFacilityService;

public class SportFacilityService implements ISportFacilityService {

    private ISportFacilityRepository repository;
    private IRecreationTypeRepository typeRepository;
    private IFacilityTypeRepository facilityTypeRepository;

    public SportFacilityService(String contextPath, String fileName) {
        repository = new SportFacilityRepository(contextPath + fileName);
        typeRepository = new RecreationTypeRepository(contextPath + "/data/recreationTypes.csv");
        facilityTypeRepository = new FacilityTypeRepository(contextPath + "/data/facilityTypes.csv");
    }

    @Override
    public void Create(SportFacility element) throws Exception {
        repository.Create(element);
    }

    @Override
    public SportFacility Read(int id) throws Exception {
        List<SportFacility> facilities = GetAll();
        for (SportFacility sportFacility : facilities) {
            if(sportFacility.getId() == id){
                return sportFacility;
            }
        }
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
        List<SportFacility> facilities = repository.GetAll();
        List<RecreationType> types = typeRepository.GetAll();
        List<FacilityType> facilityTypes = facilityTypeRepository.GetAll();
        for (SportFacility facility : facilities) {
            for (RecreationType it : facility.getRecreationTypes()) {
                for (RecreationType recreationType : types) {
                    if(it.getId() == recreationType.getId()){
                        it.Change(recreationType);
                    }
                }
            }
            for (FacilityType facilityType : facilityTypes) {
                if(facility.getType().getId() == facilityType.getId()){
                    facility.setType(facilityType);
                }
            }
        }
        return facilities;
    }

}
