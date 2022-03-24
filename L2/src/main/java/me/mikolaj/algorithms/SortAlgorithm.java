package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

public abstract class SortAlgorithm {

	private final AlgorithmStatistics statistics = new AlgorithmStatistics();

	public abstract Pair<int[], AlgorithmStatistics> sortArray(int n, int[] tab);

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
}
