import java.util.ArrayList;

public class Randomize {

	private ArrayList<Expansion> expansions;
	private ArrayList<Card> availableCards;
	
	public Randomize(ArrayList<Expansion> expansions) {
		this.expansions = expansions;
	}
	
	public ArrayList<Card> getCardSet() {
		ArrayList<Card> returnCards = new ArrayList<Card>();
		for(int i = 0; i < expansions.size(); i++) {
			for(int j = 0; j < expansions.get(i).getSize(); j++) {
				returnCards.add(expansions.get(i).getCardByIndex(j));
			}
		}
		return returnCards;
	}
	
	public static void main(String[] args) {
		Expansion e = new Expansion();
		e.addCard(new Card("Village", 5));
		e.addCard(new Card("Market", 5));
		e.addCard(new Card("Festival", 5));
		e.addCard(new Card("Cellar", 2));
		e.addCard(new Card("Chapel", 2));
		e.addCard(new Card("Moat", 2));
		e.addCard(new Card("Workshop", 3));
		e.addCard(new Card("Bureaucrat", 4));
		e.addCard(new Card("Militia", 4));
		e.addCard(new Card("Moneylender", 4));
		e.addCard(new Card("Remodel", 4));
		e.addCard(new Card("Smithy", 4));
		e.addCard(new Card("Throne Room", 4));
		e.addCard(new Card("Council Room", 5));
		e.addCard(new Card("Laboratory", 5));
		e.addCard(new Card("Mine", 5));
		e.addCard(new Card("Library", 5));
		e.addCard(new Card("Witch", 5));
		e.addCard(new Card("Spy", 4));
		e.addCard(new Card("Thief", 4));
		e.addCard(new Card("Woodcutter", 3));
//		e.print();
		ArrayList<Expansion> test = new ArrayList<Expansion>();
		test.add(e);
		Randomize gen = new Randomize(test);
		ArrayList<Card> testCards = gen.getCardSet();
		for(int i = 0; i < testCards.size(); i++) {
			System.out.println(testCards.get(i).getName());
		}
	}

}
