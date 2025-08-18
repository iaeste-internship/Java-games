package gameSessionMenager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Specialized bot class that has different mechanics.
 * 
 * @author Aykut Bir
 * @since 09/05/2024
 * 
 */


public class AggresiveBot extends Bot{
	
	/**
	 * Constructor
	 * @param i: int
	 */
	
	public AggresiveBot(int i) {
		super(i);
	}

	/**
	 * Determines which card to play according to last card played
	 * Favours special cards.
	 */
	@Override
	public Card playCard(Card lastCard) {
		
		Card choice;
		ArrayList<Card> discardableCards = this.findDiscardableCards(lastCard);
		ArrayList<Card> specialCards = new ArrayList<>();
		for (Card card : discardableCards) {
			if (!card.getValue().matches("[0-9]")) {
				specialCards.add(card);
			}
		}
		if (specialCards.size() != 0) {
			choice = specialCards.get(0);
			this.getHand().remove(choice);
		} else {
			choice = discardableCards.get(0);
			this.getHand().remove(choice);
		}
		
		return choice;
	}
	
	/**
	 * Asks uno to the bot, aggresive bots are very likely returns true.
	 */
	@Override
	public boolean askUNO() {
		boolean uno = true;
		int forget = new Random().nextInt(100);
		if (forget < 10) {
			uno = false;
		}
		return uno;
	}
	
	
}
