/*
 * Created by XinWen
 */
package model;


public class Keyword {
	private String name;
	private int weight;

	public Keyword(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}


}
