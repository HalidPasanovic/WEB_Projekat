package Repository.Memberships;

import java.util.ArrayList;
import java.util.List;
import Model.Memberships.Membership;
import Repository.Repository;
import Repository.Interfaces.Memberships.IMembershipRepository;

public class MembershipRepository extends Repository<Membership> implements IMembershipRepository {

    public MembershipRepository(String fileName) {
        super(fileName);
        //TODO Auto-generated constructor stub
    }

    @Override
    public List<Membership> GetAll() {
        List<Membership> result = new ArrayList<Membership>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Membership element = new Membership();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }
    
}
