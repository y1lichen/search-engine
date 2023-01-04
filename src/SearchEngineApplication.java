import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.WebNode;
import model.WebPage;
import model.WebTree;
import utils.GoogleQuery;

class SearchEngineApplication {
	public static ArrayList<WebTree> trees = new ArrayList<>();

	public static WebTree tree;

	public static void createTree(ArrayList<WebPage> pages) {
		for (WebPage page: pages) {
			WebNode child = new WebNode(page);
			tree.root.addChild(child);
			System.out.println(page.title + "|" + page.url);	
		}
		tree.setPostOrderScore(tree.root);
		// tree.eularPrintTree(tree.root);
	}

	public static void main(String[] args) throws IOException {
		String keyword = "data structure";
		callGoogle(keyword);
	}

	public static void callGoogle(String keyword) throws IOException {
		GoogleQuery googleQuery = new GoogleQuery(keyword);
		HashMap<String, String> baseUrls = googleQuery.query();
		ArrayList<WebPage> pages = new ArrayList<>();
		for (String webTitle: baseUrls.keySet()) {
			pages.add(new WebPage(webTitle, baseUrls.get(webTitle)));
		}
		tree = new WebTree(new WebNode(new WebPage("root", "root")));
		createTree(pages);	
		tree.setPostOrderScore(tree.root);
		tree.eularPrintTree(tree.root);
	}
}