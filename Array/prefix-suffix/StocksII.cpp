#include<bits/stdc++.h>
using namespace std;

int maxProfit(vector<int>& prices) {
    int l=0;
    int r=1;
    int profit=0;
    while(r<prices.size())
    {
        if(prices[l]<prices[r])
        {
            profit+=prices[r]-prices[l];;
            l=r;
        }
        else
        {
            l=r;
        }
        r++;
    }
    return profit;
}

int main()
{
    return 0;
}