#include<bits/stdc++.h>
using namespace std;

class Solution {
public:

    static bool help(vector<int>& piles,long long x, int h)
    {
        long long sum=0;
        for(auto &num:piles)
        {
            sum = sum + ceil((double)num/x);
        }
        if(sum<=h)
            return true;
        else
            return false;

    }

    static int minEatingSpeed(vector<int>& piles, int h) 
    {
        long long l=0; // a[l] not answer
        long long r=*max_element(piles.begin(),piles.end()); // a[h] is answer
        while(l+1<r)
        {
            long long mid = (l+r)/2;
            if(help(piles,mid,h))
                r=mid;
            else
                l=mid;
        }
        return r;
    }
};

int main()
{
    vector<int> v = {30,11,23,4,20};
    cout << Solution::minEatingSpeed(v,5) << endl;
    return 0;
}