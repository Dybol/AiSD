package me.mikolaj;

import me.mikolaj.algorithms.InsertionSort;
import me.mikolaj.algorithms.MergeSort;
import me.mikolaj.algorithms.QuickSort;
import me.mikolaj.algorithms.SortAlgorithm;
import me.mikolaj.generators.RandomTabGenerator;
import me.mikolaj.statistics.AlgorithmStatistics;
import me.mikolaj.utils.Pair;

//https://drive.google.com/drive/folders/1UOdixGBtS9VpCI1i-Bmf4lpVnMRga6XC
public class Run {
	public static void main(String[] args) {

		Run.printFor("Insertion Sort", new InsertionSort());
		Run.printFor("Merge Sort", new MergeSort());
		Run.printFor("Quick Sort", new QuickSort());

		Run.generateData();
	}

	public static void printFor(String name, SortAlgorithm sortAlgorithm) {
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
	}

	public static void generateData() {
		for (int i = 100; i <= 1000; i += 100) {
			RandomTabGenerator generator = new RandomTabGenerator();
			InsertionSort insertionSort = new InsertionSort();
			MergeSort mergeSort = new MergeSort();
			QuickSort quickSort = new QuickSort();

			int[] generatedArray1 = generator.generate(i);
			int[] generatedArray2 = generatedArray1.clone();
			int[] generatedArray3 = generatedArray1.clone();

			Pair<int[], AlgorithmStatistics> insertionSortStatisticsPair = insertionSort.sortArray(i, generatedArray1);
			Pair<int[], AlgorithmStatistics> mergeSortStatisticsPair = mergeSort.sortArray(i, generatedArray2);
			Pair<int[], AlgorithmStatistics> quickSortStatisticsPair = quickSort.sortArray(i, generatedArray3);

			System.out.println("----------------------------------------");
			System.out.println(insertionSortStatisticsPair.getSecond());
			System.out.println(mergeSortStatisticsPair.getSecond());
			System.out.println(quickSortStatisticsPair.getSecond());
			System.out.println("----------------------------------------");
		}
	}
}
