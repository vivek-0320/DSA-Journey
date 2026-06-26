import java.util.*;

public class ManyWordSearch {
    public static boolean backtrack(char[][] board, int x, int y, int idx, String target, StringBuilder word) {
        if (idx == target.length() && target.equals(word.toString())) {
            return true;
        }
        if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == target.charAt(idx)) {
            char temp = board[x][y];
            board[x][y] = '#';
            word.append(temp);
            boolean ans = backtrack(board, x + 1, y, idx + 1, target, word) ||
                    backtrack(board, x - 1, y, idx + 1, target, word) ||
                    backtrack(board, x, y + 1, idx + 1, target, word) ||
                    backtrack(board, x, y - 1, idx + 1, target, word);
            word.deleteCharAt(idx);
            board[x][y] = temp;
            return ans;
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        char[][] board = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j] = sc.next().charAt(0);
            }
        }
        int num = sc.nextInt();
        sc.nextLine();
        int count = 0;
        List<String> words = new ArrayList<>();
        for (int x = 0; x < num; x++) {
            String str = sc.nextLine();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (str.charAt(0) == board[i][j]) {
                        StringBuilder word = new StringBuilder();
                        if (backtrack(board, i, j, 0, str, word)) {
                            count++;
                            words.add(str);
                        }
                    }
                }
            }
        }
        System.out.println(count);
        for(String word:words) {
            System.out.println(word);
        }
        sc.close();
    }
}
