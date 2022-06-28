package Model;

import java.util.ArrayList;
import java.util.List;

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
	protected int id = -1;

	/**
	 * 
	 */
	protected boolean deleted = false;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = 0;
		id = Integer.parseInt(values.get(i++));
		deleted = Boolean.parseBoolean(values.get(i++));
		return i;
	}

	@Override
	public List<String> ToCSV() {
		ArrayList<String> result = new ArrayList<>();
		result.add(String.valueOf(id));
		result.add(String.valueOf(deleted));
		return result;
	}
}