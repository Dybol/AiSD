package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

import java.util.Arrays;

public abstract class Algorithm {

	private final AlgorithmStatistics statistics = new AlgorithmStatistics();

	private int[] tabAtTheBeginning;

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

	public int[] getTabAtTheBeginning() {
		return tabAtTheBeginning;
	}

	public void setTabAtTheBeginning(int[] tabAtTheBeginning) {
		this.tabAtTheBeginning = tabAtTheBeginning;
	}

	public void printResults(int[] tabAtTheEnd, int result) {
		System.out.println("Wyniki dla algorytmu: " + this.getClass().getSimpleName());
		printArray(tabAtTheEnd);
		printArray(tabAtTheBeginning);
		System.out.println("Statystyka pozycyjna: " + result);
		Arrays.sort(tabAtTheEnd);
		printArray(tabAtTheEnd);
	}
}
