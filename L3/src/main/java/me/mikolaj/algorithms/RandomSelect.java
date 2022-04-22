package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

import java.util.Random;

public class RandomSelect extends Algorithm {

	private final Random random = new Random();

	@Override
	public Pair<Integer, AlgorithmStatistics> countPositionalStatistic(int n, int k, int[] tab) {
		return new Pair<>(randomSelect(tab, 0, n - 1, k), getStatistics());
	}

	private int randomSelect(int[] tab, int p, int q, int i) {
		if (p == q)
			return tab[p];

		int r = randPartition(tab, p, q);
		int k = r - p + 1;
		if (k == i)
			return tab[r];
		else if (compareLess(i, k))
			return randomSelect(tab, p, r - 1, i);
		else
			return randomSelect(tab, r + 1, q, i - k);
	}

	private int randPartition(int[] arr, int begin, int end) {
		int pivot = arr[random.nextInt(begin, end + 1)];
		int i = (begin - 1);

		for (int j = begin; j < end; j++) {
			if (compareLessEqual(arr[j], pivot)) {
				i++;

				int swapTemp = arr[i];
				arr[i] = arr[j];
				arr[j] = swapTemp;
				getStatistics().increaseKeySwap();
			}
		}

		int swapTemp = arr[i + 1];
		arr[i + 1] = arr[end];
		arr[end] = swapTemp;
		getStatistics().increaseKeySwap();

		return i + 1;
	}
}
