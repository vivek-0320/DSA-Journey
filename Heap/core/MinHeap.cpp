#include<bits/stdc++.h>
using namespace std;

class MinHeap
{
private:
	int* arr;
	int sizeOfTree;

	void heapifyBottomToTop()
	{
		int index = sizeOfTree;
		while(arr[index] < arr[(index/2)] && index > 0)
		{
			int temp=arr[index];
			arr[index]=arr[(index/2)];
			arr[(index/2)]=temp;
			index/=2;
		}
	}
	/* Recursive Approach
	void heapifyBottomToTop(int index, String heapType) {
    int parent = index / 2;
    if (index <= 1 ) {
      return;
    }
    if (heapType == "Min") {
      if (arr[index] < arr[parent]) {
        int tmp = arr[index];
        arr[index] = arr[parent];
        arr[parent] = tmp;
      }
    } else if (heapType == "Max") {
      if (arr[index] > arr[parent]) {
        int tmp = arr[index];
        arr[index] = arr[parent];
        arr[parent] = tmp;
      }
    }    heapifyBottomToTop(parent, heapType);

  }*/

	void heapifyTopToBottom(int index) {
    int left = index*2;
    int right = index*2 + 1;
    int swapChild = 0;
    if (sizeOfTree < left) {
      return;
    }
      if (sizeOfTree == left) {
        if (arr[index] > arr[left]) {
          int tmp = arr[index];
          arr[index] = arr[left];
          arr[left] = tmp;
        }
        return;
      } else {
        if (arr[left] < arr[right]) {
          swapChild = left;
        } else {
          swapChild = right;
        }
        if (arr[index] > arr[swapChild]) {
          int tmp = arr[index];
          arr[index] = arr[swapChild];
          arr[swapChild] = tmp;
        }
      }
    heapifyTopToBottom(swapChild);
  }

public:
	MinHeap(int size)
	{
		arr = new int[(size+1)];
		sizeOfTree = 0;
	}

	int peek()
	{
		return arr[1];
	}

	int size()
	{
		if(sizeOfTree==0)
		{
			cout  << "Heap is Empty";
			return -1;
		}
		else
		{
			return sizeOfTree;
		}
	}

	void levelTraversal()
	{
	   for(int i=1;i<=sizeOfTree;i++)
	   {
		cout << this->arr[i] << " "; 
	   }
	   cout << endl;
	}

	void insert(int num)
	{
		arr[(sizeOfTree+1)] = num;
		sizeOfTree++;
		heapifyBottomToTop();
	}	

	int extract()
	{
		int x = arr[1];
		arr[1] = arr[sizeOfTree];
		sizeOfTree--;
		heapifyTopToBottom(1);
		return x;
	}

	~MinHeap() {
        delete[] arr; // Free memory
    }
};

int main()
{
	MinHeap mh = MinHeap(10);
	mh.insert(5);
	mh.levelTraversal();
	mh.insert(10);
	mh.levelTraversal();
	mh.insert(20);
	mh.levelTraversal();
	mh.insert(30);
	mh.levelTraversal();
	mh.insert(2);
	mh.levelTraversal();
	mh.insert(25);
	mh.levelTraversal();
	mh.insert(15);
	mh.levelTraversal();
	
}