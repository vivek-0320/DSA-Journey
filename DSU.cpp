#include <iostream>
#include <vector>

using namespace std;

class DSU {
    vector<int> parent;
    vector<int> size; // Union by size

public:
    DSU(int n) {
        parent.resize(n);
        size.resize(n, 1); // Everyone starts as a gang of size 1
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Everyone is their own boss initially
        }
    }

    // Find the ultimate boss with Path Compression
    int findUltimateBoss(int node) {
        if (node == parent[node]) {
            return node;
        }
        // Recursively find the boss AND compress the path on the way back
        return parent[node] = findUltimateBoss(parent[node]); 
    }

    // Merge two gangs
    void unionBySize(int u, int v) {
        int ultimateBossU = findUltimateBoss(u);
        int ultimateBossV = findUltimateBoss(v);

        // Already in the same gang
        if (ultimateBossU == ultimateBossV) return;

        // Smaller gang joins the bigger gang
        if (size[ultimateBossU] < size[ultimateBossV]) {
            parent[ultimateBossU] = ultimateBossV;
            size[ultimateBossV] += size[ultimateBossU];
        } else {
            parent[ultimateBossV] = ultimateBossU;
            size[ultimateBossU] += size[ultimateBossV];
        }
    }
};

int main() {
    DSU dsu(7);
    
    // Merge some nodes
    dsu.unionBySize(1, 2);
    dsu.unionBySize(2, 3);
    dsu.unionBySize(4, 5);
    dsu.unionBySize(6, 7);
    dsu.unionBySize(5, 6);
    
    // Query: Are 3 and 7 in the same component?
    if (dsu.findUltimateBoss(3) == dsu.findUltimateBoss(7)) {
        cout << "Yes, 3 and 7 are connected.\n";
    } else {
        cout << "No, 3 and 7 are NOT connected.\n"; // This will print
    }

    // Merge the two big components
    dsu.unionBySize(3, 7);
    
    // Query again
    if (dsu.findUltimateBoss(3) == dsu.findUltimateBoss(7)) {
        cout << "Now, 3 and 7 ARE connected.\n"; // This will print
    }

    return 0;
}