package me.mikolaj.data;

import java.awt.*;

public class RBBstTreeItem extends BstTreeItem {
	private Color color;

	public RBBstTreeItem(Color color) {
		this.color = color;
	}

	public RBBstTreeItem(Integer value, Color color) {
		super(value);
		this.color = color;
	}

	public RBBstTreeItem(Integer value, BstTreeItem leftNode, BstTreeItem rightNode, Color color) {
		super(value, leftNode, rightNode);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
