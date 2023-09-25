

import java.util.*;


// Strategy pattern
public class SearchManager {
    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public Set<String> search(String query, InvertedIndex index, ArrayList<String> lines) {
        return strategy.search(query, index, lines);
    }
}

interface SearchStrategy {

    Set<String> search(String query, InvertedIndex index, ArrayList<String> lines);

     default Set<String> getLines(ArrayList<String> lines, List<Integer> linesIndexes) {
        Set<String> foundLines = new HashSet<>();

        for (Integer lineIndex : linesIndexes) {
            foundLines.add(lines.get(lineIndex));
        }
        return foundLines;
    }
}

class SearchAnyStrategy implements SearchStrategy {
    @Override
     public Set<String> search(String query, InvertedIndex index, ArrayList<String> lines) {
        ArrayList<String> words = new ArrayList<>(List.of(query.split(" ")));
        Set<String> foundLines = new HashSet<>();
        for (String word : words) {
            if (index.get(word.toLowerCase()) != null) {
                foundLines.addAll(getLines(lines, index.get(word.toLowerCase())));
            }
        }
        return foundLines;

    }

}

class SearchAllStrategy implements SearchStrategy {
    @Override
    public Set<String> search(String query, InvertedIndex index, ArrayList<String> lines) {
        Set<String> foundLines = new HashSet<>(lines);
        ArrayList<String> words = new ArrayList<>(List.of(query.split(" ")));
        for (String word : words) {
            if (index.get(word.toLowerCase()) != null) {
                foundLines.retainAll(getLines(lines, index.get(word.toLowerCase())));
            }
        }

        return foundLines;
    }

}

class SearchNoneStrategy implements SearchStrategy {
    @Override
    public Set<String> search(String query, InvertedIndex index, ArrayList<String> lines) {
        Set<String> foundLines = new HashSet<>(lines);
        ArrayList<String> words = new ArrayList<>(List.of(query.split(" ")));
        for (String word : words) {
            if (index.get(word.toLowerCase()) != null) {
                foundLines.removeAll(getLines(lines, index.get(word.toLowerCase())));
            }
        }

        return foundLines;
    }

}






