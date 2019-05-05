import java.util.ArrayList;
import java.util.Scanner;
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
		ArrayList<Expansion> kingdom = new ArrayList<Expansion>();
		Expansion testExp1 = new Expansion();
		Expansion testExp2 = new Expansion();
		Expansion testExp3 = new Expansion();
		
		File base = new File("./BaseSet.txt");
		File prosperity = new File("./Prosperity.txt");
		File dark = new File("./DarkAges.txt");
		
		testExp1.addByFile(base);
		testExp1.setName("Base Set");
		testExp2.addByFile(prosperity);
		testExp2.setName("Prosperity");
		testExp3.addByFile(dark);
		testExp3.setName("Dark Ages");
		kingdom.add(testExp1);
		kingdom.add(testExp2);
		
		kingdom.add(testExp3);
		System.out.println("Welcome to the Dominion Randomizer. Here are the loaded expansions:");
		for(int i = 0; i < kingdom.size(); i++) {
			System.out.println((i+1) + ") " + kingdom.get(i).getName());
		}
		System.out.println("Are there any you'd like to exclude? Enter the number" + 
				" of the expansion to remove it or 0 to continue.");
		
		Scanner scan = new Scanner(System.in);
		try {
			int remove = scan.nextInt();
			while(remove != 0) {
				String removed = kingdom.get(remove -1).getName();
				kingdom.remove(remove - 1);
				System.out.println("Removed expansion " + removed);
				System.out.println("Would you like to remove another? If so, enter its new number."
						+ " If not, enter 0.");
				for(int i = 0; i < kingdom.size(); i++) {
					System.out.println((i+1) + ") " + kingdom.get(i).getName());
				}
				remove = scan.nextInt();
			}
		} catch (Exception e) {
			System.out.println("Error: Please enter the number of one of the expansions,"
					+ " or 0.");
		}

		System.out.print("Randomizing with expansions: ");
		for(int i = 0; i < kingdom.size(); i++) {
			System.out.print(kingdom.get(i).getName() + ". ");
		} 
		
		System.out.println("\n");
		System.out.println("Here is your randomized set: ");
		Randomize gen = new Randomize(kingdom);
		ArrayList<Card> testCards = gen.randGen();
		for(int i = 0; i < testCards.size(); i++) {
			testCards.get(i).print();
		}

		scan.close();
	}
}
