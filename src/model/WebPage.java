package model;

import utils.Filter;
import utils.Scrapper;

public class WebPage {
    public String title;
    public String url;
    public String content;
    public double score;
    public Filter filter;

    public WebPage(String title, String url) {
        try {
            this.title = title;
            this.url = url;
            this.content = Scrapper.fetchContent(url);
            this.score = 0;
            this.filter = new Filter(this.content);
        } catch (Exception e) {
        }
    }

    public void setScore() {
        if (filter != null) {
            this.score = filter.getScore();
        }
    }
}
