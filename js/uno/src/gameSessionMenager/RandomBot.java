package gameSessionMenager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Specialized bot class that has different mechanics.
 * 
 * @author Aykut Bir
 * @since 09/05/2024
 * 
 */
public class RandomBot extends Bot{
	
	/**
	 * Constructor
	 * @param i :int
	 */
	public RandomBot(int i) {
		super(i);
	}

	/**
	 * Determines which card to play according to last card played
	 * Does not favour any card, plays randomly.
	 * 
	 * Utilizes Collections' shuffle() method during that process.
	 */
	@Override
	public Card playCard(Card lastCard) {
		
		Card choice;
		ArrayList<Card> discardableCards = this.findDiscardableCards(lastCard);
		Collections.shuffle(discardableCards);
		choice = discardableCards.get(0);
		this.getHand().remove(choice);
		
		return choice;
	}
	
	/**
	 * Asks uno to the bot, random bots forgot to say uno half of the time.
	 */

	@Override
	public boolean askUNO() {
		boolean uno = true;
		int forget = new Random().nextInt(100);
		if (forget < 50) {
			uno = false;
		}
		return uno;
	}

}
