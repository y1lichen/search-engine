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

	public static String fetchContent(String url) {
		String retVal = "";
		try {
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			// set HTTP header
			conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
			InputStream in = conn.getInputStream();

			InputStreamReader inReader = new InputStreamReader(in, "utf-8");
			BufferedReader bufReader = new BufferedReader(inReader);
			String line = null;

			while ((line = bufReader.readLine()) != null) {
				retVal += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	// public static String fetchContent(String urlString) {
	// String content = "";
	// URL url;
	// String line = null;
	// try {
	// url = new URL(urlString);
	// URLConnection connection = url.openConnection();
	// InputStream stream = connection.getInputStream();
	// BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	// while ((line = reader.readLine()) != null) {
	// content = content.concat(line + "\n");
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return content;
	// }
}
