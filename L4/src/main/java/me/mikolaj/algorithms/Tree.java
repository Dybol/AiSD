package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;

public abstract class Tree {
	private final AlgorithmStatistics statistics = new AlgorithmStatistics();

	public abstract void insert(int k);

	public abstract void delete(int k);

	public AlgorithmStatistics getStatistics() {
		return statistics;
	}

	public void resetStatistics() {
		statistics.reset();
	}

	public int[] swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

		statistics.increaseKeySwap();

		return arr;
	}

	public boolean compareLessEqual(int i, int j) {
		statistics.increaseKeyComparison();
		return i <= j;
	}

	public boolean compareGreaterEqual(int i, int j) {
		statistics.increaseKeyComparison();
		return i >= j;
	}

	public boolean compareLess(int i, int j) {
		statistics.increaseKeyComparison();
		return i < j;
	}

	public boolean compareGreater(int i, int j) {
		statistics.increaseKeyComparison();
		return i > j;
	}

	public boolean compareEqual(int i, int j) {
		statistics.increaseKeyComparison();
		return i == j;
	}
}
