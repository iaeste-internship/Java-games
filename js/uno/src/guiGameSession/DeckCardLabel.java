package guiGameSession;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gameSessionMenager.Card;


/**
 * Custom card label that displays cards image
 * 
 * @author Aykut Bir
 * @since 10/05/2024
 * 
 */


public class DeckCardLabel extends JLabel{
		
	/**
	 * 
	 * @param card, Card whose image used as label to display deck
	 */
	public DeckCardLabel (Card card) {
		
		super("");
		this.setIcon(card.getIcon());
		this.setBounds(161, 185, 98, 143);
		
	}
}
