#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    static void rotate(vector<vector<int>>& matrix) 
    {
        int r = matrix.size();
        int c = matrix[0].size();

        for(int i=0;i<r;i++)
        {
            for(int j=i;j<c;j++)
            {
                int t=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=t;
            }
        }
        int x=0;
        int y=c-1;
        while(x<y)
        {
            for(int i=0;i<r;i++)
            {
                int t=matrix[i][x];
                matrix[i][x]=matrix[i][y];
                matrix[i][y]=t;
            }
            x++;
            y--;
        }
        

        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                cout << matrix[i][j] <<" ";
            }
             cout << endl;
        }
    }
};

int main() 
{
    vector<vector<int>> matrix = {{1,2,3},{4,5,6},{7,8,9}};
    Solution::rotate(matrix);

}