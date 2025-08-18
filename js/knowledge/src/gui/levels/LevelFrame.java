package gui.levels;
import entities.RenderObject;
import entities.enemies.Enemy;
import entities.enemies.Professor;
import entities.enemies.SL;
import entities.enemies.TA;
import entities.player.Player;
import entities.player.User;
import entities.shotboxes.InfoBox;
import entities.shotboxes.QuestionBox;
import gui.CorruptedFileException;
import gui.GraphicsControl;
import gui.GuiController;
import gui.LoginFrame;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

/**
 * <h3>This is an abstract class for all level frames.</h3>
 * <p>Initialize all common labels panes etc. </p>
 * <p>Has gameOver and levelPassed methods for interaction with different frames.</p>
 */
public abstract class LevelFrame extends JFrame {
	protected JPanel contentPane;
	protected User user;
	protected static ArrayList<String> slQuestions = new ArrayList<String>();
	protected static ArrayList<String> taQuestions = new ArrayList<String>();
	protected static ArrayList<String> profQuestions = new ArrayList<String>();
	protected static ArrayList<String> slInfos = new ArrayList<String>();
	protected static ArrayList<String> taInfos = new ArrayList<String>();
	protected static ArrayList<String> profInfos = new ArrayList<String>();
	protected JLabel levellbl;
	protected Player player;
	
	/**
	 * Constructor for LevelFrame.
	 * @param user Associated user
	 */
	public LevelFrame(User user) {
		clearRenders(); // clear renders in case of faulty level changes (There is non in this project)
		this.user = user;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(107, 82, 23));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		levellbl = new JLabel("Level 1");
		levellbl.setForeground(new Color(246, 245, 244));
		levellbl.setFont(new Font("Dialog", Font.BOLD, 18));
		levellbl.setBounds(718, 12, 91, 17);
		contentPane.add(levellbl);
		
		JLabel lblHealth = new JLabel("Health: 100");
		lblHealth.setForeground(new Color(246, 245, 244));
		lblHealth.setBounds(718, 70, 78, 17);
		contentPane.add(lblHealth);
		
		JLabel lblQuestions = new JLabel("Questions:");
		lblQuestions.setForeground(new Color(246, 245, 244));
		lblQuestions.setBounds(718, 120, 78, 17);
		contentPane.add(lblQuestions);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(712, 149, 126, 135);
		contentPane.add(scrollPane);
		
		JTextArea questionsArea = new JTextArea();
		questionsArea.setForeground(new Color(255, 255, 255));
		questionsArea.setBackground(new Color(134, 94, 60));
		questionsArea.setFont(new Font("Dialog", Font.BOLD, 10));
		questionsArea.setEditable(false);
		scrollPane.setViewportView(questionsArea);
		
		
		JLabel lblInformations = new JLabel("Informations:");
		lblInformations.setForeground(new Color(246, 245, 244));
		lblInformations.setBounds(718, 296, 91, 17);
		contentPane.add(lblInformations);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(712, 325, 126, 135);
		contentPane.add(scrollPane_1);
		
		JTextArea infosArea = new JTextArea();
		infosArea.setForeground(new Color(255, 255, 255));
		infosArea.setBackground(new Color(134, 94, 60));
		infosArea.setFont(new Font("Dialog", Font.BOLD, 10));
		infosArea.setEditable(false);
		scrollPane_1.setViewportView(infosArea);
		
		JLabel lblUser = new JLabel("User: "+user.getUsername());
		lblUser.setForeground(new Color(246, 245, 244));
		lblUser.setBounds(718, 41, 105, 17);
		contentPane.add(lblUser);
		
		JLabel lblScore = new JLabel("Score: ");
		lblScore.setForeground(new Color(246, 245, 244));
		lblScore.setBounds(718, 91, 60, 17);
		contentPane.add(lblScore);
		
		// Setting GUI interaction fields
		QuestionBox.setArea(questionsArea);
		InfoBox.setArea(infosArea);
		GraphicsControl.setHealthlb(lblHealth);
		GraphicsControl.setScorelbl(lblScore);
	}
	
	/**
	 * Sets the Arrays of questions and infos from given parameters. These parameters must be collected from text files.
	 * @param slQuestions
	 * @param taQuestions
	 * @param profQuestions
	 * @param slInfos
	 * @param taInfos
	 * @param profInfos
	 */
	public static void setArrays(ArrayList<String> slQuestions, ArrayList<String> taQuestions, ArrayList<String> profQuestions,ArrayList<String> slInfos, ArrayList<String> taInfos, ArrayList<String> profInfos) {
		LevelFrame.slQuestions = slQuestions;
		LevelFrame.taQuestions = taQuestions;
		LevelFrame.profQuestions = profQuestions;
		LevelFrame.slInfos = slInfos;
		LevelFrame.taInfos = taInfos;
		LevelFrame.profInfos = profInfos;
	}
	
	/**
	 * Sets the starting score of the game.
	 * @param initScore Starting score
	 */
	public void setInitScore(int initScore) {
		player.setScore(initScore);
	}
	
	/**
	 * Calls stop method for each RenderObject.
	 */
	public void stopAllMoves() {
		for(RenderObject r: RenderObject.getRenders()) {
			if(r instanceof TA) {
				((TA) r).getFocuser().stopMoveXRandoWithFocus();
			}
			if(r instanceof Professor) {
				((Professor) r).getFocuser().stopMoveXRandoWithFocus();
			}
			if(r instanceof Enemy) {
				((Enemy) r).stopMoveXRandom();
			}
		}
	}
	
	/**
	 * Stops all movements and clears the RenderObject.renders ArrayList.
	 */
	protected void clearRenders() {
		stopAllMoves();
		RenderObject.getRenders().clear();
	}
	
	/**
	 * <h3>Game Over method for ending the frame </h3>
	 * <p>Clears renders. </p>
	 * <p>Show option panes for informing user. </p>
	 * <p>Updates the user scores and the users.txt file. </p>
	 * <p>Changes the frame to the Login Frame via {@link gui.GuiController#changeFrame(JFrame, JFrame)} </p>
	 * @see gui.GuiController#updateUsers()
	 * @see entities.player.User#addScore(int)
	 */
	public void gameOver() {
		GuiController.addLogLine("Health dropped zero. Game over with score: "+player.getScore());
		stopAllMoves(); // clear renders stop anyway but I want to stop before the option pane showed up
		JOptionPane.showMessageDialog(null, "Game Over. You Loose", "Game Over", JOptionPane.PLAIN_MESSAGE, null);
		clearRenders();
		user.addScore(player.getScore());
		GuiController.updateUsers();
		GuiController.changeFrame(this, new LoginFrame());
	}
	
	/**
	 * Abstract class for level passing which is different in each LevelFrame.
	 * @param score starting score of the user
	 */
	public abstract void levelPassed(int score);
}
