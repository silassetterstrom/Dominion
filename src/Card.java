public class Card {

	private String name;
	private int cost;
	
	public Card(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCost() {
		return this.cost;
	}
}
