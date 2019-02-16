import java.util.ArrayList;
import java.io.*;

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
		ArrayList<Expansion> testRand = new ArrayList<Expansion>();
		Expansion testExp = new Expansion();
		File fileName = new File("src/BaseSet.txt");
		testExp.addByFile(fileName);
		Randomize gen = new Randomize(testRand);
		ArrayList<Card> testCards = gen.getCardSet();
		for(int i = 0; i < testCards.size(); i++) {
			System.out.println(testCards.get(i).getName());
		}
	}

}
