package utils;

import java.util.ArrayList;

import model.Keyword;

public class Filter {
	int time = 1;
	ArrayList<Keyword> keywordsList;

	public Filter() {
		this.keywordsList = new ArrayList<>();
		initKeywordsList();
	}

	public void initKeywordsList() {
		keywordsList.add(new Keyword("publisher", 10));
		keywordsList.add(new Keyword("title", 10));
		keywordsList.add(new Keyword("book name", 10));
		keywordsList.add(new Keyword("author", 10));
		keywordsList.add(new Keyword("isbn", 10));
		keywordsList.add(new Keyword("series", 10));
		keywordsList.add(new Keyword("edition", 10));
		keywordsList.add(new Keyword("epub", 10));
		keywordsList.add(new Keyword("pdf", 10));
		keywordsList.add(new Keyword("download", 10));
		keywordsList.add(new Keyword("mirror", 8));
		keywordsList.add(new Keyword("mirrors", 8));
		keywordsList.add(new Keyword("library", 8));
		keywordsList.add(new Keyword("extension", 7));
		keywordsList.add(new Keyword("page", 6));
		keywordsList.add(new Keyword("pages", 6));
		keywordsList.add(new Keyword("book", 5));
		keywordsList.add(new Keyword("books", 5));
		keywordsList.add(new Keyword("language", 3));
	}

}
