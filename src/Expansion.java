import java.util.ArrayList;

public class Expansion {

	private ArrayList<Card> cards;
	
	public Expansion(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public Expansion() {
		this.cards = new ArrayList<Card>();
	}
	
	public ArrayList<Card> getCards() {
		return this.cards;
	}
	
	public Card getCardByIndex(int index) {
		return cards.get(index);
	}
	
	public int getSize() {
		return cards.size();
	}
	
	public void addCard(Card c) {
		cards.add(c);
	}
	
	public void removeCard(String name) {
		int location = -1;
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getName().equals(name)) {
				location = i;
			}
		}
		
		if(location != -1) {
			cards.remove(location);
		}
	}
	
	public void print() {
		for(int i = 0; i < cards.size(); i++) {
			System.out.println(cards.get(i).getName() + " " + cards.get(i).getCost());
		}
	}

}
