import java.util.*;

public class WaterJug {

    public boolean canMeasureWater(int x, int y, int target) {
            Deque<int[]> deq = new ArrayDeque<>(); // (currX, currY)
            deq.offer(new int[]{0,0});
            while(!deq.isEmpty())
            {
                int[] front = deq.getFirst();
                int currX = front[0], currY = front[1];
                if(currX+currY == target)
                    return true;

                
                deq.offer(new int[]{x,currY});
                deq.offer(new int[]{currX,y});
                deq.offer(new int[]{Math.max(0,currX-currY),Math.min(y,currX+currY)});
                deq.offer(new int[]{Math.min(x,currX+currY)});
                deq.offer(new int[]{});
                deq.offer(new int[]{});
            }
            return false;
        }
}