import java.util.Scanner;

public class AIProjectDev {

    public static void solve(int n, int x, int y, int z) {
        int withoutAI = (int) Math.ceil((double) n / (double) (x + y));
        int linesWrittenWhileSetup = x * z;
        int linesLeft = n - linesWrittenWhileSetup;
        int withAI = z + (int) Math.ceil((double) linesLeft / (double) (x + (10 * y)));
        System.out.println(Math.min(withAI, withoutAI));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            int n, x, y, z;
            n = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            solve(n, x, y, z);
        }
    }

}
