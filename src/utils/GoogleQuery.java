/*
 * This file is for calling google-search
 * Created by 陳奕利
 */

package utils;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery {
	public String searchKeyword;
	public String url;
	public String content;

	// 把輸入的keyword改成get method的參數樣子
	public static String reformatKeyword(String keyword) {
		String result = keyword.replace(" ", "%");
		return result;
	}

	public GoogleQuery(String searchKeyword) {
		String temp = reformatKeyword(searchKeyword);
		String num = "30";
		this.searchKeyword = temp;
		this.url = "http://www.google.com/search?q=" + temp + "&oe=utf8&num=" + num;
	}

	public HashMap<String, String> query() throws IOException {
		if (content == null) {
			content = Scrapper.fetchContent(this.url);
		}

		HashMap<String, String> retVal = new HashMap<String, String>();

		// using Jsoup analyze html string
		Document doc = Jsoup.parse(content);

		// select particular element(tag) which you want

		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");

		for (Element li : lis) {
			try {
				// String citeUrl = li.select("a").get(0).attr("href");
				// 移掉奇怪的前綴和後綴
				String citeUrl = li.select("a").get(0).attr("href");
				int startIndexOfUrl = citeUrl.indexOf("http");
				int endIndexOfUrl = citeUrl.indexOf("&sa=U&ved=2ah");
				citeUrl = citeUrl.substring(startIndexOfUrl, endIndexOfUrl);
				//
				String title = li.select("a").get(0).select(".vvjwJb").text();

				if (title.equals("")) {
					continue;
				}

				// put title and pair into HashMap
				retVal.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) {
				// e.printStackTrace();
			}
		}
		return retVal;
	}
}