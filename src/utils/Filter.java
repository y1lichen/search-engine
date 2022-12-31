package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.WebNode;
import model.WebPage;
import model.WebTree;

public class Filter {
	int time = 1;
	ArrayList<String> keywordList;
	WebPage rootPage = new WebPage("http://soslab.nccu.edu.tw/Courses.html", "soslab");
	WebTree tree = new WebTree(rootPage);

	public Filter(ArrayList<String> keywordList) {
		this.keywordList = keywordList;
	}

	public WebTree createTree(WebNode node, int depth) {
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
