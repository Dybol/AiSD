package me.mikolaj;

import me.mikolaj.algorithms.BST;
import me.mikolaj.generators.RandomTabGenerator;

public class Run {
	public static void main(String[] args) {
		ex1();
	}

	public static void ex1() {
		BST bst = new BST();

		bst.insertRandomElements(50);
		bst.deleteRandomElements(50);

		bst.setRoot(null);

		System.out.println("-------------------------------------");
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		int[] ascendingArray = tabGenerator.generateAscendingArray(50);
		bst.insertFromArray(ascendingArray);
		bst.deleteRandomElements(50);
	}
}
