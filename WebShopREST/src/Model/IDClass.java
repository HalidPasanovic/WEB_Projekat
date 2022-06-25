package Model;


/**
 * 
 */
public abstract class IDClass implements Serializable {

	/**
	 * Default constructor
	 */
	public IDClass() {
	}

	/**
	 * 
	 */
	protected int id;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}