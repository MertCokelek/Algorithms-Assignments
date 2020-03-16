import java.util.HashMap;
import java.util.Map;

public class Cell {

    String source, destination;
    Map<Character, Boolean> map = new HashMap<Character, Boolean>();

    public Cell(String s, String d, char type, boolean edge){
        source = s;
        destination = d;
        map.put(type, edge);
    }
}
