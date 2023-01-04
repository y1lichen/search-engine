import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.WebNode;
import model.WebPage;
import model.WebTree;
import utils.GoogleQuery;

class SearchEngineApplication {
	public static ArrayList<WebTree> trees = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		String keyword = "data structure";
		callGoogle(keyword);
	}

	public static void callGoogle(String keyword) throws IOException {
		GoogleQuery googleQuery = new GoogleQuery(keyword);
		HashMap<String, String> baseUrls = googleQuery.query();
		for (String url : baseUrls.values()) {
			WebNode node = new WebNode(new WebPage(url));
			trees.add(WebTree.createTree(node, 0, 1));
			//
			for (int i = 0; i < trees.size(); i++) {
				System.out.println("tree" + i);
				WebTree tree = trees.get(i);
				tree.eularPrintTree(tree.root);
			}
		}
	}
}