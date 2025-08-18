package guiAuthentication;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import authenticationMenager.LoginListener;
import authenticationMenager.UserLogin;

/**
 * 
 * Login sub-panel screen. User can interact this screen to login the game.
 * 
 * @author Aykut Bir
 * @since 01/05/2024
 * 
 */

public class LoginScreen extends JDialog {
	
	
	private JPasswordField passwordField;
	private JTextField usernameField;
	private LoginListener loginListener;
	
	/**
	 * Constructor, displays login screen. User can interact with login screen to login to the game.
	 * 
	 * Invalid inputs will display info messages to screen.
	 * Upon valid input user is logged in to the game, with corresponding user profile.
	 * 
	 * Utilizes login username and password checkers to check validity of user inputs.
	 * Utilizes logger to log user in to the game files after valid inputs.
	 * 
	 * @param owner :LoginAndRegister, main panel. User cannot interact with main panel until Login Screen is closed
	 * @param modal :boolean, used in parent class' constructor to disable user interaction with owner panel
	 * 
	 * @see authenticationMenager package to see authentication related methods.
	 * @see LoginAndRegisterScreen :main screen to see how login screen is activated.
	 */
	
	public LoginScreen(LoginAndRegisterScreen owner, boolean modal) {
		super(owner, modal);
		this.loginListener = owner;
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Login");
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
			JLabel lblNewLabel_1 = new JLabel("LOGIN");
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
			JButton btnCreate = new JButton("LOGIN");
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String usernameInput = usernameField.getText();
					char[] passwordCharArray = passwordField.getPassword();
	                String passwordInput = new String(passwordCharArray);
	                
	                if (UserLogin.userLogger(usernameInput, passwordInput)) {
	                	activateLoginInfoScreenPanel("User logged in successfully");
	                	dispose();
	                	loginListener.onSuccessfulLogin(usernameInput);
	                	
	                } else {
	                	activateLoginInfoScreenPanel("Wrong username or password is wrong. Please try again.");
	                	
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
	 * Activates info panel about login screen. Can display:
	 * 	-Successful logging in.
	 *  -Info about invalid input pair.
	 *  
	 * @param message :String, message that is used while activating info panel.
	 * 
	 * @see AuthenticationInfoScreen :to see how info-messages are utilized.
	 */
	
	private void activateLoginInfoScreenPanel(String message) {
		try {
			AuthenticationInfoScreen dialog = new AuthenticationInfoScreen(this, true, message);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
