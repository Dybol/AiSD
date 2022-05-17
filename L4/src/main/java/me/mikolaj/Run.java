package me.mikolaj;

import me.mikolaj.algorithms.BST;
import me.mikolaj.algorithms.RBBst;
import me.mikolaj.data.RBBstTreeItem;
import me.mikolaj.generators.RandomTabGenerator;

public class Run {
	public static void main(String[] args) {
//		ex1();
		ex3();
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

		System.out.println(bst.countHeight());
	}

	public static void ex3() {
		RBBst rbBst = new RBBst();
		rbBst.insertRandomElements(50);
		rbBst.deleteRandomElements(50);

		rbBst.setRoot((RBBstTreeItem) null);

		System.out.println("-------------------------------------");
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		int[] ascendingArray = tabGenerator.generateAscendingArray(50);
		rbBst.insertFromArray(ascendingArray);
		rbBst.deleteRandomElements(50);

		System.out.println(rbBst.countHeight());
	}
}
