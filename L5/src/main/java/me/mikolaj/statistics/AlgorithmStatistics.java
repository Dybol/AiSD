package me.mikolaj.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AlgorithmStatistics {
	private Integer keyComparison = 0;
	private Integer keySwap = 0;

	public AlgorithmStatistics() {
	}

	public AlgorithmStatistics(Integer keyComparison, Integer keySwap) {
		this.keyComparison = keyComparison;
		this.keySwap = keySwap;
	}

	public Integer getKeyComparison() {
		return keyComparison;
	}

	public void setKeyComparison(Integer keyComparison) {
		this.keyComparison = keyComparison;
	}

	public void increaseKeyComparison() {
		keyComparison++;
	}

	public Integer getKeySwap() {
		return keySwap;
	}

	public void setKeySwap(Integer keySwap) {
		this.keySwap = keySwap;
	}

	public void increaseKeySwap() {
		keySwap++;
	}

	public void reset() {
		keyComparison = 0;
		keySwap = 0;
	}

	public boolean compareLessEqual(int i, int j) {
		increaseKeyComparison();
		return i <= j;
	}

	public boolean compareGreaterEqual(int i, int j) {
		increaseKeyComparison();
		return i >= j;
	}

	public boolean compareLess(int i, int j) {
		increaseKeyComparison();
		return i < j;
	}

	public boolean compareGreater(int i, int j) {
		increaseKeyComparison();
		return i > j;
	}

	public boolean compareEqual(int i, int j) {
		increaseKeyComparison();
		return i == j;
	}

	public int[] swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

		increaseKeySwap();

		return arr;
	}

	public ArrayList<Integer> swap(ArrayList<Integer> array, int i, int j) {
		int temp = array.get(j);
		array.set(j, array.get(i));
		array.set(i, temp);

		increaseKeySwap();

		return array;
	}
}
