package guiGameSession;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gameFileMenager.EndgameFileMenager;
import gameFileMenager.RoundLogger;
import gameSessionMenager.Bot;
import gameSessionMenager.Card;
import gameSessionMenager.GameListener;
import gameSessionMenager.GameSession;
import gameSessionMenager.Player;
import gameSessionMenager.WildCard;
import guiAuthentication.AuthenticationInfoScreen;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import java.awt.SystemColor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The third and last main screen user encounters where game's visiual updates and user inputs are menaged.
 * It constantly listens other objects to log and change state of the game.
 * 
 * @author Aykut Bir
 * @since 10/05/2024
 */

public class GameScreen extends JFrame implements GameListener{

	private JPanel contentPane;
	
	private ImageIcon gameDirectionImage;
	private ImageIcon gameDirectionImageReverse;
	private String direction = "Normal";
	
	private JLabel lblCardsLeftDrawPile;
	private JLabel lblDirection;
	private JLabel lblDiscardPile;
	
	private JButton btnDraw;
	private JButton btnPointOut;
	private JButton btnSkip;
	private JButton btnUNO;
	private JButton btnViewYourDeck;
	private JButton btnExitGame;
	private JButton btnViewDiscardableCards;
	private JButton btnDetails;
	
	private Map<String, JLabel> cardsLeftLabels = new HashMap<>();
	
	private GameSession gameSession;
	private boolean unoStatus = false;
	private String playername;
	
	private RoundLogger logger;
	private String roundActions;
			
