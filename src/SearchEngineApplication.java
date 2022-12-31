import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.WebNode;
import model.WebPage;
import model.WebTree;
import utils.GoogleQuery;

class SearchEngineApplication {
	public static ArrayList<WebTree> trees = new ArrayList<>();
	public static void main(String[] args) {
		String keyword = "data structure";
		callGoogle(keyword);
	}

	public static void callGoogle(String keyword) {
		GoogleQuery googleQuery = new GoogleQuery(keyword);
		try {
			HashMap<String, String> baseUrls = googleQuery.query();
			for (String url: baseUrls.values()) {
				WebNode node = new WebNode(new WebPage(url));
				trees.add(WebTree.createTree(node, 0));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}