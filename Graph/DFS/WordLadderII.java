import java.util.*;

public class WordLadderII {

    public void dfs(String currentWord, String endWord, List<String> path, HashMap<String, List<String>> adjList,
            List<List<String>> res) {
        if (currentWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (adjList.containsKey(currentWord)) {
            for (String nei : adjList.get(currentWord)) {
                path.add(nei);
                dfs(nei, endWord, path, adjList, res);
                path.remove(path.size() - 1);
            }
        }

    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        HashSet<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord))
            return res;

        HashMap<String, List<String>> patternToWord = new HashMap<>();
        for (String word : dict) {
            StringBuilder pattern = new StringBuilder(word);
            for (int i = 0; i < pattern.length(); i++) {
                char org = pattern.charAt(i);
                pattern.setCharAt(i, '*');
                if (!patternToWord.containsKey(pattern.toString()))
                    patternToWord.put(pattern.toString(), new ArrayList<>());
                patternToWord.get(pattern.toString()).add(word);
                pattern.setCharAt(i, org);
            }
        }

        HashMap<String, List<String>> adjList = new HashMap<>();
        HashSet<String> vis = new HashSet<>();
        Deque<String> deq = new ArrayDeque<>();

        deq.add(beginWord);
        vis.add(beginWord);
        boolean foundEnd = false;

        while (!deq.isEmpty() && !foundEnd) {
            int n = deq.size();
            HashSet<String> currentLevelVisited = new HashSet<>();

            for (int i = 0; i < n; i++) {
                String word = deq.poll();
                StringBuilder pattern = new StringBuilder(word);

                for (int x = 0; x < pattern.length(); x++) {
                    char org = pattern.charAt(x);
                    pattern.setCharAt(x, '*');

                    if (patternToWord.containsKey(pattern.toString())) {
                        for (String nei : patternToWord.get(pattern.toString())) {

                            if (!vis.contains(nei)) {

                                if (!adjList.containsKey(word))
                                    adjList.put(word, new ArrayList<>());
                                adjList.get(word).add(nei);

                                // FIX 4: Mark that we found the end, but DO NOT break yet!
                                // Let the rest of the current level finish mapping their paths to it.
                                if (nei.equals(endWord)) {
                                    foundEnd = true;
                                }

                                // Only push to queue if it's the FIRST time seeing it THIS level
                                if (!currentLevelVisited.contains(nei)) {
                                    currentLevelVisited.add(nei);
                                    deq.add(nei);
                                }
                            }
                        }
                    }
                    pattern.setCharAt(x, org);
                }
            }
            vis.addAll(currentLevelVisited);
        }
        for (String key : adjList.keySet()) {
            System.out.println(key + " -> " + adjList.get(key));
        }

        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, path, adjList, res);

        return res;
    }
}
