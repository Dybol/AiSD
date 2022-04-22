package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

import java.util.Arrays;

public class Select extends Algorithm {

	@Override
	public Pair<Integer, AlgorithmStatistics> countPositionalStatistic(int n, int k, int[] tab) {
		if (n < k)
			return new Pair<>(-1, getStatistics());

		setTabAtTheBeginning(tab.clone());

		int positionalStatistic = select(tab, 0, n - 1, k);

		return new Pair<>(positionalStatistic, getStatistics());
	}

	private int select(int[] tab, int left, int right, int k) {
		if (compareGreater(k, 0) && compareLessEqual(k, right - left + 1)) {
			int n = right - left + 1;

			int i;

			int[] median = new int[(n + 4) / 5];
			for (i = 0; i < n / 5; i++)
				median[i] = findMedian(tab, left + i * 5, left + i * 5 + 5);

			if (i * 5 < n) {
				median[i] = findMedian(tab, left + i * 5, left + i * 5 + n % 5);
				i++;
			}

			int medOfMed = (i == 1) ? median[0] : select(median, 0, i - 1, i / 2);

			int pos = partition(tab, left, right, medOfMed);

			if (compareEqual(pos - left, k - 1))
				return tab[pos];
			if (compareGreater(pos - left, k - 1))
				return select(tab, left, pos - 1, k);

			return select(tab, pos + 1, right, k - pos + left - 1);
		}
		return -1;
	}

	private int findMedian(int[] arr, int left, int right) {
		Arrays.sort(arr, left, right);
		return arr[left + (right - left) / 2];
	}

	private int partition(int[] arr, int l,
						  int r, int x) {
		int i;
		for (i = l; i < r; i++)
			if (compareEqual(arr[i], x))
				break;
		swap(arr, i, r);

		i = l;
		for (int j = l; j <= r - 1; j++) {
			if (compareLessEqual(arr[j], x)) {
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, r);
		return i;
	}

}
