import java.util.*;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        int original = image[sr][sc];
        if (original == color)
            return image;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] { sr, sc });
        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int x = curr[0], y = curr[1];
            image[x][y] = color;
            int[] dx = { 1, -1, 0, 0 };
            int[] dy = { 0, 0, 1, -1 };
            for (int k = 0; k < 4; k++) {
                int nx = dx[k] + x;
                int ny = dy[k] + y;
                if (nx > -1 && nx < m && ny > -1 && ny < n && image[nx][ny]==original) {
                    stack.push(new int[] { nx, ny });
                }
            }
        }
        return image;
    }
}
