#include<bits/stdc++.h>
using namespace std;

class Solution {
public:

    static bool helper(vector<int>& bloomDay,long x,int k,int m)
    {
        int noB = 0;
        int adjacentFlower = 0;
        for(int i=0;i<bloomDay.size();i++)
        {
            if(bloomDay[i]<=x)
                adjacentFlower++;
            else
            {
                noB += (adjacentFlower/k);
                adjacentFlower=0;
            }
        }
        noB+=(adjacentFlower/k);
        // int i = 0;
        // while(i<bloomDay.size())
        // {           
            
        //     int curr = 0;
        //     bool flag=true;
        //     while(curr < k && flag)
        //     {
        //         if(i+curr < bloomDay.size())
        //         cout << "new bunch " << bloomDay[i+curr] << " ";
        //         if(i+curr==bloomDay.size() || bloomDay[i+curr]>x)
        //         {
        //             flag=false;
        //             break;
        //         }        
        //         curr++;            
        //     }
        //     cout << endl;
        //     if(curr==k && flag)
        //     {
        //         c++;
        //         i+=curr;
        //     }
        //     else
        //     {
        //         i+=(curr+1);
        //     }
                           
        // }
        // cout << "c " << c << endl;
        if(noB>=m)
            return true;
        else
            return false;
    }

    static int minDays(vector<int>& bloomDay, int m, int k) {
        if(m*k > bloomDay.size()) return -1;
        long low = *min_element(bloomDay.begin(),bloomDay.end())-1;
        long high = *max_element(bloomDay.begin(),bloomDay.end());
        while(low+1 < high)
        {
            long mid = (low+high)/2;
            cout << "mid " << mid << endl;
            if(helper(bloomDay,mid,k,m))
                high=mid;
            else
                low=mid;
        }
        return high;
    }
};

int main()
{
    vector<int> v ={7,7,7,7,12,7,7};
    int m=2;
    int k=3;
    cout << Solution::minDays(v,m,k) << endl;
    return 0;
}