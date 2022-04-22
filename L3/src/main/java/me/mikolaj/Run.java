package me.mikolaj;

import me.mikolaj.algorithms.Algorithm;
import me.mikolaj.algorithms.Select;
import me.mikolaj.generators.RandomTabGenerator;
import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

import java.io.File;
import java.io.FileWriter;

public class Run {
	public static void main(String[] args) {
		Select select = new Select();

		checkComparisonsAndSwapsFor(select, 100, 10, 5);

		Select select3 = new Select();
		select3.setNumOfTabsToDivide(3);
		checkComparisonsAndSwapsFor(select3, 100, 10, 3);

		Select select7 = new Select();
		select3.setNumOfTabsToDivide(7);
		checkComparisonsAndSwapsFor(select7, 100, 10, 7);

		Select select9 = new Select();
		select3.setNumOfTabsToDivide(9);
		checkComparisonsAndSwapsFor(select9, 100, 10, 9);
	}


	public static void checkComparisonsAndSwapsFor(Algorithm algorithm, int m, int k, int numOfTabs) {
		File outputFile = new File("measurements/" + algorithm.getClass().getSimpleName() + "divTo" + numOfTabs + ".txt");
		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			fileWriter.write("n,c,s\n");
			for (int i = 100; i <= 10_000; i += 100) {
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
				System.out.println("Dla i  = " + i);
				fileWriter.write(i + "," + keyComparison / m + "," + keySwap / m + "\n");
			}
		} catch (Throwable ignored) {
			System.out.println("Nie udalo sie wygenerowac danych!");
		}
	}
}


