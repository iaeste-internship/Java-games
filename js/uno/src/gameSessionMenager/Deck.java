package gameSessionMenager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * Deck object is used to create hands, draw pile as well as open discard pile.
 * 
 * @author Aykut Bir
 * @since 09/05/2024
 * 
 */

public class Deck {
	
	private ArrayList <Card> cards;
	private Map<String, Card> cardMap; 
	
	/**
	 * Initializes two UNO decks, one of which contains every card (total 108 cards)
	 * which is used while initializing default games
	 * 
	 * The other deck hold every card type as Map object, pairing card name with card object.
	 * This deck is used while setting a game load.
	 */
	public Deck() {
		
		cards = new ArrayList<>();
		cardMap = new HashMap<>();
		initializeDeck();
		shuffleDeck();
		
	}
	
	/**
	 * Initializes UNO decks.
	 */
	
	private void initializeDeck() {
		
		String[] wildValues = new String[] {"WildColor", "WildDraw4"};
		String[] numberValues = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] specialValues = new String[]{"Skip", "Reverse", "Draw2"};
		String[] colors = new String[]{"Red", "Green", "Blue", "Yellow"};
		
		for (String color : colors) {
			for (String numberValue : numberValues) {
				if (numberValue == "0") {
					ImageIcon image = new ImageIcon("src/img/"+ "uno" + color + numberValue + ".png");
					this.cards.add(new ColorCard(numberValue, color, image,  Integer.parseInt(numberValue)));
					this.cardMap.put(color + numberValue,new ColorCard(numberValue, color, image,  Integer.parseInt(numberValue)));
				} else {
					ImageIcon image = new ImageIcon("src/img/"+ "uno" + color + numberValue + ".png");
					this.cards.add(new ColorCard(numberValue, color, image,  Integer.parseInt(numberValue)));
					this.cards.add(new ColorCard(numberValue, color, image, Integer.parseInt(numberValue)));
					this.cardMap.put(color + numberValue,new ColorCard(numberValue, color, image,  Integer.parseInt(numberValue)));
				}
			}
			for (String specialValue : specialValues) {
				ImageIcon image = new ImageIcon("src/img/"+ "uno" + color + specialValue + ".png");
				this.cards.add(new ColorCard(specialValue, color, image, 20));
				this.cards.add(new ColorCard(specialValue, color, image, 20));
				this.cardMap.put(color + specialValue, new ColorCard(specialValue, color, image, 20));
			}
		}
		
		for (String wildValue : wildValues) {
			ImageIcon image = new ImageIcon("src/img/"+ "uno" + wildValue + ".png");
			this.cards.add(new WildCard(wildValue, image));
			this.cards.add(new WildCard(wildValue, image));
			this.cards.add(new WildCard(wildValue, image));
			this.cards.add(new WildCard(wildValue, image));
			this.cardMap.put(wildValue,new WildCard(wildValue, image));
		}
	}
	
	/**
	 * Shuffles the deck of 108 cards using shuffle method from collections
	 */
	
	public void shuffleDeck() {
		Collections.shuffle(cards);
	}
	
	/**
	 * @param card :Card, which will be copied
	 * @return copt :Card, copy of given card
	 */
	public static Card copyCard(Card card) {
		Card copy = null;
		if (card instanceof ColorCard) {
			copy = new ColorCard(card.getValue(), ((ColorCard) card).getColor(), card.getIcon(), card.getPoint());
		} else {
			copy = new WildCard(card.getValue(), card.getIcon());
		}
		return copy;
	}
	
	/**
	 * @param player :Player, which will be set with 7 cards in their hand.
	 */
	public void distrubuteCards(Player player) {
		ArrayList<Card> playerHand = new ArrayList<>();
		for (int i = 1 ; i<= 7 ; i++) {
			playerHand.add(cards.remove(cards.size() - 1));
			shuffleDeck();
		player.setHand(playerHand);
		}
	}
	
	/**
	 * 
	 * @return ArrayList<Card>, remaining cards after distrubution of cards will be opened as draw pile
	 */
	public ArrayList<Card> initializeDrawPile() {
		return this.cards;
	}

	/**
	 * getter for Map deck object 
	 */
	public Map<String, Card> getCardMap() {
		return cardMap;
	}
	

}
