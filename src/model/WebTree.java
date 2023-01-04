package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.Validator;

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

	public void createTree(WebNode node, int depth) {
		if (depth > 1) {
			return;
		}
		String resourcecode = node.page.content;
		if (resourcecode == null || resourcecode.isEmpty()) {
			return;
		}
		Document doc = Jsoup.parse(resourcecode);
		Elements a = doc.select("a");
		int countOfATags = 0;
		for (Element atag : a) {
			String url = atag.attr("href");
			if (Validator.isValidURL(url)) {
				countOfATags += 1;
				WebPage newPage = new WebPage("subpage", url);
				WebNode newNode = new WebNode(newPage);
				node.addChild(newNode);
			}
			if (countOfATags >= 4) {
				return;
			}
		}
		// Set<String> visitedLinks = new HashSet<>();
		// Pattern linkPattern = Pattern.compile("href=\"(.+?)\"");
		// Matcher linkMatcher = linkPattern.matcher(resourcecode);

		// while (linkMatcher.find()) {
		// String link = linkMatcher.group(1);
		// if (!visitedLinks.contains(link)) {
		// visitedLinks.add(link);
		// WebNode child = new WebNode(new WebPage("subpage", link));
		// tree.addChild(node, child);
		// createTree(child, depth + 1);
		// }
		// }
		// System.out.println("created");
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