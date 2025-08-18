package guiGameSession;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gameSessionMenager.Card;

/**
 * Custom card button that has functionality to inititiate GameSession loop.
 * 
 * @author Aykut Bir
 * @since 10/05/2024
 */

public class DiscardableCardButton extends JButton{
	
	DiscardableCards owner;
	
	/**
	 * 
	 * @param card :Card, whose image is used as buttons image
	 * @param listener :GameScreen, listens the button and gets the player move upon action.
	 * @param owner :DiscardableCards
	 */
	public DiscardableCardButton (Card card, GameScreen listener, DiscardableCards owner) {
		super("");
		this.setIcon(card.getIcon());
		this.owner = owner;
		
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.listenPlayerMove(card);
				owner.dispose();
			}
		});
		
	}
}
