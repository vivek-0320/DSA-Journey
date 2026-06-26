
// The Celebrity Problem can be stated like this:
// You are at a party with n people (labeled 0 to n-1). A celebrity is a person who is known by everyone but knows no one.
// You are given access to a helper function knows(a, b) that returns:
// true if person a knows person b
// false otherwise.

// Your task is to find the celebrity's label (index) if one exists, or return -1 if there is no celebrity.
// The goal is to find the celebrity using the minimum number of knows() calls.
// Constraints of the problem:
// Celebrity conditions:
// Everyone except the celebrity knows the celebrity:

// For all i ≠ celebrity, knows(i, celebrity) == true
// Celebrity knows no one:

// For all i, knows(celebrity, i) == false
// Time complexity goal: O(n) calls to knows() is possible with an optimal approach.

// Input: Only through the knows(a, b) API

public class CelebrityProblem {
    int[][] relation;

    CelebrityProblem(int[][] relation) {
        this.relation = relation;
    }

    private boolean knows(int a, int b) {
        return relation[a][b] == 1;
    }

    public int findCelebrity(int n) {
        // Deque<Integer> stack = new ArrayDeque<>();
        // for (int i = 0; i < n; i++) {
        // stack.push(i);
        // }
        // while (stack.size() > 1) {
        // int b = stack.pop();
        // int a = stack.pop();
        // if (knows(a, b))
        // stack.push(b);
        // else
        // stack.push(a);
        // }
        // int tempCeleb = stack.pop();
        int tempCeleb = 0;
        for (int i = 1; i < n; i++) {
            if (knows(tempCeleb, i))
                tempCeleb = i;
        }

        for (int i = 0; i < n; i++) {
            if (i != tempCeleb && (!knows(i, tempCeleb) || knows(tempCeleb, i)))
                return -1;
        }
        return tempCeleb;
    }

    public static void main(String[] args) {
        int[][][] tests = {
                {
                        { 0, 1, 0 },
                        { 0, 0, 0 },
                        { 0, 1, 0 }
                },
                {
                        { 0, 1, 0 },
                        { 1, 0, 0 },
                        { 0, 1, 0 }
                },
                {
                        { 0, 1, 1, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 0, 1, 0 }
                },
                {
                        { 0, 1, 1, 0 },
                        { 0, 0, 1, 0 },
                        { 1, 0, 0, 0 },
                        { 1, 0, 1, 0 }
                },
                {
                        { 0 }
                }
        };
        int[] expected = { 1, -1, 2, -1, 0 };

        for (int i = 0; i < tests.length; i++) {
            CelebrityProblem cp = new CelebrityProblem(tests[i]);
            int ans = cp.findCelebrity(tests[i].length);
            System.out.printf("Test %d: Output=%d, Expected=%d\n",
                    i + 1, ans, expected[i]);
        }
    }
}
