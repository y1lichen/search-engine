package model;

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

	public static WebTree createTree(WebNode node, int depth) {
		WebTree tree = new WebTree(node);
		if(depth > 3) {
			return tree;
		}
		String resourcecode = node.page.content;
		Set<String> visitedLinks = new HashSet<>();
		Pattern linkPattern = Pattern.compile("href=\"(.+?)\"");
		Matcher linkMatcher = linkPattern.matcher(resourcecode);

		while (linkMatcher.find()) {
			String link = linkMatcher.group(1);
			if (!visitedLinks.contains(link)) {
				visitedLinks.add(link);
				WebNode child = new WebNode(new WebPage(link, "content"));
			    tree.addChild(node, child);
			    createTree(child, depth + 1);
			}
		}
		return tree;
	}
}