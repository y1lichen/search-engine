import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import model.WebNode;
import model.WebPage;
import model.WebTree;
import utils.Filter;
import utils.GoogleQuery;

class SearchEngineApplication {

	public static void main(String[] args) throws IOException {
		String keyword = "data structure" + "book";
		callGoogle(keyword);
		for (WebTree tree: Filter.trees) {
			System.out.println(tree.root.score + "|" + tree.root.page.url);
		}
	}

	public static HashMap<String, String> callGoogle(String keyword) throws IOException {
		GoogleQuery googleQuery = new GoogleQuery(keyword);
		HashMap<String, String> baseUrls = googleQuery.query();
		for (String webTitle: baseUrls.keySet()) {
			WebNode root = new WebNode(new WebPage(webTitle, baseUrls.get(webTitle)));
			WebTree tree = new WebTree(root);
			tree.createTree(root, 0);
			tree.setPostOrderScore(root);
			Filter.trees.add(tree);
		}
		Filter.quickSort(0, Filter.trees.size()-1);
		System.out.println(Filter.trees);
		HashMap<String, String> sortedHasMap = new LinkedHashMap<>();
		return sortedHasMap;
	}
}