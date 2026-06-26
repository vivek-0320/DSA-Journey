#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    static void setZeroes(vector<vector<int>>& matrix) 
    {
        int r = matrix.size();
        int c = matrix[0].size();

        vector<int> row(r,1);
        vector<int> col(c,1);
        
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                if(matrix[i][j]==0)
                {
                    row[i]=0;
                    col[j]=0;
                }
            }
        }

        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                if(row[i]==0 || col[j]==0)
                    matrix[i][j]=0;
            }
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

    static void setZeroesOptimal(vector<vector<int>>& matrix) 
    {
        int r = matrix.size();
        int c = matrix[0].size();

        // vector<int> row(r,1); -> matrix[..][0]
        // vector<int> col(c,1); -> matrix[0][..]

        int c0 = 1;

        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                if( matrix[i][j]==0 )
                {
                    matrix[i][0]=0;

                    if(j!=0) 
                        matrix[0][j]=0;
                    else                      
                        c0=0;
                }
            }
        }

        for(int i=r-1; i>0 ; i--)
        {
            for(int j=c-1 ; j>0 ; j--)
            {   
                if(matrix[i][0]==0 || matrix[0][j]==0) 
                    matrix[i][j]=0;            
            }
        }


        if(matrix[0][0]==0)
        {
            for(int j=1;j<c;j++)    matrix[0][j]=0;
        }

        if(c0==0)
        {
            for(int i=0;i<r;i++)    matrix[i][0]=0;
        }

        cout << c0 << endl;
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
    vector<vector<int>> v = {{1,1,1,1},{1,1,0,1},{1,0,1,1}};
    Solution::setZeroesOptimal(v);
}