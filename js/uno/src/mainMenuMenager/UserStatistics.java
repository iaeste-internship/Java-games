package mainMenuMenager;

import java.util.Scanner;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 * 
 * Class implementation to fetch users all statistics to display those statistics to
 * the UserStatisticsScreen
 * 
 * @author Aykut Bir
 * @since 04/05/2024
 * 
 */


public class UserStatistics {
	
	/**
	 * Fetches username spesific statistics from, user spesific text files.
	 * (File: "src/files/[username].txt")
	 * 
	 * Using data initializes an ArrayList that contains all basic and derived user information:
	 * (in order)
	 * 		- Username
	 * 		- Games Won
	 * 		- Games Lost
	 * 		- Total Games Played
	 * 		- Points
	 * 		- Average Points per Game
	 * 		- Win/Lose Ratio
	 * 
	 * 
	 * @param username :String, used to reach user spesific profile text file.
	 * @return userStatistics :ArrayList<String>, list of user statistics to be used.
	 * 
	 * @see UserStatisticsScreen :to see full utilization and displayment on GUI components.
	 * 
	 */
	
	
	public static ArrayList<String> getStatistics(String username) {
		
		String userTextFile = String.format("src/files/" + username + ".txt");
		ArrayList<String> userStatistics = new ArrayList<>();
		try (Scanner input = new Scanner(Paths.get(userTextFile))) { 
			while (input.hasNext()) {
				String statistic = input.next();
				userStatistics.add(statistic);
			}
				
		} catch (Exception ex) {
	         ex.printStackTrace();
		}
		
		if (!userStatistics.get(3).equals("0")) {
			float average = Float.parseFloat(userStatistics.get(4)) /  Float.parseFloat(userStatistics.get(3));
			userStatistics.add(String.format("%.2f", average)); 
		} else {
			userStatistics.add("0");
		}
		
		if (!userStatistics.get(2).equals("0")) {
			float wlRatio =  Float.parseFloat(userStatistics.get(1)) /  Float.parseFloat(userStatistics.get(2));
			userStatistics.add(String.format("%.2f", wlRatio)); 
		} else {
			userStatistics.add("0");
		}
		
				
		return userStatistics;
		
	}

}
