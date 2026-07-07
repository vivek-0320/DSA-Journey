import java.util.Arrays;

// This problem is disguised as Knapsack where we take or skip the job.
// We sort the jobs in asc order by their end time so that we process jobs ending early first.
// We use dp[n] where n -> no. of jobs.
// When we skip a job then skip = dp[i-1]
// But when we include a job, we strictly want its start time to be >= end time of any previous job.
// To find the compatible job , we use binary search on end Time, and find the job whose end is before current job starting.
// We save that job and look for a higher start time of new job, if jobs[mid].end <= currJob.start 
// else we look in lower half.

public class MaxProfitInJobScheduling {
    private class Job {
        int start;
        int end;
        int profit;

        Job(int s, int e, int p) {
            start = s;
            end = e;
            profit = p;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> Integer.compare(a.end, b.end));
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;
        for (int i = 1; i < n; i++) {
            Job currJob = jobs[i];
            int k = -1;
            int low = 0;
            int high = i - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (jobs[mid].end <= currJob.start) {
                    k = mid;
                    mid = low + 1;
                } else {
                    high = mid - 1;
                }
            }
            int skip = dp[i - 1];
            int take = currJob.profit;
            if (k != -1)
                take += dp[k];
            dp[i] = Math.max(skip, take);
        }
        return dp[n - 1];
    }
}
