/*
 * This file is for web scrapping
 * Created by 陳奕利
 */

package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Scrapper {
	
	public Scrapper() {
	}

	public static String fetchContent(String urlString) throws IOException {
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		InputStream stream = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String content = "";
		String line = null;
		while ((line = reader.readLine()) != null) {
			content = content.concat(line + "\n");
		}
		return content;
	}
}
