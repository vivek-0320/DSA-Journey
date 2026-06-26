#include<bits/stdc++.h>
using namespace std;

int dfs(int i,bool canBuy, vector<int> prices)
{
    if(i>=prices.size())    return 0;

    if(canBuy)
    {
        int buy = -prices[i] + dfs(i+1,false,prices);
        int  skip = dfs(i+1,true,prices);
        return max(buy,skip);
    }
    else
    {
        int sell = prices[i]+dfs(i+2,true,prices);
        int skip = dfs(i+1,false,prices);
        return max(sell,skip);
    }
}

int maxProfit(vector<int>& prices) {
    int hold = -prices[0];
    int sold=0;
    int rest = 0;
    for(int i=1;i<prices.size();i++)
    {
        int prevHold = hold;
        int prevSold = sold;
        int prevRest = rest;

        hold = max(prevHold, prevRest - prices[i]);
        sold = prices[i] + prevHold;
        rest = max(prevRest, prevSold);
    }
    return max(sold,rest);
}

int main()
{
    return 0;
}