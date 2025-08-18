package guiGameSession;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * Displays the raund log screen for players attention.
 * 
 * @author Aykut Bir
 * @since 10/05/2024
 */
public class RoundLogScreen extends JDialog {
	
	/**
	 * Utilizes text area and log fetched from round logger object.
	 * 
	 * @param owner :JFrame, game screen
	 * @param modal 
	 * @param log :String, log fetched from round logger object.
	 */

	public RoundLogScreen(JFrame owner, boolean modal, String log) {
		super(owner, modal);
		setBounds(100, 100, 671, 639);
		getContentPane().setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 67, 637, 513);
			getContentPane().add(scrollPane);
			
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			scrollPane.setViewportView(textArea);
			textArea.setPreferredSize(new Dimension(620, 5000));
			textArea.setText(log);
		}
		{
			JLabel lblNewLabel = new JLabel("Round Log");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(10, 10, 637, 44);
			getContentPane().add(lblNewLabel);
		}
	}

}
