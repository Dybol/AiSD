package me.mikolaj.algorithms;

import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

public class InsertionSort extends SortAlgorithm {

	@Override
	public Pair<int[], AlgorithmStatistics> sortArray(int n, int[] tab) {
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
}
