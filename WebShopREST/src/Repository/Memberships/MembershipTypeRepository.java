package Repository.Memberships;

import java.util.ArrayList;
import java.util.List;
import Model.Memberships.MembershipType;
import Repository.Repository;
import Repository.Interfaces.Memberships.IMembershipTypeRepository;

public class MembershipTypeRepository extends Repository<MembershipType> implements IMembershipTypeRepository {

    public MembershipTypeRepository(String fileName) {
        super(fileName);
        //TODO Auto-generated constructor stub
    }

    @Override
    public List<MembershipType> GetAll() {
        List<MembershipType> result = new ArrayList<MembershipType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            MembershipType element = new MembershipType();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
