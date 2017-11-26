package graph.reviewHeap;

import java.util.NoSuchElementException;

public class MinHeap {
	private int[] array;
	private int size;
	
	public MinHeap(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Input array cannot be null or empty");
		}
		this.array = array;
		this.size = array.length;
		heapify(array);
	}
	
	public MinHeap(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException("The capacity cannot be equal to or less than 0");
		}
		this.array = new int[cap];
		this.size = 0;
	}

	private void heapify(int[] array) {
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			percolateDown(i);
		}
	}
	
	public void offer(int element) {
		if (this.isFull()) {
			int[] newArray = new int[(int)(array.length * 1.5)];
			copy(newArray, array);
			array = newArray;
		}
		array[size] = element;
		size++;
		percolateUp(size - 1);
	}
	

	private void copy(int[] newArray, int[] array) {
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
	}
	
	public int poll() {
		if (size == 0) {
			throw new NoSuchElementException("The heap is empty now");
		}
		int res = array[0];
		array[0] = array[size - 1];
		size--;
		percolateDown(0);
		return res;
	}

	private void percolateDown(int i) {
		while (i <= size / 2 - 1) {
			int left_child = 2 * i + 1;
			int right_child = 2 * i + 2;
			int swapCandidate = left_child;
			if (right_child <= size - 1 && array[right_child] < array[left_child]) {
				swapCandidate = right_child;
			}
			if (array[i] > array[swapCandidate]) {
				swap(array, i, swapCandidate);
			} else {
				break;
			}
			i = swapCandidate;
		}
	}

	private void percolateUp(int i) {
		while (i > 0) {
			int parentIdx = (i - 1) / 2;
			if (array[parentIdx] >= array[i]) {
				swap(array, i, parentIdx);
			} else {
				break;
			}
			i = parentIdx;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == array.length;
	}
	
	public int peek() {
		return size == 0 ? -1 : array[0];
	}
	
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	private int update(int index, int element) {
		if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException("The index must be within array range");
		}
		int res = array[index];
		array[index] = element;
		if (element > res) {
			percolateDown(index);
		} else {
			percolateUp(index);
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] array = new int[]{4,3,2,5,1,2,7};
		MinHeap minHeap = new MinHeap(array);
		System.out.println(minHeap.peek());
	}
}
