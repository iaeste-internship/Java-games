package guiMainMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import gameFileMenager.GameLoader;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;

/**
 * Screen that displays saved game sessions, that user can re-join to saved points.
 * Each saved games are user-spesific, and each save file stored in game session-spesific save file.
 * 
 * @author Aykut Bir
 * @since 12/05/2024
 * 
 */

public class LoadGameScreen extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Fetches all saved games that user can re-join using file:
	 * (File: "src/filesGameSaves/allSavedGames.txt")
	 * 
	 * Displays those games on a panel that user can select by clicking on them.
	 * 
	 * @param owner :MainMenuScreen 
	 * @param modal :boolean, until this panel is closed user cannot interact with MainMenuScreen
	 * @param playername :String, logged user's username to fetch user spesific player logs
	 * 
	 * @see GameLoader :to see how games are loaded, after user selects which game to be loaded.
	 * 
	 */
	
	public LoadGameScreen(MainMenuScreen owner, boolean modal, String playername) {
		super(owner, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoadGameScreen.class.getResource("/img/unoBack.PNG")));
		setTitle("Load a Game");
		setResizable(false);
		setBounds(100, 100, 444, 394);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.scrollbar);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Load a Game");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(68, 10, 298, 44);
		contentPanel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(68, 64, 298, 271);
		contentPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 102));
		scrollPane.setViewportView(panel);
		panel.setPreferredSize(new Dimension(298, 2000));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ArrayList<String> saveFiles = new ArrayList<>(); 
		try (Scanner file = new Scanner(Paths.get("src/filesGameSaves/allSavedGames.txt"))){
			while (file.hasNextLine()) {
				String[] line = file.nextLine().split(" ");
				if (line[0].equals(playername)) {
					saveFiles.add(line[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String sessionName : saveFiles) {
			JButton btnNewButton = new JButton(sessionName);
			btnNewButton.setPreferredSize(new Dimension(298, 30));
			panel.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GameLoader.LoadGame(sessionName, playername);
					dispose();
					owner.dispose();
				}
			});
		}	
	}
}
