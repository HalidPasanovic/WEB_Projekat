package Utilities;

import java.util.HashMap;
import java.util.List;
import Model.IDClass;

public class DataStructureConverter<T extends IDClass> {

    public HashMap<Integer, T> ConvertListToMap(List<T> list){
        HashMap<Integer, T> result = new HashMap<>();
        for (T idClass : list) { result.put(idClass.getId(), idClass); }
        return result;
    }
    
}
