import java.util.*;

public class Dota2Senate {

    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new ArrayDeque<>();
        Queue<Integer> dire = new ArrayDeque<>();
        int n = senate.length();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R')
                radiant.add(i);
            else
                dire.add(i);
        }
        while(!radiant.isEmpty() && !dire.isEmpty()) {
            if(radiant.peek() < dire.peek()) {
                dire.poll();
                radiant.add(radiant.poll()+n);
            } else {
                radiant.poll();
                dire.add(dire.poll()+n);
            }
        }
        if(radiant.isEmpty())
            return "Dire";
        else
            return "Radiant";
    }
}