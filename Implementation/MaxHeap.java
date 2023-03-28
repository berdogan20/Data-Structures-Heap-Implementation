package Implementation;

public class MaxHeap {

	private int[] heap;
	private int size;
	
	public MaxHeap(int capacity)
	{
		heap = new int[capacity];
	}
	
	public boolean isFull()
	{
		return size == heap.length;
	}
	
	public int getParent(int index)
	{
		/*
		 * left child  : 2i + 1
		 * right child : 2i + 2
		 * parent      : (i - 1) / 2
		 * */
		
		return (index - 1) / 2;
	}
	
	public void insert(int value)
	{
		if(isFull())
		{
			int[] newHeap = new int[2 * heap.length];
			System.arraycopy(heap, 0, newHeap, 0, heap.length);
			heap = newHeap;
		}
		
		heap[size] = value;
		
		// heapify
		fixHeapAbove(size);
		size++;
	}

	private void fixHeapAbove(int index) {
		
		int newValue = heap[index];
		
		while (index > 0 && newValue > heap[index])
		{
			heap[index] = heap[getParent(index)];
			index = getParent(index);
		}
		
		heap[index] = newValue;
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public int getLeftChild(int index)
	{
		return 2 * index + 1;
	}
	
	public int getRightChild(int index)
	{
		return 2 * index + 2;
	}
	
	
	public int delete(int index)
	{
		if (isEmpty())
		{
			throw new IndexOutOfBoundsException("Heap is empty.");
		}
		
		int parent = getParent(index);
		int deleteValue = heap[index];
		
		// we will set the last element in the array in place of deleted element
		heap[index] = heap[size - 1];
		
		// and heapify the heap
		if (index  == 0 || heap[index] < heap[parent])
		{
			fixHeapBelow(index, size - 1);
		}
		else
		{
			fixHeapAbove(index);
		}
		
		size--;
		return deleteValue;
	}
	
	private void fixHeapBelow(int index, int lastHeapIndex)
	{
		int childToSwap;
		
		while (index <= lastHeapIndex)
		{
			int leftChild = getLeftChild(index);
			int rightChild = getRightChild(index);
			
			if (leftChild <= lastHeapIndex)
			{
				// this node has a left child
				
				if (rightChild > lastHeapIndex)
				{
					// this node does not have a right child	
					childToSwap = leftChild;
				}
				else
				{
					// this node has a right child	
					// choose the bigger child
					childToSwap = (heap[leftChild] > heap[rightChild] ? leftChild : rightChild);
				}
				
				if (heap[index] < heap[childToSwap])
				{
					int temp = heap[index];
					heap[index] = heap[childToSwap];
					heap[childToSwap] = temp;
				}
				else
				{
					break;
				}
				
				// set the next index
				index = childToSwap;
			}
			else
			{
				// this node does not have any child
				break;
			}
		}
	}
	
	
	public void printHeap()
	{
		for (int i = 0; i < size; i++)
		{
			System.out.print(heap[i] + " ");
		}
		
		System.out.println();
	}
	
	public int peek()
	{
		if (isEmpty())
		{
			throw new IndexOutOfBoundsException("Heap is empty.");
		}
		return heap[0];
	}
	
}






