package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebTree {
	public WebNode root;

	public WebTree(WebNode node) {
		this.root = node;
	}

	public void addChild(WebNode parent, WebNode child) {
		parent.addChild(child);
	}

	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException {
		for (WebNode child : startNode.children) {
			child.setNodeScore(keywords);
		}
		startNode.setNodeScore(keywords);
	}

	public static WebTree createTree(WebNode node, int depth, int totalNodes) {
		WebTree tree = new WebTree(node);
		if (depth > 1 || totalNodes >= 5) {
			return tree;
		}
		String resourcecode = node.page.content;
		if (resourcecode == null ||  resourcecode.isEmpty()) {
			return tree;
		}
		Set<String> visitedLinks = new HashSet<>();
		Pattern linkPattern = Pattern.compile("href=\"(.+?)\"");
		Matcher linkMatcher = linkPattern.matcher(resourcecode);
		int temp = totalNodes;

		while (linkMatcher.find()) {
			String link = linkMatcher.group(1);
			if (!visitedLinks.contains(link)) {
				temp += 1;
				visitedLinks.add(link);
				WebNode child = new WebNode(new WebPage(link));
				tree.addChild(node, child);
				createTree(child, depth + 1, temp);
			}
		}
		System.out.println("created");
		return tree;
	}

	private String repeat(String str, int repeat) {
		String retVal = "";
		for (int i = 0; i < repeat; i++) {
			retVal += str;
		}
		return retVal;
	}

	public void eularPrintTree(WebNode startNode) {
		int nodeDepth = startNode.getDepth();

		if (nodeDepth > 1)
			System.out.print("\n" + repeat("\t", nodeDepth - 1));

		System.out.print("(");
		System.out.print(startNode.score);
		System.out.print("|");
		System.out.print(startNode.page.url);
		System.out.println(")");

		// print child via pre-order
		for (WebNode child : startNode.children) {
			eularPrintTree(child);
		}

		System.out.print(")");

		if (startNode.isTheLastChild())
			System.out.print("\n" + repeat("\t", nodeDepth - 2));

	}
}