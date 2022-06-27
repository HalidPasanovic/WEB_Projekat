package Repository;

import java.util.*;
import Model.IDClass;
import Repository.Interfaces.ICrud;

/**
 * 
 */
public abstract class Repository<T extends IDClass> implements ICrud<T> {

	/**
	 * 
	 */
	protected Serializer<T> serializer = new Serializer<T>();

	/**
	 * 
	 */
	protected String fileName;

	/**
	 * 
	 */
	protected HashSet<Integer> idMap = new HashSet<>();

	/**
	 * @param T
	 * @throws Exception
	 */
	public void Create(T element) throws Exception {
		element.setId(GenerateId());
		ArrayList<T> result = new ArrayList<>();
		result.add(element);
		serializer.ToCSVAppend(fileName, result);
		idMap.add(element.getId());
	}

	/**
	 * @param id
	 * @throws Exception
	 */
	public T Read(int id) throws Exception {
		CheckIfIdExists(id);
		List<T> elements = GetAll();
		for (T element : elements) {
			if(element.getId() == id){
				return element;
			}
		}
		throw new Exception("Element not found");
	}

	/**
	 * @param T
	 * @throws Exception
	 */
	public void Update(T element) throws Exception {
		CheckIfIdExists(element.getId());
		List<T> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getId() == element.getId()) {
				elements.set(i, element);
				serializer.ToCSV(fileName, elements);
				return;
			}
		}
		throw new Exception("Element not found");
	}

	/**
	 * @param id
	 * @throws Exception
	 */
	public void Delete(int id) throws Exception {
		CheckIfIdExists(id);
		List<T> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			T element = elements.get(i);
			if(element.getId() == id) {
				CheckIfElementEligableForDeletion(element);
				DeleteDependanciesInOtherRepositories(element);
				elements.get(i).setDeleted(true);
				serializer.ToCSV(fileName, elements);
				idMap.remove(id);
				return;
			}
		}
		throw new Exception("Element not found");
	}

	/**
	 * 
	 */
	public abstract List<T> GetAll();


	protected int GenerateId(){
		int id;
		Random random = new Random();
		do {
			id = random.nextInt();
		} while (idMap.contains(id) && id != -1);
		return id;
	}

	protected void CheckIfIdExists(int id) throws Exception{
		if(!idMap.contains(id)){
			throw new Exception("Element not found in idMap");
		}
	}

	protected void InstantiateRepository(){
		InstantiteIDMap(GetAll());
	}

	protected void InstantiteIDMap(List<T> elements){
		for (T element : elements) {
			int id = element.getId();
			idMap.add(id);
		}
	}

	public abstract void CheckIfElementEligableForDeletion(T element) throws Exception;

	public void CheckIfElementEligableForPhysicalDeletion(T element) throws Exception {
		CheckIfElementEligableForDeletion(element);
	}

	protected abstract void DeleteDependanciesInOtherRepositories(T element) throws Exception;

	protected abstract void DeleteDependanciesInOtherRepositoriesPhysically(T element) throws Exception;

	
	public void DeletePhysically(int id) throws Exception {
		CheckIfIdExists(id);
		List<T> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			T element = elements.get(i);
			if(element.getId() == id) {
				CheckIfElementEligableForPhysicalDeletion(element);
				DeleteDependanciesInOtherRepositories(element);
				elements.remove(i);
				serializer.ToCSV(fileName, elements);
				idMap.remove(id);
				return;
			}
		}
		throw new Exception("Element not found");
	}
}