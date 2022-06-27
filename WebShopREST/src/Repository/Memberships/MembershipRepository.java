package Repository.Memberships;

import java.util.ArrayList;
import java.util.List;
import Model.Memberships.Membership;
import Model.Memberships.MembershipType;
import Model.Users.Customer;
import Repository.Repository;
import Repository.Interfaces.Memberships.IMembershipRepository;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Users.CustomerRepository;

public class MembershipRepository extends Repository<Membership> implements IMembershipRepository {

    private static MembershipRepository instance;
    private String contextString;

    public static MembershipRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new MembershipRepository(contextPath);
        }
        return instance;
    }

    public MembershipRepository(String contextPath) {
        super();
        contextString = contextPath;
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
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public List<Membership> GetAllWithLogicalyDeleted() {
        List<Membership> result = new ArrayList<Membership>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            Membership element = new Membership();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(Membership element) throws Exception {}

    @Override
    protected void DeleteDependanciesInOtherRepositories(Membership element) throws Exception {
        DeleteDependanciesInOtherRepositoriesPhysically(element);
    }

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(Membership element) throws Exception {
        ICustomerRepository customerRepository = CustomerRepository.getInstance(contextString);
        customerRepository.RemoveCustomerMembershipsForDeletedMemberhip(element);
    }

    @Override
    public void CheckIfMembershipTypeIsUsed(MembershipType type) throws Exception {
        List<Membership> memberships = GetAll();
        for (Membership membership : memberships) {
            if(membership.getType().getId() == type.getId()){
                throw new Exception("MembershipType is used");
            }
        }
        
    }

    @Override
    public void RemoveMembershipsForDeletedCustomer(Customer deletedCustomer) throws Exception {
        List<Membership> memberships = GetAll();
        List<Membership> deletedMemberships = new ArrayList<>();
        for (Membership membership : memberships) {
            if(membership.getBuyer().getId() == deletedCustomer.getId()){
                deletedMemberships.add(membership);
            }
        }
        memberships.removeAll(deletedMemberships);
        serializer.ToCSV(fileName, memberships);
    }
}
