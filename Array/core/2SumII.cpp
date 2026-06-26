#include<bits/stdc++.h>
using namespace std;

vector<int> twoSum(vector<int>& numbers, int target) {
        int x=0;
        int y=numbers.size()-1;
        while(x<y)
        {
            int sum = numbers[x]+numbers[y] ;
            if( sum == target) {
                vector<int> ans(2);
                ans[0]=x+1;
                ans[1]=y+1;
                return ans;
            } else if(sum < target) {
                x++;
            } else {
                y--;
            }
        }
}

int main()
{
    return 0;
}