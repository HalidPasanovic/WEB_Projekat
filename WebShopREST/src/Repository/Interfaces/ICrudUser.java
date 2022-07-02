package Repository.Interfaces;

import java.util.*;

/**
 * 
 */
public interface ICrudUser<T> {

	/**
	 * @param T
	 * @throws Exception
	 */
	public void Create(T element) throws Exception;
	
	public int CreateAndReturn(T element) throws Exception;

	/**
	 * @param id
	 * @throws Exception
	 */
	public T Read(int id) throws Exception;

	/**
	 * @param T
	 * @throws Exception
	 */
	public void Update(T element, String usernameBefore) throws Exception;

	/**
	 * @param id
	 * @throws Exception
	 */
	public void Delete(int id) throws Exception;

	/**
	 * @param id
	 * @throws Exception
	 */
	public void DeletePhysically(int id) throws Exception;

	/**
	 * 
	 */
	public List<T> GetAll();

	/**
	 * 
	 */
	public List<T> GetAllWithLogicalyDeleted();

}