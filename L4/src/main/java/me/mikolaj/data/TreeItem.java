package me.mikolaj.data;

public class TreeItem {

	private Integer value;
	private TreeItem leftNode;
	private TreeItem rightNode;

	public TreeItem() {
	}

	public TreeItem(Integer value) {
		this.value = value;
		leftNode = null;
		rightNode = null;
	}

	public TreeItem(Integer value, TreeItem leftNode, TreeItem rightNode) {
		this.value = value;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public TreeItem getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(TreeItem leftNode) {
		this.leftNode = leftNode;
	}

	public TreeItem getRightNode() {
		return rightNode;
	}

	public void setRightNode(TreeItem rightNode) {
		this.rightNode = rightNode;
	}
}
