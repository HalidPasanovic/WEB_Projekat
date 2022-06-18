package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Product;

/***
 * Klasa namenjena da u�ita proizvode iz fajla i pru�a operacije nad njima (poput pretrage).
 * Proizvodi se nalaze u fajlu WebContent/products.txt u obliku: <br>
 * id;naziv;jedinicna cena
 * @author Lazar
 *
 */
public class ProductDAO {
	
	private HashMap<String, Product> products = new HashMap<String, Product>();
	
	public ProductDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */
	public ProductDAO(String contextPath) {
		loadProducts(contextPath);
	}

	/***
	 * Vra�a sve proizvode.
	 * @return
	 */
	public Collection<Product> findAll() {
		return products.values();
	}

	/***
	 *  Vraca proizvod na osnovu njegovog id-a. 
	 *  @return Proizvod sa id-em ako postoji, u suprotnom null
	 */
	public Product findProduct(String id) {
		return products.containsKey(id) ? products.get(id) : null;
	}
	
	/***
	 * Dodaje proizvod u mapu proizvoda. Id novog proizvoda �e biti postavljen na maxPostojeciId + 1.
	 * @param product
	 */
	public Product save(Product product) {
		Integer maxId = -1;
		for (String id : products.keySet()) {
			int idNum =Integer.parseInt(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		product.setId(maxId.toString());
		products.put(product.getId(), product);
		return product;
	}
	
	public Product update(String id, Product product) {
		Product productToUpdate = this.findProduct(id);
		if(productToUpdate == null) {
			return this.save(product);
		}
		productToUpdate.setName(product.getName());
		productToUpdate.setPrice(product.getPrice());
		return productToUpdate;
	}
	
	public void delete(String id) {
		this.products.remove(id);
	}

	/**
	 * U�itava korisnike iz WebContent/users.txt fajla i dodaje ih u mapu {@link #products}.
	 * Klju� je id proizovda.
	 * @param contextPath Putanja do aplikacije u Tomcatu
	 */
	private void loadProducts(String contextPath) {
		/*BufferedReader in = null;
		try {
			File file = new File(contextPath + "/products.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, id = "", name = "", price = "";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();
					name = st.nextToken().trim();
					price = st.nextToken().trim();
				}
				products.put(id, new Product(id, name, Double
						.parseDouble(price)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		} */
		/*String line = "";  
		String splitBy = ",";  
		BufferedReader in = null;
		try   
		{  
		//parsing a CSV file into BufferedReader class constructor  
			File file = new File(contextPath + "/products.csv");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file)); 
			StringTokenizer st;
			String id = "", name = "", price = "";
		while ((line = in.readLine()) != null)   //returns a Boolean value  
		{  
			line = line.trim();
			if (line.equals("") || line.indexOf('#') == 0)
				continue;
			st = new StringTokenizer(line, ",");
			while (st.hasMoreTokens()) {
				id = st.nextToken().trim();
				name = st.nextToken().trim();
				price = st.nextToken().trim();
				price = price.substring(0, price.length()-1);
				//System.out.println(id + " " + name + " " + price);
				System.out.println(id);
				System.out.println(name);
				System.out.println(price);
			}
			products.put(id, new Product(id, name, Double
					.parseDouble(price)));
		}  
		}   
		catch (Exception e)   
		{  
		e.printStackTrace();  
		}  
		finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		}  */
		
		String line = "";  
		String splitBy = ",";  
		BufferedReader br = null;
		try   
		{  
		//parsing a CSV file into BufferedReader class constructor  
		br = new BufferedReader(new FileReader(contextPath + "/products.csv"));  
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
		String[] productss = line.split(splitBy);
		products.put(productss[0], new Product(productss[0], productss[1], Double
				.parseDouble(productss[2])));
		}  
		}   
		catch (Exception e)   
		{  
		e.printStackTrace();  
		}
		finally {
			if ( br != null ) {
				try {
					br.close();
				}
				catch (Exception e) { }
			}
		}
	}
}
