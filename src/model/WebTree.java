package model;

public class WebTree {
	public WebNode root;

	public WebTree(WebPage a) {
		this.root = new WebNode(a);
	}
}