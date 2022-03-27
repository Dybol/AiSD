package me.mikolaj;

import me.mikolaj.algorithms.*;
import me.mikolaj.generators.RandomTabGenerator;
import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

import java.io.File;
import java.io.FileWriter;

//https://drive.google.com/drive/folders/1UOdixGBtS9VpCI1i-Bmf4lpVnMRga6XC
public class Run {
	public static void main(String[] args) throws InterruptedException {

		Run.printFor("Insertion Sort", new InsertionSort());
		Run.printFor("Merge Sort", new MergeSort());
		Run.printFor("Quick Sort", new QuickSort());
		Run.printFor("Dual Pivot Quick Sort", new DualPivotQuickSort());

		Run.printData();

		Run.generateDataFor(new InsertionSort(), 1);
		Run.generateDataFor(new MergeSort(), 1);
		Run.generateDataFor(new QuickSort(), 1);
		Run.generateDataFor(new DualPivotQuickSort(), 1);
	}

	public static void printFor(String name, SortAlgorithm sortAlgorithm) throws InterruptedException {
		RandomTabGenerator randomTabGenerator = new RandomTabGenerator();

		System.out.println("----------------------" + name + "----------------------");
		Pair<int[], AlgorithmStatistics> random = sortAlgorithm.sortArray(8, randomTabGenerator.generate(8));
		System.out.println("Dlugosc 8 - posortowany losowo: " + random.getSecond());
		sortAlgorithm.resetStatistics();
		Pair<int[], AlgorithmStatistics> ascending = sortAlgorithm.sortArray(8, randomTabGenerator.generateAscendingArray(8));
		System.out.println("Dlugosc 8 - posortowany rosnaco: " + ascending.getSecond());
		sortAlgorithm.resetStatistics();
		Pair<int[], AlgorithmStatistics> descending = sortAlgorithm.sortArray(8, randomTabGenerator.generateDescendingArray(8));
		System.out.println("Dlugosc 8 - posortowany malejaco: " + descending.getSecond());
		sortAlgorithm.resetStatistics();
		System.out.println("-----------------------------------------------------------------");
		random = sortAlgorithm.sortArray(16, randomTabGenerator.generate(16));
		System.out.println("Dlugosc 16 - posortowany losowo: " + random.getSecond());
		sortAlgorithm.resetStatistics();
		ascending = sortAlgorithm.sortArray(16, randomTabGenerator.generateAscendingArray(16));
		System.out.println("Dlugosc 16 - posortowany rosnaco: " + ascending.getSecond());
		sortAlgorithm.resetStatistics();
		descending = sortAlgorithm.sortArray(16, randomTabGenerator.generateDescendingArray(16));
		System.out.println("Dlugosc 16 - posortowany malejaco: " + descending.getSecond());
		sortAlgorithm.resetStatistics();
		System.out.println("-----------------------------------------------------------------");
		random = sortAlgorithm.sortArray(32, randomTabGenerator.generate(32));
		System.out.println("Dlugosc 32 - posortowany losowo: " + random.getSecond());
		sortAlgorithm.resetStatistics();
		ascending = sortAlgorithm.sortArray(32, randomTabGenerator.generateAscendingArray(32));
		System.out.println("Dlugosc 32 - posortowany rosnaco: " + ascending.getSecond());
		sortAlgorithm.resetStatistics();
		descending = sortAlgorithm.sortArray(32, randomTabGenerator.generateDescendingArray(32));
		System.out.println("Dlugosc 32 - posortowany malejaco: " + descending.getSecond());
		sortAlgorithm.resetStatistics();
		System.out.println("-------------------------------------------------------------------");
		Thread.sleep(1000);
	}


	public static void printData() {
		for (int i = 100; i <= 1000; i += 100) {
			RandomTabGenerator generator = new RandomTabGenerator();
			InsertionSort insertionSort = new InsertionSort();
			MergeSort mergeSort = new MergeSort();
			QuickSort quickSort = new QuickSort();
			DualPivotQuickSort dualPivotQuickSort = new DualPivotQuickSort();

			int[] generatedArray1 = generator.generate(i);
			int[] generatedArray2 = generatedArray1.clone();
			int[] generatedArray3 = generatedArray1.clone();
			int[] generatedArray4 = generatedArray1.clone();

			Pair<int[], AlgorithmStatistics> insertionSortStatisticsPair = insertionSort.sortArray(i, generatedArray1);
			Pair<int[], AlgorithmStatistics> mergeSortStatisticsPair = mergeSort.sortArray(i, generatedArray2);
			Pair<int[], AlgorithmStatistics> quickSortStatisticsPair = quickSort.sortArray(i, generatedArray3);
			Pair<int[], AlgorithmStatistics> dualPivotQuickSortStatisticsPair = dualPivotQuickSort.sortArray(i, generatedArray4);

			System.out.println("Dla i = " + i + ":");
			System.out.println("Insertion sort: " + insertionSortStatisticsPair.getSecond());
			System.out.println("Merge sort: " + mergeSortStatisticsPair.getSecond());
			System.out.println("Quick sort: " + quickSortStatisticsPair.getSecond());
			System.out.println("Dual pivot quick sort: " + dualPivotQuickSortStatisticsPair.getSecond());
			System.out.println("----------------------------------------");
		}
	}

	public static void generateDataFor(SortAlgorithm sortAlgorithm, int k) {
		File outputFile = new File("measurements/" + sortAlgorithm.getClass().getSimpleName() + k + ".txt");
		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			fileWriter.write("n,c,s\n");
			for (int i = 100; i <= 1000; i += 100) {
				int keyComparison = 0;
				int keySwap = 0;
				for (int j = 0; j < k; j++) {
					RandomTabGenerator generator = new RandomTabGenerator();

					int[] generatedArray = generator.generate(i);

					Pair<int[], AlgorithmStatistics> arrayStatisticsPair = sortAlgorithm.sortArray(i, generatedArray);

					AlgorithmStatistics statistics = arrayStatisticsPair.getSecond();
					keyComparison += statistics.getKeyComparison();
					keySwap += statistics.getKeySwap();
					statistics.reset();
				}

				fileWriter.write(i + "," + keyComparison / k + "," + keySwap / k + "\n");
			}
			System.out.println("Zapis pomyslny dla " + sortAlgorithm.getClass().getSimpleName());
		} catch (Throwable ignored) {
			System.out.println("Nie udalo sie wygenerowac danych!");
		}
	}
}
