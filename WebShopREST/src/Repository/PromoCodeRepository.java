package Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import Model.PromoCode;
import Repository.Interfaces.IPromoCodeRepository;

public class PromoCodeRepository extends Repository<PromoCode> implements IPromoCodeRepository{

    private static PromoCodeRepository instance;

    public static PromoCodeRepository getInstance(String contextPath) {
        if (instance == null) {
            instance = new PromoCodeRepository(contextPath);
        }
        return instance;
    }

    public PromoCodeRepository(String contextPath) {
        super();
        this.fileName = contextPath + "/data/promoCodes.csv";
        InstantiateRepository();
    }

    /**
	 * 
	 */
	private HashSet<String> codeMap = new HashSet<>();


    @Override
    public List<PromoCode> GetAllWithLogicalyDeleted() {
        List<PromoCode> result = new ArrayList<PromoCode>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            PromoCode element = new PromoCode();
            element.FromCSV(object);
            result.add(element);
        }
        return result;
    }

    @Override
    public List<PromoCode> GetAll() {
        List<PromoCode> result = new ArrayList<PromoCode>();
    	List<List<String>> objects = serializer.FromCSV(fileName);
        for (List<String> object : objects) {
            PromoCode element = new PromoCode();
            element.FromCSV(object);
            if(!element.isDeleted()){
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public void CheckIfElementEligableForDeletion(PromoCode element) throws Exception {
    }

    @Override
    protected void DeleteDependanciesInOtherRepositories(PromoCode element) throws Exception {
    }

    @Override
    protected void DeleteDependanciesInOtherRepositoriesPhysically(PromoCode element)
            throws Exception {
    }

    @Override
    public void Create(PromoCode element) throws Exception {
        element.setId(GenerateId());
		ArrayList<PromoCode> result = new ArrayList<>();
		result.add(element);
		serializer.ToCSVAppend(fileName, result);
		idMap.add(element.getId());
        codeMap.add(element.getCode());
    }

    @Override
    public void Delete(int id) throws Exception {
        CheckIfIdExists(id);
		List<PromoCode> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			PromoCode element = elements.get(i);
			if(element.getId() == id) {
				CheckIfElementEligableForDeletion(element);
				DeleteDependanciesInOtherRepositories(element);
				elements.get(i).setDeleted(true);
				serializer.ToCSV(fileName, elements);
				idMap.remove(id);
                codeMap.remove(element.getCode());
				return;
			}
		}
		throw new Exception("Element not found");
    }

    @Override
    public void DeletePhysically(int id) throws Exception {
        CheckIfIdExists(id);
		List<PromoCode> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			PromoCode element = elements.get(i);
			if(element.getId() == id) {
				CheckIfElementEligableForPhysicalDeletion(element);
				DeleteDependanciesInOtherRepositories(element);
				elements.remove(i);
				serializer.ToCSV(fileName, elements);
				idMap.remove(id);
                codeMap.remove(element.getCode());
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
    public void Update(PromoCode element) throws Exception {
        PromoCode temp = Read(element.getId());
        if(!temp.getCode().equals(element.getCode())){
            CheckIfCodeExists(element.getCode());
        }
        CheckIfIdExists(element.getId());
		List<PromoCode> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getId() == element.getId()) {
				elements.set(i, element);
                codeMap.remove(temp.getCode());
                codeMap.add(element.getCode());
				serializer.ToCSV(fileName, elements);
				return;
			}
		}
		throw new Exception("Element not found");
    }

    protected void InstantiteCodeDictionary(List<PromoCode> codes){
        for (PromoCode promoCode : codes) {
            codeMap.add(promoCode.getCode());
        }
    }
    
    private void CheckIfCodeExists(String code) throws Exception{
        if(codeMap.contains(code)){
            throw new Exception("Element not found in idMap");
        }
    }

}
