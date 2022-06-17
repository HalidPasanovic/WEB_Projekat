package Repository.Users;

import java.util.HashMap;
import java.util.List;
import Model.Users.User;
import Repository.Repository;

public abstract class UserRepository<T extends User> extends Repository<T> {

    public UserRepository(String fileName) {
        super();
        this.fileName = fileName;
        List<T> elements = GetAll();
        InstantiteIDMapAndMaxID(elements);
        InstantiteUserDictionary(elements);
    }

    /**
	 * 
	 */
	protected HashMap<String, User> userDictionary = new HashMap<String, User>();
    
    /**
	 * 
	 */
    protected void InstantiteUserDictionary(List<T> elements){
        for (T element : elements) {
			userDictionary.put(element.getUsername(), element);
		}
    }

    @Override
    public void Create(T element) throws Exception {
        CheckIfUsernameExists(element.getUsername());
        super.Create(element);
        userDictionary.put(element.getUsername(), element);
    }

    @Override
    public void Delete(int id) throws Exception {
        CheckIfIdExists(id);
		List<T> elements = GetAll();
		for (int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getId() == id) {
                String username = elements.get(i).getUsername();
				elements.remove(i);
				serializer.ToCSV(fileName, elements);
				idMap.remove(id);
                userDictionary.remove(username);
				return;
			}
		}
		throw new Exception("Element not found");
    }

    @Override
    public void Update(T element) throws Exception {
        CheckIfUsernameExists(element.getUsername());
        super.Update(element);
    }

    protected void CheckIfUsernameExists(String username) throws Exception {
        if(userDictionary.containsKey(username)){
            throw new Exception("Username already exists");
        }
    }
}
