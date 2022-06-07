package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;

import java.util.ArrayList;

public class Heap {

	private final AlgorithmStatistics statistics = new AlgorithmStatistics();

	public void heapify(int[] array, int size, int i) {
		//TODO: printowanie stanu posredniego
		System.out.println("Stan posredni: ");
		printArray(array);
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		if (statistics.compareLess(l, size) && statistics.compareGreater(array[l], array[largest]))
			largest = l;
		if (statistics.compareLess(r, size) && statistics.compareGreater(array[r], array[largest]))
			largest = r;

		if (!statistics.compareEqual(largest, i)) {
			statistics.swap(array, largest, i);

			heapify(array, size, largest);
		}
	}

	// Function to heapify the tree
	public void heapify(ArrayList<Integer> array, int i) {
		int size = array.size();
		// Find the largest among root, left child and right child
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		if (statistics.compareLess(l, size) && statistics.compareGreater(array.get(l), array.get(largest)))
			largest = l;
		if (statistics.compareLess(r, size) && statistics.compareGreater(array.get(r), array.get(largest)))
			largest = r;

		if (!statistics.compareEqual(largest, i)) {

			statistics.swap(array, i, largest);

//			int temp = array.get(largest);
//			array.set(largest, array.get(i));
//			array.set(i, temp);

			heapify(array, largest);
		}
	}

	public void printArray(ArrayList<Integer> array) {
		for (Integer i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public void printArray(int[] array) {
		for (Integer i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public void display(int[] array) {
		// n is number of elements in the heap
		// It can be further optimised by calculating height of the heap
		// and looping i only till height of the tree
		for (int i = 0; i <= array.length / 2; i++) {
			for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) <= array.length; j++) { // Each row has 2^n nodes
				System.out.print(array[j + (int) Math.pow(2, i) - 1] + " ");
			}
			System.out.println();
		}
	}

	public void display2(int[] array) {
		StringBuilder sb = new StringBuilder();
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) < array.length; j++) {
				if (j > max) {
					max = j;
				}
			}
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) < array.length; j++) {
				sb.append(" ".repeat(Math.max(0, max / ((int) Math.pow(2, i)))));
				sb.append(array[j + (int) Math.pow(2, i) - 1]).append(" ");

			}
			sb.append("\n");
		}

		System.out.println(sb);

	}


	public AlgorithmStatistics getStatistics() {
		return statistics;
	}
}
