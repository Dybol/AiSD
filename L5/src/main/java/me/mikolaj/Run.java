package me.mikolaj;

import me.mikolaj.algorithms.Heapsort;
import me.mikolaj.algorithms.LCS;
import me.mikolaj.algorithms.PriorityQueue;
import me.mikolaj.generators.RandomTabGenerator;
import me.mikolaj.measurements.Measurements;

import java.util.ArrayList;

public class Run {
	public static void main(String[] args) {

		heapSort();

//		priorityQueue();

//		lcs();

//		Measurements.checkComplexityForLCS(new LCS(), 20);

//		Measurements.checkComparisonsAndSwapsForPriorityQueue(new PriorityQueue(), 1);

//		Measurements.checkComparisonsAndSwapsFor(heapsort, 100);
	}

	public static void priorityQueue() {
		ArrayList<Integer> array = new ArrayList<>();

		PriorityQueue queue = new PriorityQueue();
		queue.insert(array, 3);
		queue.insert(array, 4);
		queue.insert(array, 9);
		queue.insert(array, 5);
		queue.insert(array, 2);
		queue.insert(array, 7);
		queue.insert(array, 8);

		System.out.println("Max-Heap array: ");
		queue.printArray(array);

		queue.printQueue(array);

		queue.deleteNode(array, 4);
		System.out.println("After deleting an element: ");
		queue.printArray(array);
	}

	public static void heapSort() {

		Heapsort heapsort = new Heapsort();
		heapsort.sort(new int[]{1, 4, 19, 2, 3, 13, 15, 5});

		for (int i = 10; i <= 100; i += 10) {
			RandomTabGenerator generator = new RandomTabGenerator();

			int[] generatedArray = generator.generate(i);

			System.out.println("Poczatkowy stan tablicy: ");
			generator.printArray(generatedArray);

			heapsort.sort(generatedArray);

			System.out.println("Koncowy stan tablicy dla i = " + i + ":");
			heapsort.printArray(generatedArray);
			System.out.println("----------------------------------------");
		}
	}

	public static void lcs() {
		LCS lcs = new LCS();
		System.out.println(lcs.findLCS("ABEDM", "ABCDEG"));
		System.out.println(lcs.findLCS("ALBERTA", "ABORT"));

		for (int j = 10; j <= 100; j += 10) {
			String firstString = Measurements.generateString(j);
			String secondString = Measurements.generateString(j);
			System.out.println(lcs.findLCS(firstString, secondString));
		}
	}
}
