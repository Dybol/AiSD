package me.mikolaj.measurements;

import me.mikolaj.algorithms.Heapsort;
import me.mikolaj.algorithms.LCS;
import me.mikolaj.algorithms.PriorityQueue;
import me.mikolaj.generators.RandomTabGenerator;
import me.mikolaj.statistics.AlgorithmStatistics;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Measurements {

	public static void checkComparisonsAndSwapsForHeapsort(Heapsort algorithm, int k) {
		File outputFile = new File("measurements/" + algorithm.getClass().getSimpleName() + k + ".txt");
		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			fileWriter.write("n,c,s\n");
			for (int i = 100; i <= 1_000; i += 100) {
				int keyComparison = 0;
				int keySwap = 0;
				for (int j = 0; j < k; j++) {
					RandomTabGenerator generator = new RandomTabGenerator();

					int[] generatedArray = generator.generate(i);

					algorithm.sort(generatedArray);

					AlgorithmStatistics statistics = algorithm.getStatistics();
					keyComparison += statistics.getKeyComparison();
					keySwap += statistics.getKeySwap();
					statistics.reset();
				}
				System.out.println("Dla i = " + i);

				fileWriter.write(i + "," + keyComparison / k + "," + keySwap /k + "\n");
			}
		} catch (Throwable ignored) {
			System.out.println("Nie udalo sie wygenerowac danych!");
		}
	}
	public static void checkComparisonsAndSwapsForPriorityQueue(PriorityQueue priorityQueue, int k) {
		File outputFile = new File("measurements/" + priorityQueue.getClass().getSimpleName() + k + ".txt");
		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			fileWriter.write("n,c,s\n");
			for (int i = 10000; i <= 100_000; i += 10000) {
				int keyComparison = 0;
				int keySwap = 0;
				for (int j = 0; j < k; j++) {
					RandomTabGenerator generator = new RandomTabGenerator();

					int[] generatedArray = generator.generate(i);
					ArrayList<Integer> queue = new ArrayList<>();
					for(int value: generatedArray) {
//						priorityQueue.heapify(generatedArray, i, );
						priorityQueue.insert(queue, value);
					}

					for(int x = 0; x < i-1; x++)
						priorityQueue.extractMax(queue);

					AlgorithmStatistics statistics = priorityQueue.getStatistics();
					keyComparison += statistics.getKeyComparison();
					keySwap += statistics.getKeySwap();
					statistics.reset();
				}
				System.out.println("Dla i = " + i);

				fileWriter.write(i + "," + keyComparison / k + "," + keySwap /k + "\n");
			}
		} catch (Throwable ignored) {
			ignored.printStackTrace();
			System.out.println("Nie udalo sie wygenerowac danych!");
		}
	}

	public static void checkComplexityForLCS(LCS lcs, int k) {
		File outputFile = new File("measurements/" + lcs.getClass().getSimpleName() + k + ".txt");
		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			fileWriter.write("n,c,s\n");
			for (int i = 1000; i <= 10_000; i += 1000) {

				long now = System.currentTimeMillis();
				for (int j = 0; j < k; j++) {
					String firstString = generateString(i);
					String secondString = generateString(i);
					lcs.findLCS(firstString, secondString);
				}
				long finish = System.currentTimeMillis();
				System.out.println("Dla i = " + i);

				fileWriter.write(i + "," + ((finish - now)/k) + "\n");
			}
		} catch (Throwable ignored) {
			ignored.printStackTrace();
			System.out.println("Nie udalo sie wygenerowac danych!");
		}
	}

	public static String generateString(int length) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1)
				.limit(length)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
	}
}
