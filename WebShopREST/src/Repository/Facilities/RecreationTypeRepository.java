package Repository.Facilities;

import java.util.ArrayList;
import java.util.List;
import Model.Facilities.RecreationType;
import Repository.Repository;
import Repository.Interfaces.Facilities.IRecreationTypeRepository;

public class RecreationTypeRepository extends Repository<RecreationType> implements IRecreationTypeRepository  {

    public RecreationTypeRepository(String fileName) {
        super(fileName);
    }

    @Override
    public List<RecreationType> GetAll() {
        List<RecreationType> result = new ArrayList<RecreationType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            RecreationType element = new RecreationType();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
