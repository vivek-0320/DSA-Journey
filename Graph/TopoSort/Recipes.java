import java.util.*;

public class Recipes {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        int r = recipes.length;

        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < r; i++) {
            String recipe = recipes[i];
            for (int j = 0; j < ingredients.get(i).size(); j++) {
                String ingredient = ingredients.get(i).get(j);

                inDegree.put(recipe, inDegree.getOrDefault(recipe, 0) + 1);

                map.putIfAbsent(ingredient, new ArrayList<>());
                map.get(ingredient).add(recipe);
            }
        }

        Deque<String> deq = new ArrayDeque<>();
        for (int i = 0; i < supplies.length; i++) {
            deq.add(supplies[i]);
        }

        List<String> res = new ArrayList<>();

        // 2. Process the Queue (Kahn's Algorithm)
        while (!deq.isEmpty()) {
            String curr = deq.poll();

            for (String recipe : map.getOrDefault(curr, new ArrayList<>())) {
                inDegree.put(recipe, inDegree.get(recipe) - 1);
                if (inDegree.get(recipe) == 0) {
                    res.add(recipe);
                    deq.add(recipe);
                }
            }
        }
        return res;
    }
}
