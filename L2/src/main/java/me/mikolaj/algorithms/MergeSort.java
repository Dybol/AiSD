package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

public class MergeSort extends SortAlgorithm {

	private boolean compare(int i, int j) {
		getStatistics().increaseKeyComparison();
		return i < j;
	}

	@Override
	public Pair<int[], AlgorithmStatistics> sortArray(int n, int[] tab) {
		if (n == 1)
			return new Pair<>(tab, getStatistics());

		int[] firstPart = new int[(n + 1) / 2];
		int[] secondPart = new int[(n - firstPart.length)];

		System.arraycopy(tab, 0, firstPart, 0, firstPart.length);
		System.arraycopy(tab, firstPart.length, secondPart, 0, secondPart.length);

		int[] sortedFirstPart =  sortArray(firstPart.length, firstPart).getFirst();
		int[] sortedSecondPart = sortArray(secondPart.length, secondPart).getFirst();

		return new Pair<>(merge(sortedFirstPart, sortedSecondPart), getStatistics());
	}

	private int[] merge(int[] firstTab, int[] secondTab) {
		int firstArrayLength = firstTab.length;
		int secondArrayLength = secondTab.length;
		int[] newArray = new int[firstArrayLength + secondArrayLength];

		int i = 0;
		int j = 0;
		int k = 0;
		while (compare(i, firstArrayLength) && compare(j, secondArrayLength)) {
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

		while (compare(i, firstArrayLength)) {
			getStatistics().increaseKeyComparison();
			newArray[k] = firstTab[i];
			k++;
			i++;
		}

		while (compare(j, secondArrayLength)) {
			getStatistics().increaseKeyComparison();
			newArray[k] = secondTab[j];
			k++;
			j++;
		}

		return newArray;
	}
}
