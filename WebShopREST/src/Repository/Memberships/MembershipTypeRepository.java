package Repository.Memberships;

import java.util.ArrayList;
import java.util.List;
import Model.Memberships.MembershipType;
import Repository.Repository;
import Repository.Interfaces.Memberships.IMembershipTypeRepository;

public class MembershipTypeRepository extends Repository<MembershipType> implements IMembershipTypeRepository {

    private static MembershipTypeRepository instance;

    public static MembershipTypeRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new MembershipTypeRepository(contextPath);
        }
        return instance;
    }

    public MembershipTypeRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/membershipTypes.csv";
        InstantiateRepository();
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
