import java.util.ArrayDeque;
import java.util.Queue;

public class StudentUnableToEatLunch {
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int s:students) queue.add(s);
        int i=0;
        int rotations=0;
        while(!queue.isEmpty() && rotations<queue.size()) {
            if(sandwiches[i] == queue.peek()) {
                i++;
                queue.poll();
                rotations = 0;
            } else {
                queue.add(queue.poll());
                rotations++;
            }
        }
        return queue.size();
    }

    public int countStudents2(int[] students, int[] sandwiches) {
    int count0 = 0, count1 = 0;
    for (int s : students) {
        if (s == 0) count0++;
        else count1++;
    }

    for (int sw : sandwiches) {
        if (sw == 0) {
            if (count0 == 0) break;
            count0--;
        } else {
            if (count1 == 0) break;
            count1--;
        }
    }

    return count0 + count1;
}

}
