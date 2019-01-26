import java.util.ArrayList;

public class Expansion {

	private ArrayList<Card> cards;
	
	public Expansion(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public ArrayList<Card> getCards() {
		return this.cards;
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
	
	public static void main(String[] args) {
		ArrayList<Card> exCards = new ArrayList<Card>();
		exCards.add(new Card("Village", 3));
		exCards.add(new Card("Market", 5));
		exCards.add(new Card("Smithy", 4));
		Expansion e = new Expansion(exCards);
		e.addCard(new Card("Festival", 5));
		e.print();
	}

}
