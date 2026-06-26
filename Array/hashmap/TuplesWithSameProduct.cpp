#include<bits/stdc++.h>
using namespace std;

int tupleSameProduct(vector<int>& nums) {
    unordered_map<int,int> ump;
    for(int i=0; i<nums.size(); i++) {
        for(int j=i+1; j<nums.size(); j++) {
            ump[(nums[i]*nums[j])]++;
        }
    }
    int count=0;
    for(auto &pairs:ump) {
        if(pairs.second > 1) {
            int p = ( pairs.second * (pairs.second -1) ) / 2;
            count += p*8;
        }            
    }
    return count;
}

int main()
{
    vector<int> v = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192};
    int ans = tupleSameProduct(v);
    cout << ans << endl;
    return 0;
}