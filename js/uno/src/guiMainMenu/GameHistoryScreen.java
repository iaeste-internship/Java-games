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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gameFileMenager.GameLoader;
import java.awt.Toolkit;
	
/**
 * Screen that displays all possible game-logs that user can choose to display
 * From panel user can select a game that user played and wrote its log.
 * 
 * @author Aykut Bir
 * @since 12/05/2024
 * 
 */
	public class GameHistoryScreen extends JDialog {
		
		private final JPanel contentPanel = new JPanel();
		private MainMenuScreen owner;

		/**
		 * Using files, constructor fetches user spesific all game history files. Displays those files 
		 * name to let player choose one from them. 
		 * (File: "src/filesGameSaves/allGames.txt")
		 * 
		 * @param owner :MainMenuScreen 
		 * @param modal :boolean, until this panel is closed user cannot interact with MainMenuScreen
		 * @param playername :String, logged user's username to fetch user spesific player logs
		 * 
		 * @see MainMenuScreen :to see initialization
		 * @see GameLogScreen :to see panel that displays selected game log by the player.
		 * 
		 */
	public GameHistoryScreen(MainMenuScreen owner, boolean modal, String playername) {
		super(owner, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameHistoryScreen.class.getResource("/img/unoBack.PNG")));
		this.owner = owner;
		setTitle("Game Logs");
		setResizable(false);
		setBounds(100, 100, 444, 394);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.scrollbar);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose a Game Log");
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
		
		ArrayList<String> historyFiles = new ArrayList<>(); 
		try (Scanner file = new Scanner(Paths.get("src/filesGameSaves/allGames.txt"))){
			while (file.hasNextLine()) {
				String[] line = file.nextLine().split(" ");
				if (line[0].equals(playername)) {
					historyFiles.add(line[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String sessionName : historyFiles) {
			JButton btnNewButton = new JButton(sessionName);
			btnNewButton.setPreferredSize(new Dimension(298, 30));
			panel.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String log = "";
						try (Scanner file = new Scanner(Paths.get("src/filesGameHistory/"+ btnNewButton.getText() +".txt"))){
							while (file.hasNextLine()) {
								log = log.concat(String.format("%s%n", file.nextLine()));
								}
						} catch (IOException ex) {
							ex.printStackTrace();
						}
						dispose();
						GameLogScreen dialog = new GameLogScreen(owner, true, log, btnNewButton.getText());
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
		}	
	}

}
