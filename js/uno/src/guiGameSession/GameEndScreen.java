package guiGameSession;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Displays the message if user won or lost the game
 * 
 * @author Aykut Bir
 * @since 10/05/2024
 */
public class GameEndScreen extends JDialog {
	
	

	private final JPanel contentPanel = new JPanel();
	private JLabel message;
	
	/**
	 * 
	 * @param owner :JFrame, GameScreen
	 * @param modal :boolean
	 * @param playerWon :boolean
	 * @param points :int, displays points won if player wins the game
	 * @param botName :String, displays winner bot if player loses the game
	 */
	public GameEndScreen (JFrame owner, boolean modal, boolean playerWon, int points, String botName) {
		super(owner, modal);
		setResizable(false);
		setTitle("GAME ENDED!");
		setFont(new Font("Dialog", Font.PLAIN, 10));
		setBounds(100, 100, 749, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(10, 10, 715, 63);
			contentPanel.add(lblNewLabel);
			this.message = lblNewLabel;
		}
		
		JButton btnNewButton = new JButton("GOT IT!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				owner.dispose();
				
			}
		});
		btnNewButton.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(595, 83, 130, 36);
		contentPanel.add(btnNewButton);
		
		if (playerWon) {
			message.setText(String.format("You won! You earned %d points!", points));
		} else {
			message.setText(String.format("You lost, %s won the game!", botName));
		}
	}

}
