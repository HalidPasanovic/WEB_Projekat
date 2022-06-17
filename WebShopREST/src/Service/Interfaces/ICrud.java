package Service.Interfaces;

import java.util.*;

/**
 * 
 */
public interface ICrud<T> {

	/**
	 * @param T
	 * @throws Exception
	 */
	public void Create(T element) throws Exception;

	/**
	 * @param id
	 * @throws Exception
	 */
	public T Read(int id) throws Exception;

	/**
	 * @param T
	 * @throws Exception
	 */
	public void Update(T element) throws Exception;

	/**
	 * @param id
	 * @throws Exception
	 */
	public void Delete(int id) throws Exception;

	/**
	 * 
	 */
	public List<T> GetAll();

}