import java.util.*;

public class AccountsMerge {

    class DSU {
        int[] parent;
        int[] size;

        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            if (parent[i] == i)
                return i;
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                // Union by Size: attach smaller tree to larger tree
                if (size[rootI] < size[rootJ]) {
                    parent[rootI] = rootJ;
                    size[rootJ] += size[rootI];
                } else {
                    parent[rootJ] = rootI;
                    size[rootI] += size[rootJ];
                }
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU(10001); // Sufficient size or dynamic based on unique emails

        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailToId = new HashMap<>();

        int id = 0;

        // Step 1: Map every email to a unique ID and a Name
        for (List<String> account : accounts) {

            String name = account.get(0);

            for (int i = 1; i < account.size(); i++) {

                String email = account.get(i);

                if (!emailToId.containsKey(email)) {

                    emailToId.put(email, id++);
                    emailToName.put(email, name);

                }
                // Union all emails in the same account to the first email
                dsu.union(emailToId.get(account.get(1)), emailToId.get(email));
            }
        }

        // Step 2: Group emails by their ultimate Root ID
        Map<Integer, List<String>> components = new HashMap<>();

        for (String email : emailToId.keySet()) {
            int root = dsu.find(emailToId.get(email));
            components.computeIfAbsent(root, x -> new ArrayList<>()).add(email);
        }

        // Step 3: Format the final output (Sort emails and add Name)
        List<List<String>> res = new ArrayList<>();
        for (List<String> component : components.values()) {
            Collections.sort(component);
            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(emailToName.get(component.get(0))); // Add the name
            mergedAccount.addAll(component); // Add sorted emails
            res.add(mergedAccount);
        }

        return res;
    }
}
