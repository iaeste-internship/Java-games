package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Consist of one non-editable table that contains users data.
 */
public class HighScoreFrame extends JFrame {
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public HighScoreFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 250, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 376, 148);
		contentPane.add(scrollPane);
		
		String[] cols = {"Username","Session","Score"};
		String[][] data = GuiController.getSortedUserScores(); // Retrieving scores
		table = new JTable(data,cols);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
	}

}
