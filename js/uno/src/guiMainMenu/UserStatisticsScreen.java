package guiMainMenu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainMenuMenager.UserStatistics;

import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;


/**
 * Panel that spesific user statistics are displayed. 
 * 
 * @author Aykut Bir
 * @since 05/05/2024
 * 
 */


public class UserStatisticsScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Displays fetched user statistics on labels on the panel. Utilizes array list on the process
	 * 
	 * @param owner :MainMenuScreen 
	 * @param modal :boolean, until this panel is closed user cannot interact with MainMenuScreen
	 * @param username :String, username to reach user spesific user textfile
	 * 
	 * @see UserStatistics :to see how all basic and derived user statistics are fetched from user spesific file.
	 * 
	 */
	
	
	public UserStatisticsScreen(JFrame owner, boolean modal, String username) {
		super(owner, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserStatisticsScreen.class.getResource("/img/cardsLeftIcon.PNG")));
		setTitle("Statistics");
		ArrayList<String> userStatistics = UserStatistics.getStatistics(username);
		setResizable(false);
		setBounds(100, 100, 435, 479);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPoints = new JLabel(String.format("Points: %s", userStatistics.get(4)));
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPoints.setBounds(10, 81, 401, 42);
		contentPanel.add(lblPoints);
		
		JLabel lblGamePlayed = new JLabel(String.format("Games Played: %s", userStatistics.get(3)));
		lblGamePlayed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGamePlayed.setBounds(10, 133, 401, 42);
		contentPanel.add(lblGamePlayed);
		
		JLabel lblGameWon = new JLabel(String.format("Games Won: %s", userStatistics.get(1)));
		lblGameWon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGameWon.setBounds(10, 185, 401, 42);
		contentPanel.add(lblGameWon);
		
		JLabel lblGameLost = new JLabel(String.format("Games Lost: %s", userStatistics.get(2)));
		lblGameLost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGameLost.setBounds(10, 237, 401, 42);
		contentPanel.add(lblGameLost);
		
		JLabel lblAverage = new JLabel(String.format("Average Points per Game: %s", userStatistics.get(5)));
		lblAverage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAverage.setBounds(10, 289, 401, 42);
		contentPanel.add(lblAverage);
		
		JLabel lblUserName = new JLabel(String.format("%s", userStatistics.get(0)));
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(10, 10, 401, 61);
		contentPanel.add(lblUserName);
		
		JLabel lblWLRatio = new JLabel(String.format("Win/Loss Ratio: %s", userStatistics.get(6)));
		lblWLRatio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWLRatio.setBounds(10, 341, 401, 42);
		contentPanel.add(lblWLRatio);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnNewButton.setBounds(326, 411, 85, 23);
		contentPanel.add(btnNewButton);
	}
}
