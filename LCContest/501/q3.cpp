#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    long long minArraySum(vector<int>& nums) {
        int max_val = 0;
        for (int num : nums) {
            if (num > max_val) max_val = num;
        }
        vector<bool> isPresent(max_val + 1, false);
        for (int num : nums) {
            isPresent[num] = true;
        }
        vector<int> minDivisor(max_val + 1, 0);

        for (int i = 1; i <= max_val; i++) {
            if (isPresent[i]) {
                for (int j = i; j <= max_val; j += i) {
                    
                    if (isPresent[j] && minDivisor[j] == 0) {
                        minDivisor[j] = i; 
                    }
                }
            }
        }
        long long sum = 0;
        for (int num : nums) {
            sum += minDivisor[num];
        }
        return sum;
    }
};