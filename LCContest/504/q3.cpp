#include <vector>
#include <algorithm>
#include <map>

using namespace std;

class Solution {
public:
    long long maxItems(vector<vector<int>>& items, int budget) {
        int n = items.size();
        long long minPrice = 2e9; 
        int maxFactor = 0;

        for (int i = 0; i < n; i++) {
            minPrice = min(minPrice, (long long)items[i][1]);
            maxFactor = max(maxFactor, items[i][0]);
        }

        vector<int> factor_count(maxFactor + 1, 0);
        for (int i = 0; i < n; i++) {
            factor_count[items[i][0]]++;
        }

        vector<int> multiple_count(maxFactor + 1, 0);
        for (int f = 1; f <= maxFactor; f++) {
            if (factor_count[f] == 0) continue;
            int count = 0;
            for (int m = f; m <= maxFactor; m += f) {
                count += factor_count[m];
            }
            multiple_count[f] = count;
        }

        map<long long, long long> deals;
        for (int i = 0; i < n; i++) {
            int c_i = multiple_count[items[i][0]] - 1; 
            if (c_i > 0) {
                deals[items[i][1]] += c_i;
            }
        }

        long long budget_left = budget;
        long long value2_items = 0;
        
        long long max_total = budget / minPrice;

        for (auto const& [p, c] : deals) {
            if (budget_left <= 0) break;

            long long take = min((long long)c, budget_left / p);
            
            budget_left -= take * p;
            value2_items += 2 * take; 
            
            long long current_total = value2_items + (budget_left / minPrice);
            max_total = max(max_total, current_total);

            if (take < c) {
                break;
            }
        }

        return max_total;
    }
};