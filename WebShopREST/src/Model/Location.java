package Model;

import java.util.*;

/**
 * 
 */
public class Location implements Serializable {

	/**
	 * Default constructor
	 */
	public Location() {
	}

	public Location(float longitude, float latitude, Adress adress) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.adress = adress;
	}

	/**
	 * 
	 */
	private float longitude;

	/**
	 * 
	 */
	private float latitude;

	/**
	 * 
	 */
	private Adress adress;

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Override
	public List<String> ToCSV() {
		ArrayList<String> result = new ArrayList<>();
		result.add(String.valueOf(longitude));
		result.add(String.valueOf(latitude));
		result.addAll(adress.ToCSV());
		return result;
	}

	@Override
	public int FromCSV(List<String> values) {
		int i = 0;
		longitude = Float.valueOf(values.get(i++));
		latitude= Float.valueOf(values.get(i++));
		values = RemoveNElements(i, values);
		adress = new Adress();
		i = i + adress.FromCSV(values);
		return i;
	}

	private List<String> RemoveNElements(int i, List<String> values){
		ArrayList<String> result = new ArrayList<>();
		for (int j = 0; j < values.size(); j++) {
			if(j < i){
				continue;
			}
			result.add(values.get(j));
		}
		return result;
	}

}
