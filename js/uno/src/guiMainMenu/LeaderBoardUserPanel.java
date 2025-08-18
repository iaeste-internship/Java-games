package guiMainMenu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIManager;

/**
 * 
 * Custom container that holds each leaderboard entries
 * Contains buttons to view spesific player statistics as player statistics screen,
 * as well as name and points of each player.
 * 
 * @author Aykut Bir
 * @since 05/05/2024
 * 
 */
public class LeaderBoardUserPanel extends JPanel {
	
	private String userName;
	private int points;
	private MainMenuScreen mainMenu;
	
	/**
	 * Constructor of GUI component of each leaderboard segment.
	 * 
	 * @param userName :String, User name of each entry also used to fetch user statistics.
	 * @param points :int, displayed next to user name
	 * @param mainMenu :communicates with buttons to open user statistics screen
	 * @param rank :int, used to determine panels background
	 * 
	 * @see MainMenuScreen :to see how leaderboard panels are inserted to leaderboard on the main menu
	 * 
	 */
	public LeaderBoardUserPanel(String userName, int points, MainMenuScreen mainMenu, int rank) {
		this.setPreferredSize(new Dimension(900, 50));
		this.userName = userName;
		this.points = points;
		this.mainMenu = mainMenu;
		
		if (rank == 1) {
			setBackground(new Color(255, 204, 0));
		} else if (rank == 2) {
			setBackground(Color.LIGHT_GRAY);
		} else if (rank == 3) {
			setBackground(new Color(204, 153, 51));
		} else if (rank % 2 == 1) {
			setBackground(new Color(153, 153, 102));
		} else {
			setBackground(new Color(143, 188, 143));
		}
		
		setLayout(null);
		
		JLabel lblUserName = new JLabel(rank + " - " + userName);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUserName.setBounds(105, 12, 233, 28);
		add(lblUserName);
		
		JLabel lblPoints = new JLabel(":....." + points + ".....");
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPoints.setBounds(347, 12, 543, 28);
		add(lblPoints);
		
		JButton btnShowUserInfo = new JButton("More");
		btnShowUserInfo.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnShowUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu.listenLeaderboard(userName);
			}
		});
		btnShowUserInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowUserInfo.setBounds(10, 10, 85, 30);
		add(btnShowUserInfo);
		
	}
	
}
