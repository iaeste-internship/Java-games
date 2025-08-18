package guiGameSession;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import gameSessionMenager.Card;

/**
 * Pop-up screen that contains ScrollPane which will be loaded with card images.
 * 
 * @author Aykut Bir
 * @since 10/05/2024
 */

public class YourDeck extends JDialog {

	/**
	 * Displays whole deck that player has.
	 * Utilized custom JLabels named DeckCardLabel
	 * 
	 * @param owner :GameScreen
	 * @param modal :this pop-up is closed user cannot interact with GameScreen
	 * @param cards :ArrayList of Cards, used to set every card images.
	 */
	
	public YourDeck(GameScreen owner, boolean modal, ArrayList<Card> cards) {
		super(owner, modal);

		setResizable(false);
		getContentPane().setBackground(SystemColor.activeCaption);
		setTitle("Your Deck");
		setBounds(100, 100, 645, 690);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 10, 609, 633);
		getContentPane().add(scrollPane);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.setPreferredSize(new Dimension(543, 6000));
        
        for (Card card : cards) {
        	panel.add(new DeckCardLabel(card));
        }
	}

}
