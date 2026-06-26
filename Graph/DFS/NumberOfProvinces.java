import java.util.*;
public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int count = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] vis = new boolean[n];
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                count++;
                stack.push(i);
                vis[i]=true;
                while(!stack.isEmpty()) {
                    int curr = stack.pop();
                    for(int j=0;j<n;j++) {
                        if(isConnected[curr][j] == 1 && !vis[j]) {
                            stack.push(j);
                            vis[j]=true;
                        }
                    }
                }
            }
        } 
        return count;
    }
}
