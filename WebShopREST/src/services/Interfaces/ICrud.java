package services.Interfaces;

import java.util.List;

public interface ICrud<T> {
    
    /**
	 * @param T
	 * @throws Exception
	 */
	public void Create(T element);

	/**
	 * @param id
	 * @throws Exception
	 */
	public T Read(int id);

	/**
	 * @param T
	 * @throws Exception
	 */
	public void Update(T element);

	/**
	 * @param id
	 * @throws Exception
	 */
	public void Delete(int id);

	/**
	 * 
	 */
	public List<T> GetAll();
}
