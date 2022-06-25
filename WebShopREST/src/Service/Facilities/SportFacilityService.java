package Service.Facilities;

import java.util.HashMap;
import java.util.List;
import Model.Facilities.FacilityType;
import Model.Facilities.RecreationType;
import Model.Facilities.SportFacility;
import Repository.Facilities.SportFacilityRepository;
import Repository.Interfaces.Facilities.ISportFacilityRepository;
import Service.Interfaces.Facilities.IFacilityTypeService;
import Service.Interfaces.Facilities.IRecreationTypeService;
import Service.Interfaces.Facilities.ISportFacilityService;
import Utilities.DataStructureConverter;

public class SportFacilityService implements ISportFacilityService {

    private ISportFacilityRepository repository;
    private IRecreationTypeService typeService;
    private IFacilityTypeService facilityTypeService;

    public SportFacilityService(String contextPath) {
        repository = new SportFacilityRepository(contextPath + "/data/sportFacilities.csv");
        typeService = new RecreationTypeService(contextPath);
        facilityTypeService = new FacilityTypeService(contextPath);
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
        throw new Exception("Element not found");
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
        facilities = SetRecreationTypesForFacilities(facilities);
        return SetFacilityTypeForFacilities(facilities);
    }

    private List<SportFacility> SetRecreationTypesForFacilities(List<SportFacility> facilities){
        DataStructureConverter<RecreationType> converter = new DataStructureConverter<RecreationType>();
        HashMap<Integer, RecreationType> types = converter.ConvertListToMap(typeService.GetAll());
        for (SportFacility facility : facilities) {
            for (RecreationType type : facility.getRecreationTypes()) {
                if(types.containsKey(type.getId())){
                    type.Change(types.get(type.getId()));
                }
            }
        }
        return facilities;
    }

    private List<SportFacility> SetFacilityTypeForFacilities(List<SportFacility> facilities){
        DataStructureConverter<FacilityType> converter = new DataStructureConverter<FacilityType>();
        HashMap<Integer, FacilityType> types = converter.ConvertListToMap(facilityTypeService.GetAll());
        for (SportFacility facility : facilities) {
            if(types.containsKey(facility.getType().getId())){
                facility.setType(types.get(facility.getType().getId()));
            }
        }
        return facilities;
    }

}
