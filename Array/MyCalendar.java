import java.util.ArrayList;

class MyCalendar {

    ArrayList<int[]> timeline;

    public MyCalendar() {
        timeline = new ArrayList<>();
    }

    public boolean book(int startTime, int endTime) {
        int low = -1;
        int high = timeline.size();

        // Binary search to find the correct sorted position
        // 'low' will end up at the largest start time that is <= startTime
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (timeline.get(mid)[0] <= startTime)
                low = mid;
            else
                high = mid;
        }

        // Check for overlap with the previous event (if there is one)
        if (low != -1 && startTime < timeline.get(low)[1]) {
            return false;
        }

        // Check for overlap with the next event (if there is one) , high is exactly low + 1
        if (high < timeline.size() && endTime > timeline.get(high)[0]) {
            return false;
        }

        // Insert directly at the 'high' index.
        // This shifts subsequent elements over and guarantees the list stays perfectly sorted!
        timeline.add(high, new int[] { startTime, endTime });
        return true;
    }
}