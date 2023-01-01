package model;


import utils.Scrapper;

public class WebPage {
    public String url;
    public String content;

    public WebPage(String url) {
        this.url = url;
        this.content = Scrapper.fetchContent(url);
    }
}
