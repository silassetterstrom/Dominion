import java.util.ArrayList;
import java.io.*;

public class Randomize {

	private ArrayList<Expansion> expansions = new ArrayList<Expansion>();
	private ArrayList<Card> allCards = new ArrayList<Card>();
	
	public Randomize(ArrayList<Expansion> exParam) {
		for(int i = 0; i < exParam.size(); i++) {
			this.expansions.add(exParam.get(i));
		}
		for(int i = 0; i < expansions.size(); i++) {
			for(int j = 0; j < expansions.get(i).getSize(); j++) {
				this.allCards.add(expansions.get(i).getCardByIndex(j));
			}
		}
	}
	
	public ArrayList<Card> getAllCards() {
		return this.allCards;
	}
	
	public ArrayList<Card> randGen() {
		ArrayList<Card> pool = new ArrayList<Card>();
		for(int i = 0; i < allCards.size(); i++) {
			pool.add(allCards.get(i));
		}
		ArrayList<Card> randomized = new ArrayList<Card>();
		for(int i = 0; i < 10; i++) {
			int rand = (int)Math.floor(Math.random() * (pool.size()));
			randomized.add(pool.get(rand));
			pool.remove(rand);
		}
		return randomized;
	}
	
	public static void main(String[] args) {
		ArrayList<Expansion> testRand = new ArrayList<Expansion>();
		Expansion testExp1 = new Expansion();
		Expansion testExp2 = new Expansion();
		Expansion testExp3 = new Expansion();
		File base = new File("src/BaseSet.txt");
		File prosperity = new File("src/Prosperity.txt");
		File dark = new File("src/DarkAges.txt");
		testExp1.addByFile(base);
		testExp2.addByFile(prosperity);
		testExp3.addByFile(dark);
		testRand.add(testExp1);
		testRand.add(testExp2);
		testRand.add(testExp3);
		Randomize gen = new Randomize(testRand);
		ArrayList<Card> testCards = gen.randGen();
		for(int i = 0; i < testCards.size(); i++) {
			testCards.get(i).print();
		}
	}
}
