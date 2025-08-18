package guiGameSession;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * After player moves a WildCard, player can choose a color.
 * 
 * @author Aykut Bir
 * @since 10/05/2024
 */


public class ChooseColorScreen extends JDialog {
	
	private GameScreen owner;
	
	/**
	 * 
	 * Allows player to choose color to change the selectedColor field of WildCard
	 * this action is listened by GameScreen 
	 * 
	 * @param owner :GameScreen
	 * @param modal :boolean
	 */
	public ChooseColorScreen(GameScreen owner, boolean modal) {
		
		super(owner, modal);
		this.owner = owner;
		setTitle("Choose Color!");
		setResizable(false);
		setBounds(100, 100, 660, 123);
		getContentPane().setLayout(null);
		
		JButton btnRed = new JButton("");
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorSelected("Red");
			}
		});
		btnRed.setBackground(Color.RED);
		btnRed.setBounds(10, 10, 150, 66);
		getContentPane().add(btnRed);
		btnRed.setOpaque(true);
		
	
		JButton btnBlue = new JButton("");
		btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorSelected("Blue");
			}
		});
		btnBlue.setBackground(Color.BLUE);
		btnBlue.setBounds(486, 10, 150, 66);
		getContentPane().add(btnBlue);
		btnBlue.setOpaque(true);

		
		JButton btnYellow = new JButton("");
		btnYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorSelected("Yellow");
			}
		});
		btnYellow.setBackground(Color.YELLOW);
		btnYellow.setBounds(170, 10, 150, 66);
		getContentPane().add(btnYellow);
		btnYellow.setOpaque(true);

		
		JButton btnGreen = new JButton("");
		btnGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorSelected("Green");
			}
		});
		btnGreen.setBackground(Color.GREEN);
		btnGreen.setBounds(326, 10, 150, 66);
		getContentPane().add(btnGreen);
		btnGreen.setOpaque(true);

	}
	
	/**
	 * Reminds the listener GameScreen about selected color
	 * @param color :String
	 */
	private void colorSelected(String color) {
		owner.listenColorSelection(color);
		dispose();
	}
	
}
