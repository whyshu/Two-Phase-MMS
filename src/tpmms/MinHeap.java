package tpmms;

import java.util.Arrays;
import java.util.NoSuchElementException;

// Class Minimum BinaryHeap data structure
public class MinHeap
{
    // The number of children each node has
    public static final int d = 2;
    public static int heapSize;
    public static String[] heap;

    // Constructor
    public MinHeap(int capacity)
    {
        heapSize = 0;
        heap = new String[capacity + 1];
        Arrays.fill(heap, "");
    }

    //Function to check if the heap is empty
    public static boolean isEmpty( )
    {
        return heapSize == 0;
    }

    //Check if the heap is full
    public static boolean isFull( )
    {
        return heapSize == heap.length;
    }

    //Clear heap
    public void makeEmpty( )
    {
        heapSize = 0;
    }

    //Function to  get index parent of i
    private static int parent(int i)
    {
        return (i - 1)/d;
    }

    // Function to get index of k th child of i
    private static int kthChild(int i, int k)
    {
        return d * i + k;
    }

    // Function to insert an element
    public static void insert(String x)
    {
        if (isFull( ) )
            throw new NoSuchElementException("Overflow Exception");
        //Insert the element into the array
        heap[heapSize++] = x;
        heapifyUp(heapSize - 1);
    }

    //Function to find the least element
    public static String findMin( )
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        return heap[0];
    }

    //Function to delete the min element
    public static String deleteMin()
    {
        String keyItem = heap[0];
        delete(0);
        return keyItem;
    }

    // Function to delete element at an index
    public static String delete(int ind)
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        String keyItem = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(ind);
        return keyItem;
    }

    // Function heapifyUp
    private static void heapifyUp(int childInd)
    {
        String tmp = heap[childInd];
        while (childInd > 0 && tmp.compareTo(heap[parent(childInd)])<0)
        {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }
        heap[childInd] = tmp;
    }

    //Function heapifyDown
    private static void heapifyDown(int ind)
    {
        int child;
        String tmp = heap[ ind ];
        while (kthChild(ind, 1) < heapSize)
        {
            child = minChild(ind);
            if (heap[child].compareTo(tmp)<0)
                heap[ind] = heap[child];
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
    }

    //Function to get the smallest child
    public static int minChild(int ind)
    {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize))
        {
            if (heap[pos].compareTo(heap[bestChild])<0)
                bestChild = pos;
            pos = kthChild(ind, k++);
        }
        return bestChild;
    }

    //Function to print heap
    public static void printHeap()
    {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print("\n"+heap[i]);
        System.out.println();
    }
}