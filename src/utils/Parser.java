package utils;

import java.io.IOException;
import java.util.Stack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Keyword;

public class Parser {
	String content;
	Document doc;

	public Parser(String content) {
		this.content = content;
		this.doc = Jsoup.parse(content);
	}

	public int getExtraPoint() {
		int total = 0;
		total += getExtraPointOfSpecificTag("h1", 3);
		total += getExtraPointOfSpecificTag("h2", 2);
		total += getExtraPointOfSpecificTag("h3", 1);
		return total;
	}

	public int getCountOfVedio() {
		Elements elements = doc.select("vedio");
		return elements.size();	
	}

	private int getExtraPointOfSpecificTag(String tagName, int weight) {
		int score = 0;
		Elements elements = doc.select(tagName);
		for (Element element : elements) {
			for (Keyword k: Filter.keywordsList) {
				if (element.text().equals(k.getName())) {
					score += (weight * k.getWeight());
				}
			}
		}
		return score;
	}

	public void match() throws IOException {
		/* Create a stack to store the tag */
		Stack<String> tagStack = new Stack<>();

		int indexOfOpen = 0;

		while ((indexOfOpen = content.indexOf("<", indexOfOpen)) != -1) {
			// Get full tag. e.g. "<div id="abcdefg">","</a>","</div>"...
			int indexOfClose = content.indexOf(">", indexOfOpen);// 1.**here
			String fullTag = content.substring(indexOfOpen, indexOfClose + 1);// 2.**here

			// Extract tag name from fullTag. e.g. "div","/a","/div"...
			String tagName = null;
			int indexOfSpace = -1;

			if ((indexOfSpace = fullTag.indexOf(" ")) == -1) {
				// If there is no space in the fullTag (e.g. "<li>","</a>","</div>")
				// then the tag name will be the words between first and last character.
				// For example, if fullTag is "<li>" then the tagName will be "li";
				// For example, if fullTag is "</li>" then the tagName will be "/li" (Note that
				// we preserve the slash'/' so we can tell that this is a close tag in the
				// future)
				tagName = fullTag.substring(1, fullTag.length() - 1);
			} else {
				// If there are some space in the fullTag (e.g "<li id='theID'>","<a
				// href='http://www.google.com.tw/'>")
				// then the tag name will be the words between first character and the first
				// space.
				// For example, if fullTag is "<li id='theID'>" the tagName will be "li";
				// For example, if fullTag is "<a href='http://www.google.com.tw/'>" the tagName
				// will be "a"
				tagName = fullTag.substring(1, indexOfSpace);
			}

			if (tagName.equals("meta") || tagName.equals("!doctype")) {
				indexOfOpen = indexOfClose;
				continue;
			}

			// Determine whether this tag is an open tag (e.g. "<div>") or close tag (e.g.
			// "</div>")
			int indexOfSlash = -1;
			if ((indexOfSlash = tagName.indexOf("/")) == -1) {
				// This is an open tag, so simply push it into stack
				tagStack.push(tagName);
			} else {
				// This is an close tag, so we should compare it to the topmost tag in the stack
				// Remove the slash '/' (the first character of tagName), so that we can compare
				// it with the open tag name in stack
				tagName = tagName.substring(indexOfSlash + 1);

				// But...what if there is no topmost tag in the stack
				if (tagStack.isEmpty()) {
					// stack is empty, this tag is an invalid tag
					System.out.println("False");
					return;
				}

				// Compare to topmost tag in the stack
				String topMostTag = tagStack.peek();

				if (topMostTag.equals(tagName)) {
					// This tagName is equal to the tag name in the stack!
					// Pop out the top tag in the stack.
					tagStack.pop();

				} else {
					// This tagName is not equal to the tag name in the stack!
					// So we found that this tag is an invalid tag
					System.out.println("False " + getStackString(tagStack));
					return;
				}
			}
			// Move the searching start point, so that we can search the next tag in
			// htmlContent
			indexOfOpen = indexOfClose;
		}

		// After search and compare all the tag in the htmlContent,
		// We should also check whether the stack is empty or not.
		if (!tagStack.isEmpty()) {
			// The stack is not empty, this mean the tags is invalid
			System.out.println("False " + getStackString(tagStack));
		} else {
			// The stack is empty, all tags successfully matched.
			System.out.println("True");
		}
	}

	private String getStackString(Stack<String> stack) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stack.size(); i++) {
			if (i > 0) {
				sb.append(" ");
			}
			sb.append(stack.get(i));
		}
		return sb.toString();
	}
}