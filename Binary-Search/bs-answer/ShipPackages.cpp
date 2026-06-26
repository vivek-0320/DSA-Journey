#include<bits/stdc++.h>
using namespace std;

class Solution {
public:

    static bool helper(vector<int>& weights, int days,int x){
        int packages=0;
        int load=0;
        for(int i=0;i<weights.size();i++){      
            if(load+weights[i]>x) {
                packages++;
                //cout << endl;
                //cout <<  weights[i] << " ";
                load=weights[i] ;
            } else {
                //cout << weights[i] << " ";
                load+=weights[i];
            }
        }
         if(load!=0)
                packages++;
        //cout << "p: "<< packages << endl;
        if(packages<=days)
            return true;
        else
            return false;
    }

    static int shipWithinDays(vector<int>& weights, int days){
        int low=*max_element(weights.begin(),weights.end())-1; // not answer
        int high=accumulate(weights.begin(),weights.end(),0); // answer
        while(low+1 < high) {
            int mid = (low+high)/2;
            //cout << "mid : " << mid << endl;
            if(helper(weights,days,mid))
                high=mid;
            else
                low=mid;
        }
        return high;
    }
};

int main()
{
    vector<int> v = {1,2,3,4,5,6,7,8,9,10};
    cout << Solution::shipWithinDays(v,5) << endl;
    return 0;
}