import java.util.Arrays;

public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = Arrays.stream(gas).sum();
        int sumCost = Arrays.stream(cost).sum();
        if (sumGas < sumCost)
            return -1;
        int totalGain = 0;
        int tank = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int gain = gas[i] - cost[i];
            totalGain += gain;
            tank += gain;

            if (tank < 0) {
                // can't reach station i+1 from current start
                start = i + 1;
                tank = 0;
            }
        }
        return totalGain >= 0 ? start : -1;
    }
}
