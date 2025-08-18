package guiMainMenu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainMenuMenager.LeaderBoardInitializer;
import mainMenuMenager.LeaderBoardListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.SoftBevelBorder;

import guiAuthentication.LoginAndRegisterScreen;

import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

/**
 * Main menu panel displayed after successfully logging in.
 * Contains:
 * 
 * 		-Leaderboard
 *		-New/Load game buttons
 *		-Log out button
 *		-Game history button
 *		-User statistics button next to each entry 
 *		in leaderboard as well as next to profile panel of user
 *		
 *		(+Additional set of helper functions to set and maintain the main menu panel)
 * 
 * @author Aykut Bir
 * @since 05/05/2024
 * 
 */

public class MainMenuScreen extends JFrame implements LeaderBoardListener{

	private JPanel contentPane;
	private Map<String, Integer> ranking;
	private String username;
	
	/**
	 * Displays and Utilizes: 
	 * 
	 * 		-A leaderboard made out of custom panels following the descending ranking
	 * 		-Map data structure to initialize correct leaderboard panels
	 *		-LeaderBoardListener interface to listen leaderboard button activity
	 *		-Logging out option to player to turn back to authentication page
	 * 
	 * @param username :String, user that is logged in
	 * 
	 * @see LeaderBoardInitializer :to see how leaderboard ranking initialized to a map object.
	 */
	
	public MainMenuScreen(String username) {
		super("Main Menu Screen");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuScreen.class.getResource("/img/uno.png")));
		setResizable(false);
		this.ranking = LeaderBoardInitializer.initializeLeaderBoard();
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1123, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 137, 902, 386);
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		
		JLabel lblLeaderBoard = new JLabel("------LEADERBOARD------");
		lblLeaderBoard.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 38));
		lblLeaderBoard.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeaderBoard.setBounds(100, 79, 902, 58);
		contentPane.add(lblLeaderBoard);
		contentPane.add(scrollPane);
		
		JPanel leaderBoardMainPanel = new JPanel();
		leaderBoardMainPanel.setBackground(new Color(238, 232, 170));
		scrollPane.setViewportView(leaderBoardMainPanel);
		leaderBoardMainPanel.setPreferredSize(new Dimension(901, 6000));
		leaderBoardMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		int i = 1;
		for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
			leaderBoardMainPanel.add(new LeaderBoardUserPanel(entry.getKey(), entry.getValue(), this, i));
			i++;
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 483, 59);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, null, null, null));
		panel.setBackground(new Color(100, 149, 237));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnUserStats = new JButton("USER STATICTICS");
		btnUserStats.setBounds(320, 10, 153, 40);
		panel.add(btnUserStats);
		btnUserStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openUserStatisticsScreen(username);
			}
		});
		btnUserStats.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUserStats.setBackground(UIManager.getColor("Button.disabledForeground"));
		
		JLabel lblPlayer = new JLabel("Player:");
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPlayer.setBounds(10, 10, 68, 39);
		panel.add(lblPlayer);
		
		JLabel lblPlayerName = new JLabel(username);
		lblPlayerName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPlayerName.setBounds(80, 10, 356, 39);
		panel.add(lblPlayerName);
		
		JButton btnNewGame = new JButton("NEW GAME");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openNewGameScreen();
			}
		});
		btnNewGame.setBounds(100, 539, 446, 104);
		btnNewGame.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnNewGame.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(btnNewGame);
		
		JButton btnLoadGame = new JButton("LOAD GAME");
		btnLoadGame.setBounds(556, 539, 446, 104);
		btnLoadGame.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnLoadGame.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openLoadGameScreen();
			}
		});
		contentPane.add(btnLoadGame);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.setBounds(950, 10, 131, 40);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					LoginAndRegisterScreen frame = new LoginAndRegisterScreen();
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnLogOut.setForeground(new Color(213, 0, 0));
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogOut.setBackground(UIManager.getColor("Button.disabledForeground"));
		contentPane.add(btnLogOut);
		
		JButton btnViewGameHistory = new JButton("Game History");
		btnViewGameHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openGameHistoryScreen();
			}
		});
		btnViewGameHistory.setForeground(Color.BLACK);
		btnViewGameHistory.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnViewGameHistory.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnViewGameHistory.setBounds(493, 10, 131, 40);
		contentPane.add(btnViewGameHistory);
		
	}
	
	/**
	 * 
	 * Displays logged user statististics.
	 * 
	 * 
	 * @param username :String, logged user's username to initialize corresponding user statistics screen
	 * 
	 * @see UserStatisticsScreen
	 * 
	 */
	
	private void openUserStatisticsScreen(String username) {
		try {
			UserStatisticsScreen dialog = new UserStatisticsScreen(this, true, username);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Displays logged user statististics from leaderboard
	 * 
	 * 
	 * @param leaderboardUsername :String, leaderboard user's username to initialize corresponding user statistics screen.
	 * 
	 * @see UserStatisticsScreen
	 * @see LeaderBoardUserPanel :see individual button initialization of each panel
	 * 
	 */
	
	public void listenLeaderboard(String leaderboardUsername) {
		
		try {
			UserStatisticsScreen dialog = new UserStatisticsScreen(this, true, leaderboardUsername);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * Displays new game panel to allow user to set new game. 
	 * 
	 * @see NewGameScreen
	 * 
	 */
	
	private void openNewGameScreen() {
		try {
			NewGameScreen dialog = new NewGameScreen(this, true, username);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Displays load game panel to allow user to resume loaded game. 
	 * 
	 * @see LoadGameScreen
	 * 
	 */
	
	private void openLoadGameScreen() {
		try {
			LoadGameScreen dialog = new LoadGameScreen(this, true, username);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * Displays game history panel to allow user to choose one of past games to see its game log. 
	 * 
	 * @see GameHistoryScreen
	 * 
	 */
	
	private void openGameHistoryScreen() {
		try {
			GameHistoryScreen dialog = new GameHistoryScreen(this, true, username);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter for username field.
	 * 
	 */
	
	public String getUsername() {
		return this.username;
	}
}
