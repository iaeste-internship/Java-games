package guiAuthentication;

import javax.swing.JButton;
import javax.swing.JDialog;

import exceptions.*;

import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

import authenticationMenager.RegisterPasswordChecker;
import authenticationMenager.RegisterUsernameChecker;
import authenticationMenager.UserRegister;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * Register sub-panel screen. User can interact this screen to register the game.
 * 
 * @author Aykut Bir
 * @since 01/05/2024
 * 
 */

public class RegisterScreen extends JDialog {

	private JPasswordField passwordField;
	private JTextField usernameField;
	
	/**
	 * Constructor, displays register screen. User can interact with register screen to register to the game.
	 * 
	 * Invalid inputs will display info messages to screen.
	 * Upon valid input user is registered to the game.
	 * 
	 * Utilizes register username and password checkers to check validity of user inputs.
	 * Utilizes registerer to  register user to the game files after valid inputs.
	 * 
	 * @param owner :LoginAndRegister, main panel. User cannot interact with main panel until Register Screen is closed
	 * @param modal :boolean, used in parent class' constructor to disable user interaction with owner panel
	 * 
	 * @see authenticationMenager :package to see authentication related methods.
	 * @see LoginAndRegisterScreen :main screen to see how register screen is activated.
	 */
	
	public RegisterScreen(LoginAndRegisterScreen owner, boolean modal) {
	    super(owner, modal);
	    setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Register");
		getContentPane().setBackground(new Color(211, 211, 211));
		setBackground(SystemColor.menu);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterScreen.class.getResource("/img/uno.png")));
		setBounds(100, 100, 450, 578);
		getContentPane().setLayout(null);
		{
			passwordField = new JPasswordField();
			passwordField.setBackground(new Color(132, 193, 255));
			passwordField.setBounds(30, 369, 377, 52);
			getContentPane().add(passwordField);
		}
		{
			JLabel lblNewLabel = new JLabel("Password:");
			lblNewLabel.setForeground(new Color(30, 144, 255));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(30, 340, 97, 35);
			getContentPane().add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("REGISTER");
			lblNewLabel_1.setForeground(SystemColor.textHighlight);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 49));
			lblNewLabel_1.setBounds(30, 89, 377, 102);
			getContentPane().add(lblNewLabel_1);
		}
		{
			usernameField = new JTextField();
			usernameField.setBackground(new Color(132, 193, 255));
			usernameField.setBounds(30, 276, 377, 52);
			getContentPane().add(usernameField);
			usernameField.setColumns(10);
		}
		{
			JLabel lblUserName = new JLabel("User Name");
			lblUserName.setForeground(new Color(30, 144, 255));
			lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblUserName.setBounds(30, 247, 97, 35);
			getContentPane().add(lblUserName);
		}
		{
			JButton btnNewButton = new JButton("CANCEL");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
			btnNewButton.setBackground(new Color(128, 128, 128));
			btnNewButton.setForeground(new Color(255, 0, 0));
			btnNewButton.setBounds(166, 431, 116, 44);
			getContentPane().add(btnNewButton);
		}
		{
			JButton btnCreate = new JButton("CREATE");
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean valid = true;
					String usernameInput = usernameField.getText();
					char[] passwordCharArray = passwordField.getPassword();
	                String passwordInput = new String(passwordCharArray);
	                
	                try {
	                	RegisterUsernameChecker.check(usernameInput);
	                } catch (LengthException ex){
	                	valid = false;
	                	activateRegisterInfoScreenPanel("Username must be 8-15 characters long.");
	                } catch (WhitespaceException ex) {
	                	valid = false;
	                	activateRegisterInfoScreenPanel("Username must not contain whitespace character.");
	                } catch (SimilarityException ex) {
	                	valid = false;
	                	activateRegisterInfoScreenPanel("This username already exits.");
	                }
	                
	                try {
	                	RegisterPasswordChecker.check(passwordInput);
	                } catch (LengthException ex) {
	                	valid = false;
	                	activateRegisterInfoScreenPanel("Password must be longer than 5 characters long.");
	                } catch (WhitespaceException ex) {
	                	valid = false;
	                	activateRegisterInfoScreenPanel("Password must not contain whitespace character.");
	                } catch (CharacterException ex) {
	                	valid = false;
	                	activateRegisterInfoScreenPanel("Password must contain a digit and, uppercase and lowercase characters.");
	                }
	                
	                if (valid) {
	                	UserRegister.userRegisterer(usernameInput, passwordInput);
	                	activateRegisterInfoScreenPanel(String.format("Player %s successfully registered", usernameInput));
	                	dispose();
	                }
	                
				}
			});
			btnCreate.setBackground(new Color(128, 128, 128));
			btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 19));
			btnCreate.setBounds(291, 431, 116, 44);
			getContentPane().add(btnCreate);
		}
	}
	
	/**
	 * Activates info panel about register screen. Can display:
	 * 	-Successful registeration
	 *  -Info about invalid inputs about user
	 *  
	 * @param message :String, message that is used while activating info panel.
	 * 
	 * @see AuthenticationInfoScreen :to see how info-messages are utilized.
	 */
	private void activateRegisterInfoScreenPanel(String message) {
		try {
			AuthenticationInfoScreen dialog = new AuthenticationInfoScreen(this, true, message);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
