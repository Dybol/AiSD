package me.mikolaj.algorithms;

import me.mikolaj.data.TreeItem;
import me.mikolaj.generators.RandomTabGenerator;

public class BST extends Tree {

	private TreeItem root;

	public BST() {
		this.root = null;
	}

	@Override
	public void insert(int k) {
		System.out.println("insert " + k);
		root = insertRecursive(root, k);
		printTree();
	}

	@Override
	public void delete(int k) {
		System.out.println("delete " + k);
		if (search(k) == null) {
			System.out.println("klucza " + k + " nie ma w drzewie, wiec nie mozemy go usunac ");
			return;
		}
		deleteRecursive(root, k);
		printTree();
	}

	public void insertRandomElements(int numOfElements) {
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		insertFromArray(tabGenerator.generate(numOfElements));
	}

	public void insertFromArray(int[] tab) {
		for (int i : tab)
			insert(i);
	}

	private TreeItem insertRecursive(TreeItem root, int k) {
		if (root != null) {
			if (root.getValue() > k) {
				root.setLeftNode(insertRecursive(root.getLeftNode(), k));
			} else {
				root.setRightNode(insertRecursive(root.getRightNode(), k));
			}
		} else {
			root = new TreeItem(k);
		}
		return root;
	}

	public void deleteRandomElements(int numOfElements) {
		RandomTabGenerator tabGenerator = new RandomTabGenerator();
		deleteFromArray(tabGenerator.generate(numOfElements));
	}

	public void deleteFromArray(int[] tab) {
		for (int i : tab)
			delete(i);
	}

	private TreeItem deleteRecursive(TreeItem root, int k) {
		if (root == null)
			return null;

		if (root.getValue() > k) {
			root.setLeftNode(deleteRecursive(root.getLeftNode(), k));
		} else if (root.getValue() < k) {
			root.setRightNode(deleteRecursive(root.getRightNode(), k));
		} else {
			//tree contains one or 0 children
			if (root.getLeftNode() == null)
				return root.getRightNode();
			else if (root.getRightNode() == null)
				return root.getLeftNode();

			root.setValue(findMinValue(root.getRightNode()));

			//delete successor
			root.setRightNode(deleteRecursive(root.getRightNode(), root.getValue()));
		}

		return root;
	}

	private int findMinValue(TreeItem root) {
		int minValue = root.getValue();
		while (root.getLeftNode() != null) {
			minValue = root.getLeftNode().getValue();
			root = root.getLeftNode();
		}
		return minValue;
	}

	public int countHeight() {
		return countHeightRecursive(root);
	}

	private int countHeightRecursive(TreeItem root) {
		if (root == null)
			return -1;
		else {
			// compute the depth of each subtree
			int lDepth = countHeightRecursive(root.getLeftNode());
			int rDepth = countHeightRecursive(root.getRightNode());

			// use the larger one
			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}
	}

	public TreeItem search(int k) {
		return searchRecursive(root, k);
	}

	private TreeItem searchRecursive(TreeItem root, int k) {
		if (root == null)
			return null;

		if (root.getValue() == k)
			return root;
		else if (root.getValue() > k) {
			return searchRecursive(root.getLeftNode(), k);
		} else {
			return searchRecursive(root.getRightNode(), k);
		}
	}

	public void printTree() {
		System.out.println(traversePreOrder(root));
	}

	private String traversePreOrder(TreeItem root) {

		if (root == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(root.getValue());

		String pointerRight = "└──";
		String pointerLeft = (root.getRightNode() != null) ? "├──" : "└──";

		traverseNodes(sb, "", pointerLeft, root.getLeftNode(), root.getRightNode() != null);
		traverseNodes(sb, "", pointerRight, root.getRightNode(), false);

		return sb.toString();
	}

	private void traverseNodes(StringBuilder sb, String padding, String pointer, TreeItem node,
							   boolean hasRightSibling) {
		if (node != null) {
			sb.append("\n");
			sb.append(padding);
			sb.append(pointer);
			sb.append(node.getValue());

			StringBuilder paddingBuilder = new StringBuilder(padding);
			if (hasRightSibling) {
				paddingBuilder.append("│  ");
			} else {
				paddingBuilder.append("   ");
			}

			String paddingForBoth = paddingBuilder.toString();
			String pointerRight = "└──";
			String pointerLeft = (node.getRightNode() != null) ? "├──" : "└──";

			traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeftNode(), node.getRightNode() != null);
			traverseNodes(sb, paddingForBoth, pointerRight, node.getRightNode(), false);
		}
	}

	public TreeItem getRoot() {
		return root;
	}

	public void setRoot(TreeItem root) {
		this.root = root;
	}
}
