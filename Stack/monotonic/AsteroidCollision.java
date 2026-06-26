import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class AsteroidCollision {
    public static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            boolean destroyed = false;
            // Collision only happens if stack top is moving right and current asteroid is
            // moving left
            while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                int lastAsteroid = stack.peek();
                if (lastAsteroid < -asteroid) {
                    stack.pop(); // stack asteroid destroyed , check for more
                    continue;
                } else if (lastAsteroid == -asteroid) {
                    stack.pop(); // both destroyed
                }
                destroyed = true; // current asteroid destroyed
                break;
            }

            if (!destroyed) {
                stack.push(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] testInputs = {
                { 5, 10, -5 },
                { 8, -8 },
                { 10, 2, -5 },
                { -2, -1, 1, 2 },
                { 1, -2, -2, -2 },
                { 5, -10 },
                { 3, 4, -9 },
                { 10, -2, -5, -15 },
                { 1, 2, 3, -3 },
                { 4, 3, 2, 1, -10 }
        };

        int[][] expectedOutputs = {
                { 5, 10 },
                {},
                { 10 },
                { -2, -1, 1, 2 },
                { -2, -2, -2 },
                { -10 },
                { -9 },
                { -15 },
                { 1, 2 },
                { -10 }
        };

        for (int i = 0; i < testInputs.length; i++) {
            int[] result = asteroidCollision(testInputs[i]);
            System.out.printf("Test %d: Input %s → Output %s | Expected %s %s%n",
                    i + 1,
                    Arrays.toString(testInputs[i]),
                    Arrays.toString(result),
                    Arrays.toString(expectedOutputs[i]),
                    Arrays.equals(result, expectedOutputs[i]) ? "✅" : "❌");
        }
    }
}
