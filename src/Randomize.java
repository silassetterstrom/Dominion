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
	
	public static ArrayList<Card> replaceCard(ArrayList<Expansion> kingdom, ArrayList<Card> cardSet, int index) {
		Randomize randObject = new Randomize(kingdom);
		ArrayList<Card> possibleCards = randObject.getAllCards();
		
		int acceptFlag = 0;
		while(acceptFlag == 0) {
			int rand = (int)Math.floor(Math.random() * (possibleCards.size()));
			Card card = possibleCards.get(rand);
			int match = 0;
			for(int i = 0; i < cardSet.size(); i++) {
				if(card.getName().equals(cardSet.get(i).getName())) {
					match++;
				}
			}
			if(match == 0) {
				cardSet.remove(index);
				cardSet.add(card);
				acceptFlag++;
			}
		}
		
		return cardSet;
	}
	
	public static ArrayList<Card> sortCards(ArrayList<Card> cardSet) {
		ArrayList<Card> alphabetized = new ArrayList<Card>();
		
		while(!cardSet.isEmpty()) {
			Card first = cardSet.get(0);
			int removeIndex = 0;
			for(int i = 1; i < cardSet.size(); i++) {
				if(cardSet.get(i).getName().compareTo(first.getName()) < 0) {
					removeIndex = i;
					first = cardSet.get(i);
				}
			}
			alphabetized.add(first);
			cardSet.remove(removeIndex);
		}
		
		ArrayList<Card> sortedProperly = new ArrayList<Card>();
		
		while(!alphabetized.isEmpty()) {
			Card first = alphabetized.get(0);
			int removeIndex = 0;
			for(int i = 1; i < alphabetized.size(); i++) {
				if(alphabetized.get(i).getCost() < first.getCost()) {
					first = alphabetized.get(i);
					removeIndex = i;
				}
			}
			sortedProperly.add(first);
			alphabetized.remove(removeIndex);
		}
		
		return sortedProperly;
	}
	
	public static void userLoop(ArrayList<Expansion> kingdom) {
		System.out.println("Welcome to the Dominion Randomizer. Here are the loaded expansions:");
		for(int i = 0; i < kingdom.size(); i++) {
			System.out.println((i+1) + ") " + kingdom.get(i).getName());
		}
		System.out.println("Are there any you'd like to exclude? Enter the number" + 
				" of the expansion to remove it or 0 to continue.");
		
		int exitFlag = 1;
		Scanner scan = new Scanner(System.in);
		
		while(exitFlag == 1) {
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

			System.out.print("Randomizing with expansions: ");
			for(int i = 0; i < kingdom.size(); i++) {
				System.out.print(kingdom.get(i).getName() + ". ");
			} 
		
			System.out.println("\n");
			System.out.println("Here is your randomized set: ");
			Randomize gen = new Randomize(kingdom);
			ArrayList<Card> randomizedCards = gen.randGen();
			randomizedCards = sortCards(randomizedCards);
			for(int i = 0; i < randomizedCards.size(); i++) {
				System.out.print((i+1) + ") ");
				randomizedCards.get(i).print();
			}
			
			System.out.println("Would you like to replace a card? Enter the number of the card if "
					+ "yes, otherwise enter 0.");
			int cardNumber = scan.nextInt();
			while(cardNumber != 0) {
				randomizedCards = replaceCard(kingdom, randomizedCards, cardNumber-1);
				System.out.println("Card replaced. Here is your new set: ");
				randomizedCards = sortCards(randomizedCards);
				for(int i = 0; i < randomizedCards.size(); i++) {
					System.out.print((i+1) + ") ");
					randomizedCards.get(i).print();
				}
				System.out.println("Would you like to replace another card? Enter the number of "
						+ "the card if yes, otherwise enter 0.");
				cardNumber = scan.nextInt();
			}
			
			System.out.println("Would you like to randomize again? Enter '1' to randomize again"
					+ " or '0' to exit.");
			exitFlag = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Error: Incorrect input.");
			scan.close();
			return;
			}
		}
		scan.close();
		return;
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
		
		userLoop(kingdom);
		System.out.println("Thank you for using the Dominion Randomizer! The program will now exit.");
	}
}
