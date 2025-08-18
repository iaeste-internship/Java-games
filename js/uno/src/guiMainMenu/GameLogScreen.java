package guiMainMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;


/**
 * Screen that displays user-selected game sessions log.
 * 
 * @author Aykut Bir
 * @since 12/05/2024
 * 
 */

public class GameLogScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Closes game history screen and displays entire log of selected game.
	 * Log of the game is fetched before initialization of game log screen from session spesific file
	 * (File: "src/filesGameHistory/[session name].txt")
	 * 
	 * @param owner :MainMenuScreen
	 * @param modal :boolean, until this panel is closed user cannot interact with MainMenuScreen
	 * @param log :String, entire log of the game selected
	 * @param gamename :String, session name to display to the user
	 * 
	 */
	public GameLogScreen(JFrame owner, boolean modal, String log, String gamename) {
		super(owner, modal);
		setTitle("Logs");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameLogScreen.class.getResource("/img/unoBack.PNG")));
		setBounds(100, 100, 671, 639);
		getContentPane().setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 67, 637, 513);
			getContentPane().add(scrollPane);
			
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			scrollPane.setViewportView(textArea);
			textArea.setPreferredSize(new Dimension(620, 20000));
			textArea.setText(log);
		}
		{
			JLabel lblNewLabel = new JLabel(String.format("Game: %s Log", gamename));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(10, 10, 637, 44);
			getContentPane().add(lblNewLabel);
		}
	}

}
