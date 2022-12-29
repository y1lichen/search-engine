package utils;

import java.util.ArrayList;

import model.WebNode;
import model.WebPage;
import model.WebTree;

public class Filter {
	ArrayList<String> keywordList;
	WebPage rootPage = new WebPage("http://soslab.nccu.edu.tw/Courses.html", "soslab");
	WebTree tree = new WebTree(rootPage);
	
	public Filter(ArrayList<String> keywordList) {
		this.keywordList = keywordList;
	}

	public WebTree createTree(String resourcecode) {
		Boyermoore(resourcecode, "href");
		
	}
	public int Boyermoore(String T, String P) {
		int m = P.length() - 1;
		int n = T.length() - 1;
		int ans = 0;
		
		int badchar[] = new int [100000];
		for(int i = 0; i < m; i++) {
			badchar[(int)P.charAt(i)] = last(P.charAt(i), P);
		}
		
		int s = 0;
		
		while(s <= (n-m)) {
			int j = m-1;
			while(j>=0 && P.charAt(j) == T.charAt(s+j)) {
				j--;
			}
			if(j<0) {
				ans++;
				tree.root.addChild(new WebNode(new WebPage("url", "content")));
				s += (s+m<n)? m - badchar[T.charAt(s+m)]:1;
			}
			else {
				s += Math.max(1, j-badchar[T.charAt(s+j)]);
			}
		}
		return ans;
	}

	public int last(char c, String P) {
		int m = P.length() -1;
		int ans = -1;
		for(int i = m; i>=0; i--) {
			if(P.charAt(i) == c) {
				ans = i;
				break;
			}
		}
		return ans;
	}
	
	public int min(int a, int b) {
		if(a < b) {
			return a;
		}
		else if(b < a) {
			return b;
		}
		else {
			return a;
		}
	}
	
	public void postOrder(Node node) {
	    if (node == null) {
	        return;
	    }

	    postOrder(node.getLeft());
	    postOrder(node.getRight());
	    System.out.print(node.getData() + " ");
	}

}
