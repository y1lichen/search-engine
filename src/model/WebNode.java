/*
 * Created by 陳奕利
 */
package model;

import java.util.Set;

public class WebNode {
    public WebNode parent;
    public Set<WebNode> children;
    public WebPage page;   
    public double score;
}
