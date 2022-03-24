package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

public class QuickSort extends SortAlgorithm {

	@Override
	public Pair<int[], AlgorithmStatistics> sortArray(int n, int[] tab) {
		if(n == 1)
			return new Pair<>(tab, getStatistics());

		return new Pair<>(quickSort(tab, 0, n-1), getStatistics());
	}

	private int[] quickSort(int[] tab, int low, int high) {
		if (compare(low, high-1)) {
			int partitionIndex = partition(tab, low, high);

			quickSort(tab, low, partitionIndex-1);
			quickSort(tab, partitionIndex+1, high);
		}
		return tab;
	}

	private boolean compare(int i, int j) {
		getStatistics().increaseKeyComparison();
		return i <= j;
	}

	private int partition(int[] arr, int begin, int end) {
		int pivot = arr[end];
		int i = (begin-1);

		for (int j = begin; j < end; j++) {
			if (compare(arr[j], pivot)) {
				i++;

				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
				getStatistics().increaseKeySwap();
			}
		}

		int swapTemp = arr[i+1];
		arr[i+1] = arr[end];
		arr[end] = swapTemp;
		getStatistics().increaseKeySwap();

		return i+1;
	}
}
