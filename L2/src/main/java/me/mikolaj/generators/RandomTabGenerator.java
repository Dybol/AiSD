package me.mikolaj.generators;

import java.util.Arrays;
import java.util.Random;

public class RandomTabGenerator implements Generator {

	@Override
	public int[] generate(int n) {
		Random random = new Random();
		int[] randomTab = new int[n];
		for (int i = 0; i < n; i++) {
			randomTab[i] = Math.abs(random.nextInt() % (2 * n + 1));
		}

		return randomTab;
	}

	public int[] generateAscendingArray(int n) {
		return getAscendingArray(generate(n));
	}

	public int[] generateDescendingArray(int n) {
		return getDescendingArray(generate(n));
	}

	public int[] getAscendingArray(int[] array) {
		Arrays.sort(array);
		return array;
	}

	public int[] getDescendingArray(int[] array) {
		Arrays.sort(array);
		return reverse(array);
	}

	private int[] reverse(int[] data) {
		for (int left = 0, right = data.length - 1; left < right; left++, right--) {
			// swap the values at the left and right indices
			int temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
		return data;
	}
}
