package me.mikolaj;

import me.mikolaj.algorithms.RandomSelect;
import me.mikolaj.generators.RandomTabGenerator;
import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

public class Run {
	public static void main(String[] args) {
		RandomTabGenerator generator = new RandomTabGenerator();
		int[] tab = generator.generate(100000);
		RandomSelect randomSelect = new RandomSelect();
		randomSelect.printArray(tab);
		Pair<Integer, AlgorithmStatistics> integerAlgorithmStatisticsPair = randomSelect.countPositionalStatistic(100000, 56910, tab);
		randomSelect.printArray(tab);
		System.out.println(integerAlgorithmStatisticsPair.getFirst());
		System.out.println(integerAlgorithmStatisticsPair.getSecond());
	}
}


