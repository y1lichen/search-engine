package model;

import java.util.ArrayList;

import utils.Filter;
import utils.Scrapper;

public class WebPage {
    public String url;
    public String content;
    public double score;
    public Filter filter;

    public WebPage(String url) {
        try {
            this.url = url;
            this.content = Scrapper.fetchContent(url);
            this.score = 0;
            this.filter = new Filter(this.content);
        } catch (Exception e) {
        }
    }

    public void setScore(ArrayList<Keyword> keywords) {
        score = 0;
        for (Keyword k : keywords) {
            score += filter.countKeyword(k.name) * k.weight;
        }
    }
}
