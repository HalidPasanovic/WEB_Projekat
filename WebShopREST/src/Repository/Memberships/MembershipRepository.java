package Repository.Memberships;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import Model.Memberships.Membership;
import Model.Memberships.MembershipType;
import Model.Users.Customer;
import Model.Users.CustomerType;
import Repository.Repository;
import Repository.Interfaces.Memberships.IMembershipRepository;
import Repository.Interfaces.Users.ICustomerRepository;
import Repository.Users.CustomerRepository;
import Repository.Users.CustomerTypeRepository;
import Utilities.DataStructureConverter;

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
        codeLenght = 10;
        InstantiateRepository();
    }

    /**
	 * 
	 */
	private HashSet<String> codeMap = new HashSet<>();

    /**
	 * 
	 */
    private int codeLenght;

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
        SetMembershipTypeForMemberships(result);
        try {
            result = CheckExpirationDateOfAll(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Membership> SetMembershipTypeForMemberships(List<Membership> memberships){
        MembershipTypeRepository typeRepository = MembershipTypeRepository.getInstance(contextString);
        DataStructureConverter<MembershipType> converter = new DataStructureConverter<>();
        HashMap<Integer, MembershipType> types = converter.ConvertListToMap(typeRepository.GetAll());
        for (Membership membership : memberships) {
            if(types.containsKey(membership.getType().getId())){
                membership.setType(types.get(membership.getType().getId()));
            }
        }
        return memberships;
    }

    private List<Membership> CheckExpirationDateOfAll(List<Membership> memberships) throws Exception{
        CustomerRepository customerRepository = CustomerRepository.getInstance(contextString);
        LocalDate now = LocalDate.now();
        for (Membership membership : memberships) {
            if(!membership.isStatus()){
                continue;
            }
            LocalDate expiration = LocalDate.parse(membership.getValidUntil());
            if(expiration.isBefore(now)){
                membership.setStatus(false);
                Customer customer = customerRepository.Read(membership.getBuyer().getId());
                if(membership.getType().getVisitationCount()/3 > membership.getUsedVisits()){
                    customer.addPoints(-((membership.getType().getPrice()/1000) * 133 * 4));
                    ChangeCustomerTypeIfRequired(customer);
                } else {
                    customer.addPoints((membership.getType().getPrice()/1000) * membership.getUsedVisits());
                    ChangeCustomerTypeIfRequired(customer);
                }
                customerRepository.Update(customer);
            }
        }
        serializer.ToCSV(fileName, memberships);
        return memberships;
    }

    private void ChangeCustomerTypeIfRequired(Customer customer){
        CustomerTypeRepository customerTypeRepository = CustomerTypeRepository.getInstance(contextString);
        List<CustomerType> types = customerTypeRepository.GetAllSortedAscending();
        for (CustomerType customerType : types) {
            if(customer.getPoints() >= customerType.getPointsRequired()){
                customer.setType(customerType);
            }
        }
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
        SetMembershipTypeForMemberships(result);
        try {
            result = CheckExpirationDateOfAll(result);
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public void Create(Membership element) throws Exception {
        element.setId(GenerateId());
        element.setIdentifier(GenerateCode());
		ArrayList<Membership> result = new ArrayList<>();
		result.add(element);
		serializer.ToCSVAppend(fileName, result);
		idMap.add(element.getId());
        codeMap.add(element.getIdentifier());
    }

    @Override
    public void Delete(int id) throws Exception {
        CheckIfIdExists(id);
		List<Membership> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			Membership element = elements.get(i);
			if(element.getId() == id) {
				CheckIfElementEligableForDeletion(element);
				DeleteDependanciesInOtherRepositories(element);
				elements.get(i).setDeleted(true);
				serializer.ToCSV(fileName, elements);
				idMap.remove(id);
                codeMap.remove(element.getIdentifier());
				return;
			}
		}
		throw new Exception("Element not found");
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        CheckIfIdExists(id);
		List<Membership> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			Membership element = elements.get(i);
			if(element.getId() == id) {
				CheckIfElementEligableForPhysicalDeletion(element);
				DeleteDependanciesInOtherRepositories(element);
				elements.remove(i);
				serializer.ToCSV(fileName, elements);
				idMap.remove(id);
                codeMap.remove(element.getIdentifier());
				return;
			}
		}
		throw new Exception("Element not found");
    }

    @Override
    protected void InstantiateRepository() {
        super.InstantiateRepository();
        InstantiteCodeDictionary(GetAll());
    }

    @Override
    public void Update(Membership element) throws Exception {
        CheckIfIdExists(element.getId());
        Membership temp = Read(element.getId());
        if(!temp.getIdentifier().equals(element.getIdentifier())){
            CheckIfCodeExists(element.getIdentifier());
        }
		List<Membership> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getId() == element.getId()) {
				elements.set(i, element);
				serializer.ToCSV(fileName, elements);
                codeMap.remove(temp.getIdentifier());
                codeMap.add(element.getIdentifier());
				return;
			}
		}
		throw new Exception("Element not found");
    }

    private void CheckIfCodeExists(String code) throws Exception{
        if(codeMap.contains(code)){
            throw new Exception("Element not found in idMap");
        }
    }

    protected void InstantiteCodeDictionary(List<Membership> codes){
        for (Membership promoCode : codes) {
            codeMap.add(promoCode.getIdentifier());
        }
    }

    private String GenerateCode() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(codeLenght);

        do {
            // create StringBuffer size of AlphaNumericString
            sb = new StringBuilder(codeLenght);
            for (int i = 0; i < codeLenght; i++) {
                
                // generate a random number between
                // 0 to AlphaNumericString variable length
                int index
                    = (int)(AlphaNumericString.length()
                            * Math.random());
      
                // add Character one by one in end of sb
                sb.append(AlphaNumericString
                              .charAt(index));
            }
        } while (codeMap.contains(sb.toString()));
        return sb.toString();
    }
}
