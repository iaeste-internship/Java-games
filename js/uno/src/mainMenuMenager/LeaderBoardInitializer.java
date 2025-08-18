package mainMenuMenager;

import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Class that initializes leaderboard ranking for main menu panel. 
 * 
 * @author Aykut Bir
 * @since 04/05/2024
 * 
 */

public class LeaderBoardInitializer {
	
	/**
	 * Fetches all players and their points from files, using this pair initializes a ranking based on points.
	 * (File: "src/files/leaderboardInfo.txt")
	 * 
	 * Utilizes three data structures:
	 *  -LinkedList to sort unsorted list of players based on their points.
	 * 	-LinkedHashMap to preserve insertion order of players.
	 * 	-Comparable to sort LeaderBoardPlayer objects based on their points.
	 * 
	 * @see LeaderBoardPlayer :to see implementation details of Comparable.
	 * @see MainMenuScreen :to see GUI leaderboard implementation using returned ranking.
	 * 
	 * @return ranking :Map<String,Intiger>, playername-points as key-value pairing to initialize custom leaderboard blocks.
	 * 
	 */
	
	public static Map<String, Integer> initializeLeaderBoard() {
		
		Map<String, Integer> ranking = new LinkedHashMap<>();
		List<LeaderBoardPlayer> list = new LinkedList<>();
		
		try {
			Scanner leaderBoard = new Scanner(Paths.get("src/files/leaderboardInfo.txt")); 
			while (leaderBoard.hasNextLine()) {
				String[] line = leaderBoard.nextLine().split(" ");
				list.add(new LeaderBoardPlayer(line[0], Integer.parseInt(line[1])));
			}
			list.sort(null);
			for (LeaderBoardPlayer player : list) {
				ranking.put(player.getName(), player.getPoints());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ranking;
	}
}
