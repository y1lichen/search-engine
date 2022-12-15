import java.io.IOException;

import utils.GoogleQuery;

class SearchEngineApplication {
	public static void main(String[] args) {
		String keyword = "data structure";
		keyword = reformatKeyword(keyword);
		callGoogle(keyword);
	}

	public static String reformatKeyword(String keyword) {
		String result = keyword.replace(" ", "%");
		return result;
	}
	
	public static void callGoogle(String keyword) {
		GoogleQuery googleQuery = new GoogleQuery(keyword);
		try {
			googleQuery.query();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}