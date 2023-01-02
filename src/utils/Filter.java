package utils;

import java.util.ArrayList;

public class Filter {
	int time = 1;
	ArrayList<String> keywordList;

	public Filter(ArrayList<String> keywordList) {
		this.keywordList = keywordList;
	}

<<<<<<< Updated upstream
=======
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
		System.out.print(tree);
		return tree;
	}
>>>>>>> Stashed changes
}
