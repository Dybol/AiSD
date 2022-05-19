package me.mikolaj.measurements;

import me.mikolaj.algorithms.BST;
import me.mikolaj.algorithms.RBBst;
import me.mikolaj.algorithms.SplayTree;
import me.mikolaj.generators.RandomTabGenerator;
import me.mikolaj.statistics.AlgorithmStatistics;

import java.io.File;
import java.io.FileWriter;

public class Measurements {
	public void countStatisticsForBstTree(BST bst, int m) {
		File outputFile = new File("results/" + bst.getClass().getSimpleName() + "-rand.txt");

		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			fileWriter.write("n,c,p,h\n");
			for (int i = 1000; i <= 100_000; i += 1000) {
				int keyComparison = 0;
				int pointersRead = 0;
				int maxHeight = 0;
				for (int j = 0; j < m; j++) {
					RandomTabGenerator tabGenerator = new RandomTabGenerator();

//					int[] ascendingArray = tabGenerator.generateAscendingArray(i);
//					bst.insertFromArray(ascendingArray);
					bst.insertRandomElements(i);
					bst.deleteRandomElements(i);

					AlgorithmStatistics statistics = bst.getStatistics();
					keyComparison += statistics.getKeyComparison();
					pointersRead += statistics.getPointerRead();
					if (statistics.getHeight() > maxHeight)
						maxHeight = statistics.getHeight();
					statistics.reset();
					bst = new BST();
				}
				System.out.println("Dla i = " + i);

				fileWriter.write(i + "," + keyComparison / m + "," + pointersRead / m + "," + maxHeight + "\n");
			}
		} catch (Throwable ignored) {
			System.out.println("Nie udalo sie wygenerowac danych!");
		}
	}

	public void countStatisticsForRbBstTree(RBBst rbBst, int m) {
		File outputFile = new File("results/" + rbBst.getClass().getSimpleName() + "-rand.txt");
		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			fileWriter.write("n,c,p,h\n");
			for (int i = 10000; i <= 100_000; i += 10000) {
				int keyComparison = 0;
				int pointersRead = 0;
				int maxHeight = 0;
				for (int j = 0; j < m; j++) {
//					RandomTabGenerator tabGenerator = new RandomTabGenerator();
//					int[] ascendingArray = tabGenerator.generateAscendingArray(i);
//					rbBst.insertFromArray(ascendingArray);

					rbBst.insertRandomElements(i);
					rbBst.deleteRandomElements(i);

					AlgorithmStatistics statistics = rbBst.getStatistics();
					keyComparison += statistics.getKeyComparison();
					pointersRead += statistics.getPointerRead();
					if (statistics.getHeight() > maxHeight)
						maxHeight = statistics.getHeight();
					statistics.reset();
					rbBst = new RBBst();
				}
				System.out.println("Dla i = " + i);

				fileWriter.write(i + "," + keyComparison / m + "," + pointersRead / m + "," + maxHeight + "\n");
			}
		} catch (Throwable ignored) {
			System.out.println("Nie udalo sie wygenerowac danych!");
		}
	}

	public void countStatisticsForSplayTree(SplayTree splayTree, int m) {
		File outputFile = new File("results/" + splayTree.getClass().getSimpleName() + "-rand.txt");
		try (FileWriter fileWriter = new FileWriter(outputFile)) {
			fileWriter.write("n,c,p,h\n");
			for (int i = 10000; i <= 100_000; i += 10000) {
				int keyComparison = 0;
				int pointersRead = 0;
				int maxHeight = 0;
				for (int j = 0; j < m; j++) {

//					RandomTabGenerator tabGenerator = new RandomTabGenerator();
//					int[] ascendingArray = tabGenerator.generateAscendingArray(i);
//					splayTree.insertFromArray(ascendingArray);
					splayTree.insertRandomElements(i);
					splayTree.deleteRandomElements(i);

					AlgorithmStatistics statistics = splayTree.getStatistics();
					keyComparison += statistics.getKeyComparison();
					pointersRead += statistics.getPointerRead();
					maxHeight = splayTree.countHeight();
//					if (statistics.getHeight() > maxHeight)
//						maxHeight = statistics.getHeight();
					statistics.reset();
					splayTree = new SplayTree();
				}
				System.out.println("Dla i = " + i);

				fileWriter.write(i + "," + keyComparison / m + "," + pointersRead / m + "," + maxHeight + "\n");
			}
		} catch (Throwable ignored) {
			System.out.println("Nie udalo sie wygenerowac danych!");


		}
	}
}
