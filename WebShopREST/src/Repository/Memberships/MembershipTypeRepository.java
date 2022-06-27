package Repository.Memberships;

import java.util.ArrayList;
import java.util.List;
import Model.Memberships.MembershipType;
import Repository.Repository;
import Repository.Interfaces.Memberships.IMembershipRepository;
import Repository.Interfaces.Memberships.IMembershipTypeRepository;

public class MembershipTypeRepository extends Repository<MembershipType> implements IMembershipTypeRepository {

    private static MembershipTypeRepository instance;
    private String contextString;

    public static MembershipTypeRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new MembershipTypeRepository(contextPath);
        }
        return instance;
    }

    public MembershipTypeRepository(String contextPath) {
        super();
        contextString = contextPath;
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
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<MembershipType> GetAllWithLogicalyDeleted() {
        List<MembershipType> result = new ArrayList<MembershipType>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            MembershipType element = new MembershipType();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(MembershipType element) throws Exception {
        IMembershipRepository membershipRepository = MembershipRepository.getInstance(contextString);
        membershipRepository.CheckIfMembershipTypeIsUsed(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositories(MembershipType element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(MembershipType element) throws Exception {}

}
