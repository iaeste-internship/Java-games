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
public class PassiveBot extends Bot{


	/**
	 * Constructor
	 * @param i :int
	 */
	public PassiveBot(int i) {
		super(i);
	}
	
	/**
	 * Determines which card to play according to last card played
	 * Favours numeric cards.
	 */
	@Override
	public Card playCard(Card lastCard) {
		
		Card choice;
		ArrayList<Card> discardableCards = this.findDiscardableCards(lastCard);
		ArrayList<Card> numberCards = new ArrayList<>();
		
		for (Card card : discardableCards) {
			if (card.getValue().matches("[0-9]")) {
				numberCards.add(card);
			}
		}
		if (numberCards.size() != 0) {
			choice = numberCards.get(0);
			this.getHand().remove(choice);
		} else {
			choice = discardableCards.get(0);
			this.getHand().remove(choice);
		}
		return choice;
		
	}
	
	/**
	 * Asks uno to the bot, passive bots sometimes forgets to say uno.
	 */

	@Override
	public boolean askUNO() {
		boolean uno = true;
		int forget = new Random().nextInt(100);
		if (forget < 30) {
			uno = false;
		}
		return uno;
	}

}
