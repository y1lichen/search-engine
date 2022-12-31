package model;

public class WebTree {
	public WebNode root;

	public WebTree(WebPage a) {
		this.root = new WebNode(a);
	}
	
	public void addChild(WebNode parent, WebNode child) {
		parent.addChild(child);
	}
}