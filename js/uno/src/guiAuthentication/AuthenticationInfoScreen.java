package guiAuthentication;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * Screen that displays info-related messages to user, upon user interaction.
 * 
 * @author Aykut Bir
 * @since 01/05/2024
 * 
 */

public class AuthenticationInfoScreen extends JDialog {


	private final JPanel contentPanel = new JPanel();
	
	/**
	 * 
	 * Constructor of info-message panel. Displays info message.
	 * 
	 * @param jDialog :Owner of this message panel, user cannot interact with owner until info-screen is closed
	 * @param modal :boolean, used in parent class' constructor to disable user interaction with owner panel
	 * @param message :String, displayed message on the info screen panel
	 * 
	 * @see LoginScreen :See login related info messages.
	 * @see RegisterScreen :See register related info messages.
	 */
	public AuthenticationInfoScreen(JDialog jDialog, boolean modal, String message) {
		
		super(jDialog, modal);
		setResizable(false);
		setTitle("Info");
		setFont(new Font("Dialog", Font.PLAIN, 10));
		setBounds(100, 100, 749, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel(message);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(10, 10, 715, 63);
			contentPanel.add(lblNewLabel);
		}
		
		JButton btnNewButton = new JButton("GOT IT!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(595, 83, 130, 36);
		contentPanel.add(btnNewButton);
	}
}
