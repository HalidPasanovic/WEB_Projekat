package Model;

import java.util.List;

public interface Serializable {

    public List<String> ToCSV();

    public int FromCSV(List<String> values);
}
