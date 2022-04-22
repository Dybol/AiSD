package me.mikolaj;

import me.mikolaj.algorithms.Algorithm;
import me.mikolaj.algorithms.RandomSelect;
import me.mikolaj.algorithms.Select;
import me.mikolaj.generators.RandomTabGenerator;
import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

import java.io.File;
import java.io.FileWriter;

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

		checkComparisonsAndSwapsFor(select, 100, 10);
		checkComparisonsAndSwapsFor(randomSelect, 100, 10);
	}


	public static void checkComparisonsAndSwapsFor(Algorithm algorithm, int m, int k) {
		File outputFile = new File("measurements/" + algorithm.getClass().getSimpleName() + k + ".txt");
		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			fileWriter.write("n,c,s\n");
			for (int i = 100; i <= 10_000; i++) {
				int keyComparison = 0;
				int keySwap = 0;
				for (int j = 0; j < m; j++) {
					RandomTabGenerator generator = new RandomTabGenerator();

					int[] generatedArray = generator.generate(i);

					Pair<Integer, AlgorithmStatistics> arrayStatisticsPair = algorithm.countPositionalStatistic(i, k, generatedArray);

					AlgorithmStatistics statistics = arrayStatisticsPair.getSecond();
					keyComparison += statistics.getKeyComparison();
					keySwap += statistics.getKeySwap();
					statistics.reset();
				}

				fileWriter.write(i + "," + keyComparison / m + "," + keySwap / m + "\n");
			}
		} catch (Throwable ignored) {
			System.out.println("Nie udalo sie wygenerowac danych!");
		}
	}
}


