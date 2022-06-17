package Repository;

import java.util.*;
import Model.IDClass;
import Repository.Interfaces.ICrud;

/**
 * 
 */
public abstract class Repository<T extends IDClass> implements ICrud<T> {

	/**
	 * Default constructor
	 */
	public Repository(String fileName) {
		this.fileName = fileName;
		InstantiteIDMapAndMaxID(GetAll());
	}

	/**
	 * Empty constructor.
	 * Only used for efficient instantiation of UserRepository.
	 * Dont use for instantiating normal repositories.
	 */
	public Repository() {}

	/**
	 * 
	 */
	protected Serializer<T> serializer;

	/**
	 * 
	 */
	protected String fileName;

	/**
	 * 
	 */
	protected HashSet<Integer> idMap = new HashSet<Integer>();

	/**
	 * 
	 */
	protected int currentMaxID;

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
			if(elements.get(i).getId() == id) {
				elements.remove(i);
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
		do {
			++currentMaxID;
		} while (idMap.contains(currentMaxID));
		return currentMaxID;
	}

	protected void CheckIfIdExists(int id) throws Exception{
		if(!idMap.contains(id)){
			throw new Exception("Element not found in idMap");
		}
	}

	protected void InstantiteIDMapAndMaxID(List<T> elements){
		currentMaxID = elements.get(0).getId();
		for (T element : elements) {
			int id = element.getId();
			idMap.add(id);
			if(currentMaxID < id){
				currentMaxID = id;
			}
		}
	}
}