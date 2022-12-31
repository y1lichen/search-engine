package model;

public class WebPage {
    public String url;
    public String content;

    public WebPage(String url) {
        this.url = url;
    }

    public WebPage(String url, String content) {
        this.url = url;
        this.content = content;
    }
}
