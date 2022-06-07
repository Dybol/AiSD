package me.mikolaj.algorithms;

public class LCS {

	public String findLCS(String first, String second) {

		String[] firstSplit = ("X" + first).split("");
		String[] secondSplit = ("X" + second).split("");

		int firstLength = first.length() + 1;
		int secondLength = second.length() + 1;

		int[][] matrix = new int[firstLength][secondLength];

		for (int i = 0; i < firstLength; i++) {
			for (int j = 0; j < secondLength; j++) {
				if (i == 0 || j == 0) {
					matrix[i][j] = 0;
				} else if (firstSplit[i].equals(secondSplit[j])) {
					matrix[i][j] = 1 + matrix[i - 1][j - 1];
				} else {
					matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
				}
			}
		}

//		printMatrix(matrix, firstLength, secondLength);

		return traverseBackwards(first, second, matrix, firstLength - 1, secondLength - 1, new StringBuilder());
	}

	public String traverseBackwards(String first, String second, int[][] matrix, int i, int j, StringBuilder builder) {

		int currentValue = matrix[i][j];
		if(i-1 >=0 && j-1 >= 0 && matrix[i-1][j-1] == currentValue)
			return traverseBackwards(first, second, matrix, i-1, j-1, builder);
		else if(i - 1 >= 0 && matrix[i - 1][j] == currentValue)
			return traverseBackwards(first, second, matrix, i-1, j, builder);
		else if(j - 1 >= 0 && matrix[i][j-1] == currentValue)
			return traverseBackwards(first, second, matrix, i, j-1, builder);
		else if (j - 1 >= 0 && i - 1 >= 0)
			return traverseBackwards(first, second, matrix, i-1, j-1, builder.append(first.split("")[i-1]));
		else
			return builder.reverse().toString();
	}

	public void printMatrix(int[][] matrix, int i, int j) {
		for(int x = 0; x < i; x++) {
			for(int y = 0; y < j; y++) {
				System.out.print(matrix[x][y] + " ");
			}
			System.out.println();
		}
	}
}
