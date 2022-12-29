/*
 * Created by 陳奕利
 */
package model;

import java.util.HashSet;
import java.util.Set;

public class WebNode {
	public WebNode parent;
	public Set<WebNode> children;
	public WebPage page;
	public double score;

	public WebNode(WebPage page) {
		this.page = page;
		this.children = new HashSet<>();
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

}
