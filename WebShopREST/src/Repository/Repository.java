package Repository;

import java.util.*;

import Model.Serializable;

/**
 * 
 */
public abstract class Repository<T extends Serializable> implements ICrud<T> {

	/**
	 * Default constructor
	 */
	public Repository() {
	}

	/**
	 * 
	 */
	public Serializer<T> serializer;

	/**
	 * 
	 */
	protected HashSet<Integer> idMap;

	/**
	 * 
	 */
	protected int currentMaxID;

	/**
	 * @param T
	 */
	public void Create(T element) 
	{
		
	}

	/**
	 * @param id
	 */
	public abstract T Read(int id);

	/**
	 * @param T
	 */
	public abstract void Update(T element);

	/**
	 * @param id
	 */
	public abstract void Delete(int id);

	/**
	 * 
	 */
	public abstract List<T> GetAll();


	public int GenerateId(){
		do {
			++currentMaxID;
		} while (idMap.contains(currentMaxID));
		return currentMaxID;
	}

}