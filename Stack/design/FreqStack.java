import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

// The solution uses two maps and one maxFreq variable.
// One map counts the frequency of all elements and updates maxfreq variable.
// Second map is a map for freq(key) -> stack of that frequency(value)
// Whener an element is pushed , then its frequency is updates and maxFreq is updated, then
// the element is pushed on the stack of its current frequency.
// Whenever an element is popped, it is removed from its current freq stack and its frequency is decreased. 
// If the maxFreq stack gets empty, then maxFreq is also

class FreqStack {
    private int maxFreq;
    HashMap<Integer, Integer> freqMap;
    HashMap<Integer, Deque<Integer>> stackMap;

    public FreqStack() {
        maxFreq = 0;
        freqMap = new HashMap<>();
        stackMap = new HashMap<>();
    }

    public void push(int val) {
        int currentFreq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, currentFreq);
        maxFreq = Math.max(maxFreq, currentFreq);

        if (stackMap.get(currentFreq) == null) {
            stackMap.put(currentFreq, new ArrayDeque<>());
        }
        stackMap.get(currentFreq).push(val);
    }

    public void push2(int val) {
        int freq = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, freq);
        maxFreq = Math.max(maxFreq, freq);
        
        stackMap.computeIfAbsent(freq, k -> new ArrayDeque<>()).push(val);
    }

    public int pop() {
        int val = stackMap.get(maxFreq).pop();
        freqMap.put(val, freqMap.get(val) - 1);
        if (stackMap.get(maxFreq).isEmpty())
            maxFreq--;
        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */