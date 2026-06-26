import java.util.*;

public class MazeProblems {

    int maximumWeight(int[] ar, int n) {

        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            if (ar[i] == -1)
                continue;
            weights[ar[i]] += i;
        }

        int maxNode = 0;
        for (int i = 0; i < n; i++) {
            if (weights[i] >= weights[maxNode]) {
                maxNode = i;
            }
        }
        return maxNode;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++)
            ar[i] = sc.nextInt();

        MazeProblems ob = new MazeProblems();
        int s = ob.maximumWeight(ar, n);
        System.out.println("Maximum Weight is of node : " + s);
        sc.close();
    }
}
