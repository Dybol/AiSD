package me.mikolaj;

import me.mikolaj.algorithms.RandomSelect;
import me.mikolaj.algorithms.Select;
import me.mikolaj.generators.RandomTabGenerator;
import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

public class Run {
	public static void main(String[] args) {

		int dataLength = 30;

		RandomTabGenerator generator = new RandomTabGenerator();
		int[] tab = generator.generate(dataLength);
		int[] tab2 = tab.clone();
		RandomSelect randomSelect = new RandomSelect();

		Pair<Integer, AlgorithmStatistics> integerAlgorithmStatisticsPair = randomSelect.countPositionalStatistic(dataLength, 5, tab);
		System.out.println(integerAlgorithmStatisticsPair.getFirst());
		System.out.println(integerAlgorithmStatisticsPair.getSecond());

		Select select = new Select();
		Pair<Integer, AlgorithmStatistics> statsForSelect = select.countPositionalStatistic(dataLength, 5, tab2);
		System.out.println(statsForSelect.getFirst());
		System.out.println(statsForSelect.getSecond());
	}
}


