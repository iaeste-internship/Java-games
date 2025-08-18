package gameFileMenager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Class that menages game files after a game session is concluded or exited.
 * 
 * @author Aykut Bir
 * @since 13/05/2024
 * 
 */
public class EndgameFileMenager {
	
	/**
	 * Constructor
	 */
	public EndgameFileMenager() {
		
	}
	
	/**
	 * Writes or updates session spesific game history file.
	 * If game exists it adds new game log onto existing game log
	 * If game does not exist it creates the file and writes the game log
	 * 
	 * @param sessionName :String, used to create or reach the spesific file
	 * @param logger :GameHistoryLogger, used to fetch log of game session
	 * 
	 * @see GameHistoryLogger, ExitGameScreen
	 */
	
	public static void writeGameHistory(String sessionName, GameHistoryLogger logger) {
		
		String filePath = "src/filesGameHistory/" + sessionName + ".txt";
		File file = new File(filePath);
		
		if (file.exists()) {
			
			String data = "";
			
			try (Scanner content = new Scanner(Paths.get(filePath))){
				while (content.hasNextLine()) {
					data = data.concat(String.format("%s%n",content.nextLine()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			data = data.concat(logger.getGameLog());
			
			try (Formatter output = new Formatter(filePath)) {
				output.format("%s", data);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		} else {
			try (Formatter output = new Formatter(filePath)) {
				output.format("%s", logger.getGameLog());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	 * Updates the player statistics and leaderboard statistics after a game is concluded.
	 * 
	 * 
	 * @param playername :String, used to reach user profile file, and leaderboard info.
	 * @param isWinner :boolean, determines which datas to be updated.
	 * @param points :int, added to total player points if player wins the game.
	 * 
	 * @see GameScreen - listenGameEnded(boolean, String) method
	 */
	
	public static void updatePlayerStats(String playername, boolean isWinner, int points) {
		
		String leaderboardFile = "src/files/leaderboardInfo.txt";
		String playerFile = "src/files/" + playername + ".txt";
		
		String[] playerStatistics = new String[5];
		
		String leaderboardEntry = "";
		String playerFileEntry = "";
	
		try (Scanner playerInfo = new Scanner(Paths.get(playerFile))){
			playerStatistics = playerInfo.nextLine().split(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		playerStatistics[3] = String.format("%d", Integer.parseInt(playerStatistics[3]) + 1);
		if (isWinner) {
			playerStatistics[1] = String.format("%d", Integer.parseInt(playerStatistics[1]) + 1);
			playerStatistics[4] = String.format("%d", Integer.parseInt(playerStatistics[4]) + points);
		} else {
			playerStatistics[2] = String.format("%d", Integer.parseInt(playerStatistics[2]) + 1);
		}
		
		leaderboardEntry = String.format("%s %s%n", playerStatistics[0], playerStatistics[4]);
		playerFileEntry = String.format("%s %s %s %s %s", playerStatistics[0], playerStatistics[1], playerStatistics[2], playerStatistics[3], playerStatistics[4]);
		
		try (Formatter outputToPlayerFile = new Formatter(playerFile)){
			outputToPlayerFile.format("%s%n", playerFileEntry);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		try (Scanner leaderboardInfo = new Scanner(Paths.get(leaderboardFile))){
			while (leaderboardInfo.hasNextLine()) {
				String line = leaderboardInfo.nextLine();
				if (!line.contains(playername)) {
					leaderboardEntry = leaderboardEntry.concat(String.format("%s%n",line));
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (Formatter outputToLeaderboardFile = new Formatter(leaderboardFile)){
			outputToLeaderboardFile.format("%s", leaderboardEntry);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
