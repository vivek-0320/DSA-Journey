#include <bits/stdc++.h>
using namespace std;

class DSU
{
public:
    vector<int> parent;
    vector<int> size;
    int maxSize = 200002;

    DSU()
    {
        parent.resize(maxSize);
        size.resize(maxSize);
        for (int i = 0; i < maxSize; i++)
        {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find(int a)
    {
        if (a == parent[a])
            return a;

        return parent[a] = find(parent[a]);
    }

    void unionPoints(int r, int c)
    {
        int pR = find(r);
        int pC = find(c);

        // SAFETY CHECK: If they already share a root, do nothing.
        // Without this, the sizes will falsely double if a stone
        // connects a row and column that are already in the same component.
        if (pR == pC)
            return;

        if (size[pR] < size[pC])
        {
            parent[pR] = pC;
            size[pC] += size[pR];
        }
        else
        {
            parent[pC] = pR;
            size[pR] += size[pC];
        }
    }
};

int removeStones(vector<vector<int>> &stones)
{
    DSU dsu;
    for (vector<int> &stone : stones)
    {
        int r = stone[0];
        int c = stone[1] + 100001;
        dsu.unionPoints(r, c);
    }

    int components = 0;

    // Count the number of active, unique roots
    for (int i = 0; i < dsu.maxSize; i++)
    {
        // A node is an active root if it points to itself AND has a size > 1.
        // (Unused indices from 0 to 200001 will have a size of exactly 1).
        if (dsu.parent[i] == i && dsu.size[i] > 1)
        {
            components++;
        }
    }

    // Master formula: Max removed = total stones - connected components
    return stones.size() - components;
}

int main()
{

    vector<vector<int>> v = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
    cout << removeStones(v) << endl;
}