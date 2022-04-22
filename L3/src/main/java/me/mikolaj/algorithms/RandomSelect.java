package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

import java.util.Random;

public class RandomSelect extends Algorithm {

	private final Random random = new Random();

	@Override
	public Pair<Integer, AlgorithmStatistics> countPositionalStatistic(int n, int k, int[] tab) {
		if (n < k)
			return new Pair<>(-1, getStatistics());

		setTabAtTheBeginning(tab.clone());

		System.out.println("Rozpoczynamy algorymt RandomSelect dla tablicy dlugosci " + n);

		int positionalStatistic = randomSelect(tab, 0, n - 1, k);
		printResults(tab, positionalStatistic);

		return new Pair<>(positionalStatistic, getStatistics());
	}

	private int randomSelect(int[] tab, int p, int q, int i) {
		if (p == q)
			return tab[p];

		int r = randPartition(tab, p, q);
		int k = r - p + 1;

		if (tab.length < 50) {
			System.out.println("Stan posredni:");
			printArray(tab);
			System.out.println(getStatistics());
		}

		if (compareEqual(k, i))
			return tab[r];
		else if (compareLess(i, k))
			return randomSelect(tab, p, r - 1, i);
		else
			return randomSelect(tab, r + 1, q, i - k);
	}

	private int randPartition(int[] arr, int begin, int end) {
		int pivotIndex = random.nextInt(begin, end + 1);
		int pivot = arr[pivotIndex];
		swap(arr, pivotIndex, end);
		int i = (begin - 1);

		for (int j = begin; j < end; j++) {
			if (compareLessEqual(arr[j], pivot)) {
				i++;
				swap(arr, i, j);
			}
		}

		swap(arr, i + 1, end);
		return i + 1;
	}
}
