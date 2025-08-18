package gameSessionMenager;

import java.util.ArrayList;

/**
 * Player class that holds a list of cards.
 * 
 * @author Aykut Bir
 * @since 08/05/2024
 * 
 */

public abstract class Player {
	
	ArrayList<Card> hand;
	String name;
	
	/**
	 * Initializes players name field, as well as creates an empty hand
	 * Hand of players utilizes ArrayLists
	 * 
	 * @param name :String
	 */
	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<>();
	}
	
	/**
	 * @return ArrayList<Card> :Players hand
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	/**
	 * Sets players hand.
	 * 
	 * @param hand :ArrayList<Card>
	 * 
	 * @see GameLoader, Deck
	 */
	
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	/**
	 * 
	 * @return name :String, returns players name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Iterates through Player's hand and compares each card to the parameter card.
	 * Returns each card from the players hand that can be played "onto" the parameter card.
	 * 
	 * @param lastCard :Card, parameter card that will be played on
	 * @return ArrayList<Card>, list of playable cards
	 */
	public ArrayList<Card> findDiscardableCards(Card lastCard) {
		ArrayList<Card> discardableCards = new ArrayList<>();
		if (lastCard != null){
			for (Card card : hand) {
				if (card.isDiscardable(lastCard)) {
					discardableCards.add(card);
				}
			}
		} else {
			discardableCards = this.hand;
		}
		
		return discardableCards;
	}
	
	public abstract void drawCard(ArrayList<Card> drawPile);
	
	public abstract boolean hasDiscardableCard(Card lastCard);
	
}
