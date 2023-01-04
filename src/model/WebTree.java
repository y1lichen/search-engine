package model;


public class WebTree {
	public WebNode root;

	public WebTree(WebNode node) {
		this.root = node;
	}

	public void addChild(WebNode parent, WebNode child) {
		parent.addChild(child);
	}

	public void setPostOrderScore(WebNode startNode) {
		for (WebNode child : startNode.children) {
			child.setNodeScore();
		}
		startNode.setNodeScore();
	}

	private String repeat(String str, int repeat) {
		String retVal = "";
		for (int i = 0; i < repeat; i++) {
			retVal += str;
		}
		return retVal;
	}

	public void eularPrintTree(WebNode startNode) {

		System.out.print("(");
		System.out.print(startNode.score);
		System.out.print("|");
		System.out.print(startNode.page.url);

		// print child via pre-order
		for (WebNode child : startNode.children) {
			eularPrintTree(child);
		}
		System.out.print(")");

	}
}