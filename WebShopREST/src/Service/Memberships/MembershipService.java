package Service.Memberships;

import java.util.HashMap;
import java.util.List;
import Model.Memberships.Membership;
import Model.Memberships.MembershipType;
import Model.Users.Customer;
import Repository.Interfaces.Memberships.IMembershipRepository;
import Repository.Memberships.MembershipRepository;
import Service.Interfaces.Memberships.IMembershipService;
import Service.Interfaces.Memberships.IMembershipTypeService;
import Service.Interfaces.Users.ICustomerService;
import Service.Users.CustomerService;
import Utilities.DataStructureConverter;

public class MembershipService implements IMembershipService {

    private IMembershipRepository repository;
    private IMembershipTypeService typeService;
    private ICustomerService customerService;

    public MembershipService(String contextPath) {
        repository = MembershipRepository.getInstance(contextPath);
        typeService = new MembershipTypeService(contextPath);
        customerService = new CustomerService(contextPath);
    }

    @Override
    public void Create(Membership element) throws Exception {
        repository.Create(element);
    }

    @Override
    public Membership Read(int id) throws Exception {
        return FindByID(GetAll(), id);
    }

    @Override
    public void Update(Membership element) throws Exception {
        repository.Update(element);
    }

    @Override
    public void Delete(int id) throws Exception {
        repository.Delete(id);
    }

    @Override
    public List<Membership> GetAll() {
        List<Membership> memberships = repository.GetAll();
        memberships = SetMembershipTypeForMemberships(memberships);
        return SetCustomerForMemberships(memberships);
    }

    private List<Membership> SetMembershipTypeForMemberships(List<Membership> memberships){
        DataStructureConverter<MembershipType> converter = new DataStructureConverter<>();
        HashMap<Integer, MembershipType> types = converter.ConvertListToMap(typeService.GetAll());
        for (Membership membership : memberships) {
            if(types.containsKey(membership.getType().getId())){
                membership.setType(types.get(membership.getType().getId()));
            }
        }
        return memberships;
    }

    private List<Membership> SetCustomerForMemberships(List<Membership> memberships){
        DataStructureConverter<Customer> converter = new DataStructureConverter<>();
        HashMap<Integer, Customer> types = converter.ConvertListToMap(customerService.GetAll());
        for (Membership membership : memberships) {
            if(types.containsKey(membership.getBuyer().getId())){
                membership.setBuyer(types.get(membership.getBuyer().getId()));
            }
        }
        return memberships;
    }

    private Membership FindByID(List<Membership> memberships, int id) throws Exception{
        for (Membership membership : memberships) {
            if(membership.getId() == id){
                return membership;
            }
        }
        throw new Exception("Element not found");
    }
    
}
