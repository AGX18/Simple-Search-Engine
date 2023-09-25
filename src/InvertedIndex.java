import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvertedIndex {

    HashMap<String, List<Integer>> myMap;

    public InvertedIndex() {
        myMap = new HashMap<>();

    }

    public void add(String word, int lineNumber) {
        // Check if the key exists in the map
        if (!myMap.containsKey(word)) {
            // If not, create a new list and associate it with the key
            myMap.put(word, new ArrayList<>());
        }
        // Add the value to the list associated with the key
        myMap.get(word).add(lineNumber);
    }

    public List<Integer> get(String word) {
        if(!myMap.containsKey(word)) {
            return new ArrayList<>();
        }
        return myMap.get(word);
    }



}
