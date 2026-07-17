import java.util.StringTokenizer;

public class ElapsedTime {

    public int secondsBetweenTimes(String startTime, String endTime) {
        int startH = Integer.valueOf(startTime.substring(0, 2));
        int startM = Integer.valueOf(startTime.substring(3, 5));
        int startS = Integer.valueOf(startTime.substring(6));
        int endH = Integer.valueOf(endTime.substring(0, 2));
        int endM = Integer.valueOf(endTime.substring(3, 5));
        int endS = Integer.valueOf(endTime.substring(6));
        int secondLeft = 0;
        if (endS < startS) {
            endM -= 1;
            endS += 60;
        }
        if (endM < startM) {
            endH -= 1;
            startM += 60;
        }
        secondLeft += (endS - startS) + (endM - startM) * 60 + (endH - startH) * 3600;
        return secondLeft;

    }
}