package guiMainMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

/**
 * 
 * Screen that displays messages upon wrong user inputs, during new game session creation
 * 
 * @author Aykut Bir
 * @since 12/05/2024
 * 
 */

public class MainMenuError extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	/**
	 * 
	 * 
	 * @param jDialog : NewGameScreen
	 * @param modal :boolean, until this panel is closed user cannot interact with NewGameScreen nor MainMenuScreen
	 * @param errorMessage :String, message that is displayed.
	 * 
	 * @see NewGameScreen :to see spesific error calls
	 * 
	 */
	
	public MainMenuError(JDialog jDialog, boolean modal,String errorMessage) {
		super(jDialog, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuError.class.getResource("/img/unoWildColor.PNG")));
		setResizable(false);
		setTitle("Info");
		setFont(new Font("Dialog", Font.PLAIN, 10));
		setBounds(100, 100, 749, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel(errorMessage);
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
