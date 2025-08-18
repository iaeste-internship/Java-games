package gameSessionMenager;

import java.util.ArrayList;

/**
 * HumanPlayer class that acts as container for human player decisions.
 * 
 * @author Aykut Bir
 * @since 09/05/2024
 * 
 */


public class HumanPlayer extends Player{
	
	/**
	 * Constructor uses superclass Player constructor. 
	 * @param username :String, players username to be used to set as name field of given object
	 */
	HumanPlayer(String username){
		super(username);
	}
	
	/**
	 * Iterates through HumanPlayer's hand and compares each card to the parameter card.
	 * Returns each card from the players hand that can be played "onto" the parameter card.
	 * 
	 * @param lastCard :Card, parameter card that will be played on
	 * @return ArrayList<Card>, list of playable cards
	 */
	public ArrayList<Card> getDiscardableCards(Card lastCard) {
		
		ArrayList<Card> discardableCards = new ArrayList<>();
		for (Card card : this.getHand()) {
			if (card.isDiscardable(lastCard)) {
				discardableCards.add(card);
			}
		}
		
		return discardableCards;
	}
	
	/**
	 * Adds a new card from draw pile, removes card from top of draw pile and adds it to HumanPlayer's hand.
	 * 
	 * @see GameScreen 
	 */
	
	public void drawCard(ArrayList<Card> drawPile) {
		this.getHand().add(drawPile.remove(drawPile.size() -1));
	}
	
	/**
	 * Checks if user has any discardable cards, using isDiscardable method
	 * 
	 * @see GameScreen 
	 */
	public boolean hasDiscardableCard(Card lastCard){
		
		boolean hasDiscardableCard = false;
		for (Card card : this.getHand()) {
			if (card.isDiscardable(lastCard)) {
				hasDiscardableCard = true;
			}
		}
		return hasDiscardableCard;
		
	}
	
	/**
	 * Removes discarded card from the HumanPlayers hand
	 * @param selectedCard
	 * 
	 */
	public void playCard(Card selectedCard) {
		this.getHand().remove(selectedCard);
	}
}
