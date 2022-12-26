package model;

public class WebTree {
	WebPage rootPage;

	public WebTree(String url) {
		this.rootPage = new WebPage(url);
	}
}