#include<bits/stdc++.h>
using namespace std;

int longestMonotonicSubarray(vector<int>& ar) {
        int maxL=0;
        int l=1;
        for(int i=0;i<=ar.size()-2;i++)
        {
            //printf("i: %d, i+1: %d , l=%d \n",ar[i],ar[i+1],l);
            if(ar[i]<ar[i+1])
                l++;
            else
                l=1;
            maxL=max(maxL,l);
        }
        l=1;
        for(int i=0;i<=ar.size()-2;i++)
        {
            //printf("i: %d, i+1: %d , l=%d \n",ar[i],ar[i+1],l);
            if(ar[i]>ar[i+1])
                l++;
            else
                l=1;
            maxL=max(maxL,l);
        }
        return maxL;
    }

int main()
{
    vector<int> v = {1,4,3,3,2};
    int ans = longestMonotonicSubarray(v);
    cout << ans << endl;
    return 0;
}