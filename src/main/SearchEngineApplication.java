package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import model.WebNode;
import model.WebPage;
import model.WebTree;
import utils.Filter;
import utils.GoogleQuery;
import utils.Translator;

public class SearchEngineApplication {

	public static void main(String[] args) throws IOException {
		String keyword = "資料結構";
		// System.out.println(getRelatedKeyword(keyword));
		System.out.println(Translator.translate(keyword));
//		for (WebTree tree: Filter.trees) {
//			System.out.println(tree.root.score + "|" + tree.root.page.url);
//		}
	}

	public static List<String> getRelatedKeyword(String keyword) throws IOException {
		keyword += " book";
		GoogleQuery googleQuery = new GoogleQuery(keyword);
		return googleQuery.getRelatedKeyword();
	}

	public static HashMap<String, String> callGoogle(String keyword) throws IOException {
		keyword += " book";
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
		HashMap<String, String> sortedHashMap = new LinkedHashMap<>();
		for (WebTree tree: Filter.trees) {
			sortedHashMap.put(tree.root.page.title, tree.root.page.url);
		}
		return sortedHashMap;
	}
}