package guiMainMenu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exceptions.LengthException;
import exceptions.SimilarityException;
import exceptions.WhitespaceException;
import gameFileMenager.GameSaver;
import gameSessionMenager.GameSession;
import gameSessionMenager.WildCard;
import guiAuthentication.AuthenticationInfoScreen;
import guiGameSession.GameScreen;
import mainMenuMenager.NewGameNameChecker;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;


/**
 * 
 * Screen that displays new game creation panel.
 * 
 * @author Aykut Bir
 * @since 06/05/2024
 * 
 */


public class NewGameScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private MainMenuScreen mainMenu;
	private String playername;
	
	/**
	 * 
	 * Allows player to create a new game. 
	 * Checks user input for new game session name, given user input must follow given 3 conditions:
	 * 		
	 *		-Session name must be 8-25 characters long.
	 *		-Session name must not contain whitespace character.
	 *		-This session name already exists.
	 * 
	 * Otherwise corresponding error screen will be shown.
	 * 
	 * @see NewGameNameChecker
	 * @see MainMenuError
	 * 
	 * Successful game creation will save the session to text file. And open GameScreen.
	 * 
	 * @see GameSaver :newGameEntry method to see how new games are filed.
	 *  
	 * 
	 * @param owner :MainMenuScreen
	 * @param modal :boolean, until this panel is closed user cannot interact with MainMenuScreen
	 * @param playername :String, saves the game name entry paired with username
	 * 
	 */
	
	public NewGameScreen(MainMenuScreen owner, boolean modal, String playername) {
		super(owner, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewGameScreen.class.getResource("/img/unoBack.PNG")));
		setTitle("New Game");
		setResizable(false);
		this.mainMenu = owner;
		this.playername = playername;
		setBounds(100, 100, 444, 394);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.scrollbar);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create a New Game");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(67, 27, 298, 44);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(new Color(204, 204, 204));
		textField.setBackground(new Color(102, 102, 102));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(138, 111, 282, 44);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Session Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 111, 118, 44);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Number of Players:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(10, 178, 153, 44);
		contentPanel.add(lblNewLabel_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(173, 188, 158, 159);
		contentPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 102));
		scrollPane.setViewportView(panel);
		panel.setPreferredSize(new Dimension(80, 290));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		for (int i = 2 ; i <= 10 ; i++) {
			JButton btnNewButton = new JButton(i + " Players");
			int numberOfPlayers = i;
			panel.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean validName = true;
					String sessionName = textField.getText();
					try {
						NewGameNameChecker.check(sessionName);
						initializeGame(numberOfPlayers, sessionName);
					} catch (LengthException ex) {
						validName = false;
						activateMainMenuError("Invalid game session name. Session name must be 8-25 characters long.");
					} catch (WhitespaceException ex) {
						validName = false;
						activateMainMenuError("Invalid game session name. Session name must not contain whitespace character.");
					} catch (SimilarityException ex) {
						validName = false;
						activateMainMenuError("Invalid game session name. This session name already exists.");
					}
					
					if (validName) {
						GameSaver.newGameEntry(playername, sessionName);
					}
				}
			});
		}	
	}
	
	/**
	 * Closes main menu.
	 * Upon valid game session entry, runs the third part of the game, main GameSession frame.
	 * Every game starts with user's turn.
	 * 
	 * @param numberOfPlayers :int, initializes decks and hands of each player 
	 * @param sessionName :String, displays the session name on the GameScreen.
	 * 
	 * @see GameSession :to see backend of the main game logic
	 * @see GameScreen :to see frontend of the main game logic
	 */
	
	
	private void initializeGame(int numberOfPlayers, String sessionName) {
		this.dispose();
		mainMenu.dispose();
		try {
			GameSession gameSession = new GameSession(numberOfPlayers, sessionName, mainMenu.getUsername());
			GameScreen frame = new GameScreen(gameSession, sessionName, mainMenu.getUsername());
			gameSession.setGameScreen(frame);
			
			frame.listenDiscardPile(gameSession.getLastDiscardedCard());
			frame.initiatePlayerMove(gameSession.getLastDiscardedCard());
			
			if (gameSession.getLastDiscardedCard().getValue().contains("Wild")) {
				((WildCard) gameSession.getLastDiscardedCard()).setSelectedColor(null);
			}
			
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * Activates error panel with corresponding message.
	 * 
	 * @param message :String, message displayed on the panel
	 */
	private void activateMainMenuError(String message) {
		try {
			MainMenuError dialog = new MainMenuError(this, true, message);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
