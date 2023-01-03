/*
 * Created by 陳奕利
 */
package model;

import java.util.ArrayList;

public class WebNode {
	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage page;
	public double score;
	public WebNode left, right;

	public WebNode(WebPage page) {
		this.page = page;
		this.children = new ArrayList<>();
		left = right = null;
	}

	public void addChild(WebNode child) {
		child.parent = this;
		this.children.add(child);
	}

	public int getDepth() {
		int retVal = 1;
		WebNode currNode = this;
		while (currNode.parent != null) {
			retVal++;
			currNode = currNode.parent;
		}
		return retVal;
	}

	public boolean isTheLastChild() {
		if (this.parent == null)
			return true;
		ArrayList<WebNode> siblings = this.parent.children;

		return this.equals(siblings.get(siblings.size() - 1));
	}

	public void setNodeScore(ArrayList<Keyword> keywords) {

		page.setScore(keywords);
		// **set webPage score to nodeScore
		score = page.score;
		for (WebNode child : children) {
			child.setNodeScore(keywords);
			score += child.score;
		}
	}

}
