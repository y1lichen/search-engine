package utils;

import java.util.ArrayList;

import model.Keyword;
import model.WebNode;
import model.WebTree;

public class Filter {
	public static ArrayList<WebTree> trees = new ArrayList<>();

	ArrayList<Keyword> keywordsList;
	String content;

	public Filter(String content) {
		this.content = content;
		this.keywordsList = new ArrayList<>();
		initKeywordsList();
	}

	public void initKeywordsList() {
		keywordsList.add(new Keyword("$", 2));
		keywordsList.add(new Keyword("cart", 2));
		keywordsList.add(new Keyword("購物車", 2));
		keywordsList.add(new Keyword("售價", 2));
		keywordsList.add(new Keyword("price", 2));
		keywordsList.add(new Keyword("ships", 8));
		keywordsList.add(new Keyword("ship", 8));
		keywordsList.add(new Keyword("delivery", 8));
		keywordsList.add(new Keyword("宅配", 8));
		keywordsList.add(new Keyword("cart", 2));
		keywordsList.add(new Keyword("購物車", 2));
		keywordsList.add(new Keyword("售價", 2));
		keywordsList.add(new Keyword("price", 2));
		keywordsList.add(new Keyword("ships", 8));
		keywordsList.add(new Keyword("ship", 8));
		keywordsList.add(new Keyword("delivery", 8));
		keywordsList.add(new Keyword("宅配", 8));
		keywordsList.add(new Keyword("publisher", 10));
		keywordsList.add(new Keyword("出版商", 10));
		keywordsList.add(new Keyword("出版社", 10));
		keywordsList.add(new Keyword("title", 10));
		keywordsList.add(new Keyword("書名", 10));
		keywordsList.add(new Keyword("book name", 10));
		keywordsList.add(new Keyword("author", 10));
		keywordsList.add(new Keyword("作者", 10));
		keywordsList.add(new Keyword("編者", 10));
		keywordsList.add(new Keyword("isbn", 10));
		keywordsList.add(new Keyword("series", 10));
		keywordsList.add(new Keyword("edition", 10));
		keywordsList.add(new Keyword("電子書", 10));
		keywordsList.add(new Keyword("epub", 10));
		keywordsList.add(new Keyword("pdf", 10));
		keywordsList.add(new Keyword("下載", 10));
		keywordsList.add(new Keyword("download", 10));
		keywordsList.add(new Keyword("鏡像", 8));
		keywordsList.add(new Keyword("mirror", 8));
		keywordsList.add(new Keyword("mirrors", 8));
		keywordsList.add(new Keyword("library", 8));
		keywordsList.add(new Keyword("extension", 3));
		keywordsList.add(new Keyword("頁數", 10));
		keywordsList.add(new Keyword("page", 6));
		keywordsList.add(new Keyword("pages", 6));
		keywordsList.add(new Keyword("book", 5));
		keywordsList.add(new Keyword("books", 5));
		keywordsList.add(new Keyword("語言", 7));
		keywordsList.add(new Keyword("language", 7));
	}

	public int getScore() {
		int score = 0;
		for (Keyword k : keywordsList) {
			score += countKeyword(k.getName()) * k.getWeight();
		}
		return score;
	}

	public int countKeyword(String keyword) {
		// To do a case-insensitive search, we turn the whole content and keyword into
		// upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();

		int retVal = 0;
		int fromIdx = 0;
		int found = -1;

		while ((found = content.indexOf(keyword, fromIdx)) != -1) {
			retVal++;
			fromIdx = found + keyword.length();
		}

		return retVal;
	}

	private static void swap(int aIndex, int bIndex) {
		WebTree temp = trees.get(aIndex);
		trees.set(aIndex, trees.get(bIndex));
		trees.set(bIndex, temp);
	}

	public static void quickSort(int left, int right) {
		if (left < right) {
			int q = partition(left, right);
			quickSort(left, q - 1);
			quickSort(q + 1, right);
		}
	}

	private static int partition(int left, int right) {
		int i = left - 1;
		for (int j = left; j < right; j++) {
			if (trees.get(j).root.score >= trees.get(right).root.score) {
				i++;
				swap(i, j);
			}
		}
		swap(i + 1, right);
		return i + 1;
	}

}
