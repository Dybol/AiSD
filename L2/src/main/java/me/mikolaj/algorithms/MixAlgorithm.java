package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

public class MixAlgorithm extends SortAlgorithm {

	@Override
	public Pair<int[], AlgorithmStatistics> sortArray(int n, int[] tab) {
		if (n == 1)
			return new Pair<>(tab, getStatistics());

		if (n < 10) {
			return insertionSort(n, tab);
		} else {
			return divide(n, tab);
		}
	}

	public Pair<int[], AlgorithmStatistics> insertionSort(int n, int[] tab) {
		for (int j = 1; j < n; j++) {
			int key = tab[j];
			int i = j - 1;
			while (i >= 0 && compareGreater(tab[i], key)) {
				getStatistics().increaseKeySwap();
				tab[i + 1] = tab[i];
				i--;
			}
			tab[i + 1] = key;
			getStatistics().increaseKeySwap();
		}

		return new Pair<>(tab, getStatistics());
	}

	protected Pair<int[], AlgorithmStatistics> divide(int n, int[] tab) {
		int[] firstPart = new int[(n + 1) / 2];
		int[] secondPart = new int[(n - firstPart.length)];

		System.arraycopy(tab, 0, firstPart, 0, firstPart.length);
		System.arraycopy(tab, firstPart.length, secondPart, 0, secondPart.length);

		int[] sortedFirstPart = sortArray(firstPart.length, firstPart).getFirst();
		int[] sortedSecondPart = sortArray(secondPart.length, secondPart).getFirst();

		return new Pair<>(merge(sortedFirstPart, sortedSecondPart), getStatistics());
	}

	@SuppressWarnings("DuplicatedCode")
	protected int[] merge(int[] firstTab, int[] secondTab) {
		int firstArrayLength = firstTab.length;
		int secondArrayLength = secondTab.length;
		int[] newArray = new int[firstArrayLength + secondArrayLength];

		int i = 0;
		int j = 0;
		int k = 0;
		while (compareLess(i, firstArrayLength) && compareLess(j, secondArrayLength)) {
			getStatistics().increaseKeyComparison();
			if (firstTab[i] > secondTab[j]) {
				newArray[k] = secondTab[j];
				j++;
			} else {
				newArray[k] = firstTab[i];
				i++;
			}
			k++;
		}

		while (compareLess(i, firstArrayLength)) {
			getStatistics().increaseKeyComparison();
			newArray[k] = firstTab[i];
			k++;
			i++;
		}

		while (compareLess(j, secondArrayLength)) {
			getStatistics().increaseKeyComparison();
			newArray[k] = secondTab[j];
			k++;
			j++;
		}

		return newArray;
	}
}
