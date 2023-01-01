/*
 * This file is for web scrapping
 * Created by 陳奕利
 */

package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Scrapper {

	public Scrapper() {
	}

	public static String fetchContent(String urlString) {
		String content = "";
		URL url;
		String line = null;
		try {
			url = new URL(urlString);
			URLConnection connection = url.openConnection();
			InputStream stream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			while ((line = reader.readLine()) != null) {
				content = content.concat(line + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
