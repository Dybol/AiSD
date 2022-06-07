package me.mikolaj.algorithms;

import java.util.ArrayList;

public class PriorityQueue extends Heap {

	public void insert(ArrayList<Integer> array, int newNum) {
		int size = array.size();
		array.add(newNum);
		if (!getStatistics().compareEqual(size, 0)) {
			for (int i = size / 2 - 1; i >= 0; i--) {
				heapify(array, i);
			}
		}
	}

	public void extractMax(ArrayList<Integer> array) {
		//zakladamy, ze tablica jest juz w postaci kopca
		deleteNode(array, array.get(0));
	}

	// Function to delete an element from the tree
	public void deleteNode(ArrayList<Integer> array, int num) {
		int size = array.size();
		int i;
		for (i = 0; i < size; i++) {
			if (getStatistics().compareEqual(num, array.get(i)))
				break;
		}

		int temp = array.get(i);
		array.set(i, array.get(size - 1));
		array.set(size - 1, temp);

		array.remove(size - 1);
		for (int j = size / 2 - 1; j >= 0; j--) {
			heapify(array, j);
		}
	}

	public void printQueue(ArrayList<Integer> array) {
		StringBuilder sb = new StringBuilder();
		int max = 0;
		for (int i = 0; i < array.size(); i++) {
			for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) <= array.size(); j++) {
				if (j > max) {
					max = j;
				}
			}

		}

		for (int i = 0; i < array.size(); i++) {
			for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) <= array.size(); j++) {

				sb.append(" ".repeat(Math.max(0, max / ((int) Math.pow(2, i)))));
				sb.append(array.get(j + (int) Math.pow(2, i) - 1)).append(" ");

			}
			sb.append("\n");
		}

		System.out.println(sb);
	}


}
