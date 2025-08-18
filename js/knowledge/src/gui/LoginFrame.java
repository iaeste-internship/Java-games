package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entities.player.User;
import gui.levels.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
 * <p>LoginFrame consist of login part, register part and high score button.</p>
 * <p>Uses GuiController static methods for validate and register users.</p>
 */
public class LoginFrame extends JFrame {
	private JPanel contentPane;
	private JTextField lgnUserNameField;
	private JTextField regUserNameField;
	private JPasswordField lgnPasswordField;
	private JPasswordField regPasswordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private HighScoreFrame scoreFrame;
	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 580, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLogin.setBounds(30, 12, 88, 33);
		contentPane.add(lblLogin);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRegister.setBounds(276, 18, 88, 17);
		contentPane.add(lblRegister);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(12, 72, 81, 17);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 114, 81, 17);
		contentPane.add(lblPassword);
		
		lgnUserNameField = new JTextField();
		lgnUserNameField.setBounds(104, 70, 114, 21);
		contentPane.add(lgnUserNameField);
		lgnUserNameField.setColumns(10);
		
		JLabel lblUserName_1 = new JLabel("User Name:");
		lblUserName_1.setBounds(263, 68, 81, 17);
		contentPane.add(lblUserName_1);
		
		regUserNameField = new JTextField();
		regUserNameField.setColumns(10);
		regUserNameField.setBounds(355, 66, 114, 21);
		contentPane.add(regUserNameField);
		
		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(263, 110, 81, 17);
		contentPane.add(lblPassword_1);
		
		lgnPasswordField = new JPasswordField();
		lgnPasswordField.setBounds(104, 112, 114, 21);
		contentPane.add(lgnPasswordField);
		
		regPasswordField = new JPasswordField();
		regPasswordField.setBounds(355, 110, 114, 21);
		contentPane.add(regPasswordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			/**
			 * <p>Login button action.</p>
			 * <p>Validates users via {@link gui.GuiController#findUser(String, String)}</p>
			 * <p>Starts the game by using {@link gui.GuiController#changeFrame(JFrame, JFrame)}</p>
			 * @param arg0
			 */
			public void actionPerformed(ActionEvent arg0) {
				User u = GuiController.findUser(lgnUserNameField.getText(),String.valueOf(lgnPasswordField.getPassword()));
				if(u == null) {
					JOptionPane.showMessageDialog(null, "Username or Password is wrong", "Error", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				if(scoreFrame != null) {scoreFrame.dispose();} // close the high score frame if its open
				GuiController.addLogLine("New game session started with the user: "+u.getUsername());
				JOptionPane.showMessageDialog(null, "Succesfully Logged In. Gamme Starting", "Succes", JOptionPane.INFORMATION_MESSAGE, null);
				Level1Frame level1Frame = new Level1Frame(u);
				GuiController.changeFrame(LoginFrame.this, level1Frame);
			}
		});
		btnLogin.setBounds(104, 145, 114, 17);
		contentPane.add(btnLogin);
		
		
		JLabel lblChooseCharacter = new JLabel("Character:");
		lblChooseCharacter.setBounds(263, 145, 81, 17);
		contentPane.add(lblChooseCharacter);
		
		JRadioButton rdnFemale = new JRadioButton("");
		buttonGroup.add(rdnFemale);
		rdnFemale.setBounds(339, 145, 21, 25);
		contentPane.add(rdnFemale);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/female.png")));
		lblNewLabel.setBounds(355, 145, 60, 80);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/male1.png")));
		lblNewLabel_1.setBounds(427, 145, 60, 80);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/male2.png")));
		lblNewLabel_1_1.setBounds(499, 145, 60, 80);
		contentPane.add(lblNewLabel_1_1);
		
		JRadioButton rdnMale1 = new JRadioButton("");
		buttonGroup.add(rdnMale1);
		rdnMale1.setBounds(414, 145, 21, 25);
		contentPane.add(rdnMale1);
		
		JRadioButton rdnMale2 = new JRadioButton("");
		buttonGroup.add(rdnMale2);
		rdnMale2.setBounds(485, 145, 21, 25);
		contentPane.add(rdnMale2);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			/**
			 * <p>Validates and register user by {@link gui.GuiController#registerUser(User)} </p>
			 * @param arg0
			 */
			public void actionPerformed(ActionEvent arg0) {
				String username = regUserNameField.getText();
				String password = String.valueOf(regPasswordField.getPassword());
				String chr = "";
				if(username.length() == 0) {
					JOptionPane.showMessageDialog(null, "Username Cannot be Empty", "Error", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				if(GuiController.isUsernameTaken(username)) {
					JOptionPane.showMessageDialog(null, "This Username Has Taken", "Error", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				if(password.length() == 0) {
					JOptionPane.showMessageDialog(null, "Password Cannot be Empty", "Error", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				if(!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.*\\W).{5,20}$")) { // password validation regex for above description.
					JOptionPane.showMessageDialog(null, "Password is not matching the conditions\nHere is the conditions:\nLength must be between 5 and 20.\nMust contain an uppercase letter.\nMust contain lower case letter.\nMust contain digit.\nMUST NOT contain a special character other than '_'.", "Error", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				if(rdnFemale.isSelected()) {
					chr = "female.png";
				}
				if(rdnMale1.isSelected()) {
					chr = "male1.png";
				}
				if(rdnMale2.isSelected()) {
					chr = "male2.png";
				}
				if(chr.length() == 0) {
					JOptionPane.showMessageDialog(null, "You Should Choose a Character", "Error", JOptionPane.WARNING_MESSAGE, null);
					return;
				}
				GuiController.registerUser(new User(username,password,chr));
				regUserNameField.setText("");
				regPasswordField.setText("");
				GuiController.addLogLine("New user created with the name: "+username);
				JOptionPane.showMessageDialog(null, "User Created", "Succes", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		btnRegister.setBounds(355, 237, 114, 17);
		contentPane.add(btnRegister);
		
		JButton btnNewButton = new JButton("Show High Scores");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scoreFrame = new HighScoreFrame();
				scoreFrame.setVisible(true);
			}
		});
		btnNewButton.setBounds(30, 232, 188, 27);
		contentPane.add(btnNewButton);
		repaint();
		
		ArrayList<String> slQuestions = new ArrayList<String>();
		ArrayList<String> taQuestions = new ArrayList<String>();
		ArrayList<String> profQuestions = new ArrayList<String>();
		ArrayList<String> slInfos = new ArrayList<String>();
		ArrayList<String> taInfos = new ArrayList<String>();
		ArrayList<String> profInfos = new ArrayList<String>();
		// Retrieving the question and infos 
		try{
			GuiController.parseData(slQuestions, taQuestions, profQuestions, "questions.txt");
		}
		catch (CorruptedFileException e) {
			JOptionPane.showMessageDialog(null, "question.txt file is corrupted. Exiting the game", "Fatal Error", JOptionPane.WARNING_MESSAGE, null);
			System.exit(1);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "question.txt file is missing. Exiting the game", "Fatal Error", JOptionPane.WARNING_MESSAGE, null);
			System.exit(1);
		}
		try{
			GuiController.parseData(slInfos, taInfos, profInfos, "infos.txt");
		}
		catch (CorruptedFileException e) {
			JOptionPane.showMessageDialog(null, "infos.txt file is corrupted. Exiting the game", "Fatal Error", JOptionPane.WARNING_MESSAGE, null);
			System.exit(1);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "infos.txt file is missing. Exiting the game", "Fatal Error", JOptionPane.WARNING_MESSAGE, null);
			System.exit(1);
		}
		LevelFrame.setArrays(slQuestions, taQuestions, profQuestions, slInfos, taInfos, profInfos);
	}
	
	/**
	 * Draws a line between login and register part.
	 */
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.BLACK);
		g.drawLine(250, 0, 250, 310);
	}
}
