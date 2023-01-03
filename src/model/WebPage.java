package model;

import java.io.IOException;
import java.util.ArrayList;

import utils.Filter;
import utils.Scrapper;

public class WebPage {
    public String url;
    public String content;
    public double score;
    public Filter filter;

    public WebPage(String url) throws IOException {
        this.url = url;
        this.content = Scrapper.fetchContent(url);
        this.score = 0;
        this.filter  = new Filter(this.content);
    }

    public void setScore(ArrayList<Keyword> keywords) {
        score = 0;
        for (Keyword k : keywords) {
            score += filter.countKeyword(k.name) * k.weight;
        }
    }
}
