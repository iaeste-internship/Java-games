package guiAuthentication;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import authenticationMenager.LoginListener;
import guiMainMenu.MainMenuScreen;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;


/**
 * 
 * Main screen after the game is launched. User can interact this screen to open login or register screens.
 * Listens successful logins through login listener interface.
 * 
 * @author Aykut Bir
 * @since 01/05/2024
 * 
 */


public class LoginAndRegisterScreen extends JFrame implements LoginListener{

	private JPanel contentPane;
	
	/**
	 * Constructor, displays first frame of the game and main screen of authentication.
	 */

	public LoginAndRegisterScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginAndRegisterScreen.class.getResource("/img/uno.png")));
		setTitle("UNO The Game");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 630);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogn = new JButton("LOGIN");
		
		btnLogn.setForeground(SystemColor.textHighlight);
		btnLogn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateLoginScreenPanel();
				
			}
		});
		btnLogn.setBackground(UIManager.getColor("Button.darkShadow"));
		btnLogn.setFont(new Font("Segoe Script", Font.BOLD, 36));
		btnLogn.setBounds(110, 188, 442, 90);
		contentPane.add(btnLogn);
		
		JButton btnRegster = new JButton("REGISTER");
		btnRegster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activateRegisterScreenPanel();
			}
		});
		
		btnRegster.setForeground(SystemColor.textHighlight);
		btnRegster.setBackground(UIManager.getColor("Button.darkShadow"));
		btnRegster.setFont(new Font("Segoe Script", Font.BOLD, 36));
		btnRegster.setBounds(110, 288, 442, 90);
		contentPane.add(btnRegster);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 10, 629, 573);
		lblNewLabel.setIcon(new ImageIcon(LoginAndRegisterScreen.class.getResource("/img/uno.png")));
		contentPane.add(lblNewLabel);
		
	}
	
	/**
	 * Activates register screen panel.
	 * 
	 * @see RegisterScreen
	 */
	 private void activateRegisterScreenPanel() {
		 RegisterScreen dialog = new RegisterScreen(this, true);
		 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 dialog.setVisible(true);
	    }
	 
	 /**
	  * Activates login screen panel.
	  * 
	  * @see LoginScreen
	  */
	 private void activateLoginScreenPanel() {
		 LoginScreen dialog = new LoginScreen(this, true);
		 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 dialog.setVisible(true);
	    }
	 
	 /**
	  * Listener method implemented from LoginListener interface.
	  * Listens successful login and closes authentication main frame and opens main menu screen of the game.
	  * Utilized in LoginScreen sub-panel
	  * 
	  * @param userName :String, username to be displayed in the main menu screen
	  * 
	  * @see LoginListener 
	  * @see LoginScreen :to see implementation details
	  * @see MainMenuScreen :to see main menu screen details, utilizing username.
	  * 
	  */
	 @Override
	 public void onSuccessfulLogin(String userName) {
	 	dispose();
	 	try {
			MainMenuScreen frame = new MainMenuScreen(userName);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 	
	 }
	 
	 
}
