package Repository;

import java.util.*;

/**
 * 
 */
public interface ICrud<T> {

	/**
	 * @param T
	 */
	public void Create(T element);

	/**
	 * @param id
	 */
	public T Read(int id);

	/**
	 * @param T
	 */
	public void Update(T element);

	/**
	 * @param id
	 */
	public void Delete(int id);

	/**
	 * 
	 */
	public List<T> GetAll();

}