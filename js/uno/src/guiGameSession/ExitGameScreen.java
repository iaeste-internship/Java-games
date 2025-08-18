package guiGameSession;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gameFileMenager.EndgameFileMenager;
import gameFileMenager.GameSaver;
import gameSessionMenager.GameSession;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * 
 * Opens the exiting game selection panel
 * 
 * @author Aykut Bir
 * @since 10/05/2024
 */
public class ExitGameScreen extends JDialog {
	
	JFrame owner;
	GameSession gameSession;
	
	/**
	 * 
	 * Opens exit panel of the game 
	 * Selecting save and exit:
	 * 		-Saves the game log and current point of the game
	 * 		-Creates according save and log files.
	 * Selecting Exit:
	 * 		-Only saves the log of game and creates according log file.
	 * 
	 * 
	 * @param owner :JFrame, GameSession
	 * @param modal :boolean,
	 * @param gamesession :GameSession, used to write corresponding log and save entry of the game session.
	 */
	
	public ExitGameScreen(JFrame owner, boolean modal, GameSession gamesession) {
		super(owner, modal);
		this.owner = owner;
		this.gameSession = gamesession;
		setResizable(false);
		setTitle("Exit Game");
		setBounds(100, 100, 721, 96);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Save and Exit");
		btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameSession.getGameLogger().updateRoundLog("Saving the Game");
				GameSaver.newGameLoadEntry(gameSession.getPlayer().getName(), gameSession.getSessionName());
				GameSaver.writeLoadFile(gamesession);
				EndgameFileMenager.writeGameHistory(gameSession.getSessionName(), gameSession.getGameLogger().getGameHistoryLogger());
				dispose();
				owner.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(225, 10, 132, 39);
		getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameSession.getGameLogger().updateRoundLog("Exiting the Game without Saving.");
				EndgameFileMenager.writeGameHistory(gameSession.getSessionName(), gameSession.getGameLogger().getGameHistoryLogger());
				dispose();
				owner.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnExit.setBounds(367, 10, 132, 39);
		getContentPane().add(btnExit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(255, 0, 0));
		btnCancel.setBackground(UIManager.getColor("Button.darkShadow"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(565, 10, 132, 39);
		getContentPane().add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Exit Game?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 167, 39);
		getContentPane().add(lblNewLabel);
	}
}
