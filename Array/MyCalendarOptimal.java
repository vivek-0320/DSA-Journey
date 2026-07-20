import java.util.TreeMap;

class MyCalendarOptimal {
    // Maps startTime -> endTime
    TreeMap<Integer, Integer> calendar;

    public MyCalendarOptimal() {
        calendar = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        // floorKey gets the greatest start time <= startTime (equivalent to your 'low')
        Integer prev = calendar.floorKey(startTime);
        
        // ceilingKey gets the smallest start time >= startTime (similar to your 'high')
        Integer next = calendar.ceilingKey(startTime);

        // Check overlap with previous event
        if (prev != null && calendar.get(prev) > startTime) {
            return false;
        }

        // Check overlap with next event
        if (next != null && next < endTime) {
            return false;
        }

        // Both search and insertion are O(log N)
        calendar.put(startTime, endTime);
        return true;
    }
}