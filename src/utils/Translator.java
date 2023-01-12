package utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Translator {

    public static String translate(String text) throws IOException {
        String urlStr = "https://translate.google.com/?sl=auto&tl=en&text=" + text + "&op=translate";
        String content = Scrapper.fetchContent(urlStr);
        Document doc = Jsoup.parse(content);
        Elements elements = doc.select("div.W5CUef");
        String result = elements.first().text();
        return result;
    }

}