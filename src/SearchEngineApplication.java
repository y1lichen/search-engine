import java.io.IOException;

import utils.GoogleQuery;

class SearchEngineApplication {
	public static void main(String[] args) {
		String keyword = "data structure";
		callGoogle(keyword);
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