	/**
	 * Initiates the screen with correct bots and seatings, sets the main frame and 
	 * components up.
	 * 
	 * Refer to the helper methods in the class body to see button functionality.
	 * 
	 * @param gameSession :GameSession object that GameScreen constantly communicates
	 * @param sessionName :String 
	 * @param playername :String
	 */
	public GameScreen(GameSession gameSession, String sessionName, String playername) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1202, 756);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		gameDirectionImage = new ImageIcon(GameScreen.class.getResource("/img/arrow.PNG"));
		gameDirectionImageReverse = new ImageIcon(GameScreen.class.getResource("/img/arrowReverse.PNG"));
		this.gameSession = gameSession;
		this.playername = playername;
		this.logger = gameSession.getGameLogger();
		
		JPanel table = new JPanel();
		table.setBackground(new Color(204, 204, 153));
		table.setBounds(318, 83, 552, 552);
		contentPane.add(table);
		table.setLayout(null);
		
		JLabel lblCardsLeftDrawPile = new JLabel(String.format("%d", gameSession.getDrawPile().size()));
		lblCardsLeftDrawPile.setBackground(new Color(255, 255, 255));
		lblCardsLeftDrawPile.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
		lblCardsLeftDrawPile.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCardsLeftDrawPile.setBounds(182, 338, 60, 54);
		table.add(lblCardsLeftDrawPile);
		this.lblCardsLeftDrawPile = lblCardsLeftDrawPile;
		
		JLabel lblDiscardPile = new JLabel("");
		lblDiscardPile.setIcon(new ImageIcon(GameScreen.class.getResource("/img/unoBack.PNG")));
		lblDiscardPile.setBounds(285, 185, 98, 143);
		table.add(lblDiscardPile);
		this.lblDiscardPile = lblDiscardPile;
		
		JLabel lblDrawPile = new JLabel("");
		lblDrawPile.setIcon(new ImageIcon(GameScreen.class.getResource("/img/unoBack.PNG")));
		lblDrawPile.setBounds(161, 185, 98, 143);
		table.add(lblDrawPile);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerDraw();
				initiatePlayerMove(gameSession.getDiscardPile().get(gameSession.getDiscardPile().size() - 1));
			}
		});
		btnDraw.setEnabled(false);
		btnDraw.setBackground(new Color(153, 102, 102));
		btnDraw.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDraw.setBounds(165, 148, 85, 27);
		table.add(btnDraw);
		this.btnDraw = btnDraw;
		
		JButton btnPointOut = new JButton("Point Out!");
		btnPointOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listenPointOut();
			}
		});
		btnPointOut.setEnabled(false);
		btnPointOut.setBackground(new Color(102, 102, 153));
		btnPointOut.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPointOut.setForeground(new Color(0, 0, 0));
		btnPointOut.setBounds(212, 515, 128, 27);
		table.add(btnPointOut);
		this.btnPointOut = btnPointOut;
		
		JPanel panelPilesBack = new JPanel();
		panelPilesBack.setBackground(new Color(153, 153, 102));
		panelPilesBack.setBounds(122, 131, 307, 261);
		table.add(panelPilesBack);
		
		JLabel lblDirection = new JLabel("");
		lblDirection.setIcon(gameDirectionImage);
		lblDirection.setBounds(212, 419, 128, 86);
		table.add(lblDirection);
		this.lblDirection = lblDirection;
		
		JButton btnDetails = new JButton("Round Details");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewRoundLog();
			}
		});
		btnDetails.setEnabled(false);
		btnDetails.setBackground(new Color(102, 153, 0));
		btnDetails.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDetails.setBounds(212, 398, 128, 21);
		table.add(btnDetails);
		this.btnDetails = btnDetails;
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 153));
		panel.setBounds(87, 664, 1014, 55);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnSkip = new JButton("   Skip   ");
		btnSkip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerSkip();
				logger.updateRoundLog(String.format("%s skips this round.", playername));
			}
		});
		btnSkip.setEnabled(false);
		btnSkip.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSkip.setBackground(new Color(51, 102, 153));
		panel.add(btnSkip, BorderLayout.WEST);
		this.btnSkip = btnSkip;
		
		JButton btnUNO = new JButton("  UNO!  ");
		btnUNO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unoStatus = true;
				logger.updateRoundLog(String.format("%s declares UNO!", playername));
				btnUNO.setEnabled(false);
			}
		});
		btnUNO.setEnabled(false);
		btnUNO.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUNO.setBackground(new Color(51, 102, 153));
		panel.add(btnUNO, BorderLayout.EAST);
		this.btnUNO = btnUNO;
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JButton btnViewYourDeck = new JButton("View Your Deck");
		btnViewYourDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayYourDeck();
			}
		});
		btnViewYourDeck.setEnabled(false);
		btnViewYourDeck.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnViewYourDeck.setBackground(new Color(102, 51, 51));
		btnViewYourDeck.setBounds(0, 0, 399, 55);
		panel_1.add(btnViewYourDeck);
		this.btnViewYourDeck = btnViewYourDeck;
		
		JButton btnViewDiscardableCards = new JButton("View Discardable Cards");
		btnViewDiscardableCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayDiscardableCards();
			}
		});
		btnViewDiscardableCards.setEnabled(false);
		btnViewDiscardableCards.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnViewDiscardableCards.setBackground(new Color(102, 51, 51));
		btnViewDiscardableCards.setBounds(397, 0, 399, 55);
		panel_1.add(btnViewDiscardableCards);
		this.btnViewDiscardableCards = btnViewDiscardableCards;
		
		JPanel panelDeckBack = new JPanel();
		panelDeckBack.setBackground(new Color(102, 102, 153));
		panelDeckBack.setBounds(25, 649, 1138, 70);
		contentPane.add(panelDeckBack);
		
		JLabel lblPlayerName = new JLabel(playername);
		lblPlayerName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPlayerName.setBounds(25, 608, 283, 37);
		contentPane.add(lblPlayerName);
		
		JLabel lblSessionName = new JLabel(sessionName);
		lblSessionName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSessionName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSessionName.setBounds(880, 608, 283, 37);
		contentPane.add(lblSessionName);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayExitScreen();
			}
		});
		btnExitGame.setEnabled(false);
		btnExitGame.setForeground(Color.RED);
		btnExitGame.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnExitGame.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExitGame.setBounds(10, 10, 130, 37);
		contentPane.add(btnExitGame);
		this.btnExitGame = btnExitGame;
		
		
		for (int i = 1 ; i < gameSession.getAllPlayers().length ; i++) {
			if (i == 1) {
				JLabel lblCardsLeft1 = new JLabel(String.format("%d", gameSession.getAllPlayers()[i].getHand().size()));
				lblCardsLeft1.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
				lblCardsLeft1.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblCardsLeft1.setBounds(482, 401, 60, 54);
				table.add(lblCardsLeft1);
				
				
				JLabel lblPlayer1 = new JLabel(gameSession.getAllPlayers()[i].getName());
				lblPlayer1.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPlayer1.setBounds(880, 478, 241, 65);
				contentPane.add(lblPlayer1);
				
				JPanel plyrbck1 = new JPanel();
				plyrbck1.setBackground(new Color(154, 205, 50));
				plyrbck1.setBounds(464, 401, 78, 54);
				table.add(plyrbck1);
				cardsLeftLabels.put(gameSession.getAllPlayers()[i].getName(), lblCardsLeft1);
			}
			
			if (i == 2) {
				JLabel lblCardsLeft2 = new JLabel(String.format("%d", gameSession.getAllPlayers()[i].getHand().size()));
				lblCardsLeft2.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
				lblCardsLeft2.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblCardsLeft2.setBounds(482, 254, 60, 54);
				table.add(lblCardsLeft2);
				
				
				JLabel lblPlayer2 = new JLabel(gameSession.getAllPlayers()[i].getName());
				lblPlayer2.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPlayer2.setBounds(880, 330, 241, 65);
				contentPane.add(lblPlayer2);
				
				JPanel plyrbck2 = new JPanel();
				plyrbck2.setBackground(new Color(100, 149, 237));
				plyrbck2.setBounds(464, 254, 78, 54);
				table.add(plyrbck2);
				cardsLeftLabels.put(gameSession.getAllPlayers()[i].getName(), lblCardsLeft2);
				
			}
			
			if (i == 3) {
				JLabel lblCardsLeft3 = new JLabel(String.format("%d", gameSession.getAllPlayers()[i].getHand().size()));
				lblCardsLeft3.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
				lblCardsLeft3.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblCardsLeft3.setBounds(482, 102, 60, 54);
				table.add(lblCardsLeft3);
				
				
				JLabel lblPlayer3 = new JLabel(gameSession.getAllPlayers()[i].getName());
				lblPlayer3.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPlayer3.setHorizontalAlignment(SwingConstants.LEFT);
				lblPlayer3.setBounds(880, 178, 241, 65);
				contentPane.add(lblPlayer3);
				
				JPanel plyrbck3 = new JPanel();
				plyrbck3.setBackground(new Color(205, 133, 63));
				plyrbck3.setBounds(464, 102, 78, 54);
				table.add(plyrbck3);
				cardsLeftLabels.put(gameSession.getAllPlayers()[i].getName(), lblCardsLeft3);
			}
			
			if (i == 4) {
				JLabel lblCardsLeft4 = new JLabel(String.format("%d", gameSession.getAllPlayers()[i].getHand().size()));
				lblCardsLeft4.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
				lblCardsLeft4.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblCardsLeft4.setBounds(482, 10, 60, 54);
				table.add(lblCardsLeft4);
				
				
				JPanel plyrbck4 = new JPanel();
				plyrbck4.setBackground(new Color(153, 50, 204));
				plyrbck4.setBounds(464, 10, 78, 54);
				table.add(plyrbck4);
				
				JLabel lblPlayer4 = new JLabel(gameSession.getAllPlayers()[i].getName());
				lblPlayer4.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
				lblPlayer4.setBounds(751, 8, 241, 65);
				contentPane.add(lblPlayer4);
				cardsLeftLabels.put(gameSession.getAllPlayers()[i].getName(), lblCardsLeft4);
			}
			
			if (i == 5) {
				JLabel lblPlayer5 = new JLabel(gameSession.getAllPlayers()[i].getName());
				lblPlayer5.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
				lblPlayer5.setBounds(476, 8, 241, 65);
				contentPane.add(lblPlayer5);
				
				
				JLabel lblCardsLeft5 = new JLabel(String.format("%d", gameSession.getAllPlayers()[i].getHand().size()));
				lblCardsLeft5.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
				lblCardsLeft5.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblCardsLeft5.setBounds(250, 10, 60, 54);
				table.add(lblCardsLeft5);
				
				
				JPanel plyrbck5 = new JPanel();
				plyrbck5.setBackground(new Color(218, 112, 214));
				plyrbck5.setBounds(232, 10, 78, 54);
				table.add(plyrbck5);
				cardsLeftLabels.put(gameSession.getAllPlayers()[i].getName(), lblCardsLeft5);
			}
			
			if (i == 6) {
				JLabel lblCardsLeft6 = new JLabel(String.format("%d", gameSession.getAllPlayers()[i].getHand().size()));
				lblCardsLeft6.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblCardsLeft6.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
				lblCardsLeft6.setBounds(20, 10, 60, 54);
				table.add(lblCardsLeft6);
				
				
				JLabel lblPlayer6 = new JLabel(gameSession.getAllPlayers()[i].getName());
				lblPlayer6.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPlayer6.setHorizontalAlignment(SwingConstants.CENTER);
				lblPlayer6.setBounds(195, 8, 241, 65);
				contentPane.add(lblPlayer6);
				
				JPanel plyrbck6 = new JPanel();
				plyrbck6.setBackground(new Color(143, 188, 143));
				plyrbck6.setBounds(10, 10, 78, 54);
				table.add(plyrbck6);
				cardsLeftLabels.put(gameSession.getAllPlayers()[i].getName(), lblCardsLeft6);
				
			}
			
			if (i == 7) {
				
				JLabel lblCardsLeft7 = new JLabel(String.format("%d", gameSession.getAllPlayers()[i].getHand().size()));
				lblCardsLeft7.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
				lblCardsLeft7.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblCardsLeft7.setBounds(20, 102, 60, 54);
				table.add(lblCardsLeft7);
				
				
				JLabel lblPlayer7 = new JLabel(gameSession.getAllPlayers()[i].getName());
				lblPlayer7.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPlayer7.setHorizontalAlignment(SwingConstants.RIGHT);
				lblPlayer7.setBounds(67, 178, 241, 65);
				contentPane.add(lblPlayer7);
				
				JPanel plyrbck7 = new JPanel();
				plyrbck7.setBackground(new Color(205, 92, 92));
				plyrbck7.setBounds(10, 102, 78, 54);
				table.add(plyrbck7);
				cardsLeftLabels.put(gameSession.getAllPlayers()[i].getName(), lblCardsLeft7);
			}
			
			if (i == 8) {
				JLabel lblCardsLeft8 = new JLabel(String.format("%d", gameSession.getAllPlayers()[i].getHand().size()));
				lblCardsLeft8.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
				lblCardsLeft8.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblCardsLeft8.setBounds(20, 254, 60, 54);
				table.add(lblCardsLeft8);
				
				
				JLabel lblPlayer8 = new JLabel(gameSession.getAllPlayers()[i].getName());
				lblPlayer8.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPlayer8.setHorizontalAlignment(SwingConstants.RIGHT);
				lblPlayer8.setBounds(67, 330, 241, 65);
				contentPane.add(lblPlayer8);
				
				JPanel plyrbck8 = new JPanel();
				plyrbck8.setBackground(new Color(106, 90, 205));
				plyrbck8.setBounds(10, 254, 78, 54);
				table.add(plyrbck8);
				cardsLeftLabels.put(gameSession.getAllPlayers()[i].getName(), lblCardsLeft8);
			}
			
			if (i == 9) {
				JLabel lblCardsLeft9 = new JLabel(String.format("%d", gameSession.getAllPlayers()[i].getHand().size()));
				lblCardsLeft9.setIcon(new ImageIcon(GameScreen.class.getResource("/img/cardsLeftIcon.PNG")));
				lblCardsLeft9.setFont(new Font("Times New Roman", Font.BOLD, 20));
				lblCardsLeft9.setBounds(20, 401, 60, 54);
				table.add(lblCardsLeft9);
				
				
				JLabel lblPlayer9 = new JLabel(gameSession.getAllPlayers()[i].getName());
				lblPlayer9.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblPlayer9.setHorizontalAlignment(SwingConstants.RIGHT);
				lblPlayer9.setBounds(67, 478, 241, 65);
				contentPane.add(lblPlayer9);
				
				JPanel plyrbck9 = new JPanel();
				plyrbck9.setBackground(new Color(176, 196, 222));
				plyrbck9.setBounds(10, 401, 78, 54);
				table.add(plyrbck9);
				cardsLeftLabels.put(gameSession.getAllPlayers()[i].getName(), lblCardsLeft9);
			}
		}
	}
	
	/**
	 * Enables the intake of user inputs. Stopping game loop execution until user's input.
	 * Corresponding buttons stays disabled:
	 * 		UNO! button - If user cannot say UNO 
	 * 		Draw button - If user has at least one discardable card
	 * 		Skip/View Discardable Cards buttons - If user does not have any discardable cards
	 * 		Point Out button - If any bot does not forget to declare UNO!
	 * 		 
	 * 
	 * @param lastCard :Card, Last discarded card
	 */
	public void initiatePlayerMove(Card lastCard) {
		disableAllButtons();
		roundActions = logger.getRoundActions();
		btnDetails.setEnabled(true);
		btnExitGame.setEnabled(true);
		btnViewYourDeck.setEnabled(true);
		if (!gameSession.getPointOut().isEmpty()) {
			btnPointOut.setEnabled(true);
		}
		if (gameSession.getPlayer().getDiscardableCards(lastCard).size() == 0) {
			btnDraw.setEnabled(true);
		} else {
			btnSkip.setEnabled(true);
			btnViewDiscardableCards.setEnabled(true);
			btnUNO.setEnabled(gameSession.getPlayer().getHand().size() == 2);
		}
	}
	
	/**
	 * Disables all buttons before and after player turn.
	 */
	private void disableAllButtons() {
		
		btnDraw.setEnabled(false);
		btnPointOut.setEnabled(false);
		btnSkip.setEnabled(false);
		btnUNO.setEnabled(false);
		btnViewYourDeck.setEnabled(false);
		btnExitGame.setEnabled(false);
		btnViewDiscardableCards.setEnabled(false);
		btnDetails.setEnabled(false);
		
	}
	
	/**
	 * Displays player's deck
	 */
	private void displayYourDeck() {
		try {
			YourDeck dialog = new YourDeck(this, true, gameSession.getPlayer().getHand());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws a card to a player's hand and updates screen and logger accordingly.
	 */
	private void playerDraw() {
		logger.updateRoundLog(String.format("%s draws.", playername));
		if (gameSession.getDrawPile().size() == 0) {
			gameSession.discardToDrawPile();
		} else {
			gameSession.getPlayer().drawCard(gameSession.getDrawPile());
			listenDrawDeck();
		}
	}
	/**
	 * Opens round log
	 * It also refreshes the round log fow incoming rounds for clarity
	 */
	private void viewRoundLog() {
		logger.flush();
		try {
			RoundLogScreen dialog = new RoundLogScreen(this, true, roundActions);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens discardable cards user has, selecting a card initiates bot moves causing the game to continue with its loop
	 */
	private void displayDiscardableCards() {
		try {
			DiscardableCards dialog = new DiscardableCards(this, true, gameSession.getPlayer().findDiscardableCards(gameSession.getLastDiscardedCard()));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Skips player turn, initiates bot moves causing the game to continue with its loop
	 * Also logs the corresponding entry.
	 */
	private void playerSkip() {
		disableAllButtons();
		gameSession.runBotTurn(gameSession.determineNextPlayerIndex(0), true);
	}
	
	/**
	 * Opens exit panel to allow player to leave the game
	 */
	private void displayExitScreen() {
			try {
				ExitGameScreen dialog = new ExitGameScreen(this, true, this.gameSession);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	/**
	 * Listens draw deck and it updates card count of the draw deck
	 */
	@Override
	public void listenDrawDeck() {
		lblCardsLeftDrawPile.setText(String.format("%d", gameSession.getDrawPile().size()));
	}
	
	/**
	 * Updates the icon of discard pile, with the parameter card
	 */
	@Override
	public void listenDiscardPile(Card lastCard) {
	    lblDiscardPile.setIcon(lastCard.getIcon());
	}
	
	/**
	 * Listens the direction, if it changes:
	 * -Logs it to loggers
	 * -Updates the direction image
	 * -Sets the direction field of bot GameScreen and GameSession
	 */
	@Override
	public void listenDirection() {
		logger.updateRoundLog("Direction is reversed!");
		if (lblDirection.getIcon().equals(gameDirectionImage)){
			lblDirection.setIcon(gameDirectionImageReverse);
			direction = "Reverse";
			gameSession.setDirection(direction);
		} else { 
			lblDirection.setIcon(gameDirectionImage);
			direction = "Normal";
			gameSession.setDirection(direction);
		}
	}
	
	/**
	 * Listens player move and initiates bot moves with parameter card.
	 * Updates the logs.
	 * Penalizes the player if player does not declare UNO!
	 * Updates discard pile
	 * If player plays its last card initiates end game screen
	 */
	@Override
	public void listenPlayerMove(Card card) {
		
		gameSession.getPlayer().playCard(card);
		gameSession.getDiscardPile().add(card);
		listenDiscardPile(gameSession.getLastDiscardedCard());
		disableAllButtons();
		
		logger.updateRoundLog(String.format("%s plays %s. %s has %d cards left!", playername, card.getName(), playername, gameSession.getPlayer().getHand().size()));
		
		if (gameSession.getLastDiscardedCard().getValue().equals("Reverse")) {
			listenDirection();
		}
		
		if (gameSession.getLastDiscardedCard().getValue().contains("Wild")) {
			try {
				ChooseColorScreen dialog = new ChooseColorScreen(this, true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if ((gameSession.getPlayer().getHand().size() == 1) && (unoStatus == false)) {
			logger.updateRoundLog(String.format("BOT Aykut pointed out, %s forgot to say UNO!", playername));
			playerDraw();
			playerDraw();
		} else {
			unoStatus = false;
		}
		
		if (gameSession.getPlayer().getHand().size() == 0) {
			listenGameEnded(true, gameSession.getAllPlayers()[1].getName());
		} else {
			gameSession.emptyPointOut();
			gameSession.runBotTurn(gameSession.determineNextPlayerIndex(0), true);
		}
		
	}

	/**
	 * Listens point out button and if clicked:
	 * -Penalizes bots
	 * -Updates the log accordingly
	 */
	@Override
	public void listenPointOut() {
		for (Player bot : gameSession.getPointOut()) {
			logger.updateRoundLog(String.format("%s pointed out, %s forgot to say UNO!",playername, bot.getName()));
			gameSession.botDrawCard(bot);
			gameSession.botDrawCard(bot);
		}
		gameSession.emptyPointOut();
		btnPointOut.setEnabled(false);
	}
	
	/**
	 * Listens the color selection of Players
	 * Sets the corresponding field of wild card and updates the loggers.
	 */
	@Override
	public void listenColorSelection(String selection) {
		((WildCard) (gameSession.getLastDiscardedCard())).setSelectedColor(selection);
		logger.updateRoundLog(String.format("%s chooses color %s", playername, selection));
	}
	
	/**
	 * Listens the card counters of bots, after each bot play updates these card counters.
	 */
	@Override
	public void listenCardCounts(Player player) {
		if (player instanceof Bot) {
			cardsLeftLabels.get(player.getName()).setText(String.format("%d", player.getHand().size()));
		}
	}
	
	/**
	 * 
	 * Concludes the game calling corresponding file menagers and end screen panels.
	 * 
	 */
	@Override
	public void listenGameEnded(boolean playerWon, String botName) {
		logger.updateRoundLog(String.format("Game is finished! %s won the game.", playerWon ? playername : botName));
		int playerPoints = gameSession.calculatePoints();
		if (playerWon) {
			logger.updateRoundLog(String.format("%s earned %d points!", playername, playerPoints));
		}
		
		EndgameFileMenager.writeGameHistory(gameSession.getSessionName(), logger.getGameHistoryLogger());
		EndgameFileMenager.updatePlayerStats(playername, playerWon, playerPoints);
		
		try {
			GameEndScreen dialog = new GameEndScreen(this, true, playerWon, playerPoints, botName);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
