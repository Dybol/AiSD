package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

public class DualPivotQuickSort extends SortAlgorithm {
	@Override
	public Pair<int[], AlgorithmStatistics> sortArray(int n, int[] tab) {
		if (n == 1)
			return new Pair<>(tab, getStatistics());

		return new Pair<>(divideArray(tab, 0, n - 1), getStatistics());

	}

	public int[] divideArray(int[] tab, int begin, int end) {
		if (compareGreaterEqual(begin, end))
			return tab;

		Pair<Integer, Integer> pivots = findPivots(tab, begin, end);

		divideArray(tab, begin, pivots.getFirst() - 1);
		divideArray(tab, pivots.getFirst() + 1, pivots.getSecond() - 1);
		divideArray(tab, pivots.getSecond() + 1, end);

		return tab;
	}

	public Pair<Integer, Integer> findPivots(int[] tab, int begin, int end) {
		if (compareGreater(tab[begin], tab[end])) {
			swap(tab, begin, end);
		}

		int firstPivotIndex = begin + 1;
		int secondPivotIndex = end - 1;
		int leftPivot = tab[begin], rightPivot = tab[end];

		for (int i = begin + 1; i <= secondPivotIndex; i++) {

			if (compareLess(tab[i], leftPivot)) {
				swap(tab, i, firstPivotIndex);
				firstPivotIndex++;
			} else if (compareGreaterEqual(tab[i], rightPivot)) {
				while (tab[secondPivotIndex] > rightPivot && i < secondPivotIndex)
					secondPivotIndex--;

				swap(tab, i, secondPivotIndex);
				secondPivotIndex--;

				if (compareLess(tab[i], leftPivot)) {
					swap(tab, i, firstPivotIndex);
					firstPivotIndex++;
				}
			}
		}
		firstPivotIndex--;
		secondPivotIndex++;

		swap(tab, begin, firstPivotIndex);
		swap(tab, end, secondPivotIndex);

		return new Pair<>(firstPivotIndex, secondPivotIndex);
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		getStatistics().increaseKeySwap();
	}
}
