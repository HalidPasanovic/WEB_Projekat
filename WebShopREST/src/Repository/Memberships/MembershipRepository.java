package Repository.Memberships;

import java.util.ArrayList;
import java.util.List;
import Model.Memberships.Membership;
import Repository.Repository;
import Repository.Interfaces.Memberships.IMembershipRepository;

public class MembershipRepository extends Repository<Membership> implements IMembershipRepository {

    private static MembershipRepository instance;

    public static MembershipRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new MembershipRepository(contextPath);
        }
        return instance;
    }

    public MembershipRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/memberships.csv";
        InstantiateRepository();
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
