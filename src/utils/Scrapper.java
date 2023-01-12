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

	public static String fetchContent(String url) throws IOException {
		String retVal = "";
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
		return retVal;
	}

}
