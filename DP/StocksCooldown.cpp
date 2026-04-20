#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    int help(int i, bool canBuy, vector<int> prices)
    {
        if (i >= prices.size())
            return 0;

        if (canBuy)
        {
            int buy = -prices[i] + help(i + 1, false, prices);
            int skip = help(i + 1, true, prices);
            return max(buy, skip);
        }
        else
        {
            int sell = prices[i] + help(i + 2, true, prices);
            int skip = help(i + 1, false, prices);
            return max(sell, skip);
        }
    }

    int maxProfit(vector<int> &prices)
    {
        int hold = -prices[0];
        int sold = 0;
        int rest = 0;
        for (int i = 1; i < prices.size(); i++)
        {
            int prevHold = hold;
            int prevSold = sold;
            int prevRest = rest;

            hold = max(prevHold, prevRest - prices[i]);
            sold = prices[i] + prevHold;
            rest = max(prevRest, prevSold);
        }
        return max(sold, rest);
    }
};

int maxProfit(vector<int> &prices)
{
    
}

int main()
{
    return 0;
}