package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

public abstract class Algorithm {

	private final AlgorithmStatistics statistics = new AlgorithmStatistics();

	public abstract Pair<Integer, AlgorithmStatistics> countPositionalStatistic(int n, int k, int[] tab);

	public void printArray(int[] tab) {
		for (int i : tab) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public AlgorithmStatistics getStatistics() {
		return statistics;
	}

	public void resetStatistics() {
		statistics.reset();
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
}
