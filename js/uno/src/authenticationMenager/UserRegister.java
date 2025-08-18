package authenticationMenager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;

/**
 * Implements a class that menages files after successful user registeration.
 * 
 * @author Aykut Bir
 * @since 30/04/2024
 */

public class UserRegister {
	
	/**
	 * Method that uses username and password pair from user written on register screen:
	 * 
	 * -Using this pair, method appends new user info to "src/files/userLoginInfo.txt" (in format: "[username] [password]")
	 * 
	 * -Using username method creates new user spesific profile file named as "src/files/[username].txt". (in format "[username] [wins] [loses] [totalGamePlayed] [points]")
	 * 
	 * -Using username method appends new user's leaderboard info to "src/files/leaderboardInfo.txt" (in format "[username] [points]")
	 * 
	 * 
	 * All basic user statistics (wins, loses, total games played, and points) are set to 0 upon new user registeration.
	 * 
	 * 
	 * @param userName :User's valid username input from register screen.
	 * @param password :User's valid password input from register screen.
	 * @return void
	 * 
	 * @see RegisterScreen :to see detailed implementation.
	 */
	
	public static void userRegisterer(String userName, String password) {
		
		try (Scanner input = new Scanner(Paths.get("src/files/userLoginInfo.txt"))){
			
			String loginData = "";
			while (input.hasNextLine()) {
				String line = input.nextLine();
				loginData = loginData.concat(String.format("%s%n", line));
			}
			
			loginData = loginData.concat(String.format("%s %s",userName, password));
			
			try (Formatter output = new Formatter("src/files/userLoginInfo.txt")) {
				output.format("%s%n", loginData);
				
			} catch (SecurityException | FileNotFoundException | FormatterClosedException ex) {
		         ex.printStackTrace();
			}
		} catch (IOException ex) {
        	ex.printStackTrace();
		}
		
		
		
		String userTextFile = String.format("src/files/" + userName + ".txt");
		try (Formatter output = new Formatter(userTextFile)){ 
			
			output.format("%s 0 0 0 0", userName);
		} catch (SecurityException | FileNotFoundException | FormatterClosedException ex) {
	         ex.printStackTrace();
		}
		
		
		try (Scanner input = new Scanner(Paths.get("src/files/leaderboardInfo.txt"))){
			String leaderboardData = "";
			while (input.hasNextLine()) {
				String line = input.nextLine();
				leaderboardData = leaderboardData.concat(String.format("%s%n", line));
			}
			
			leaderboardData = leaderboardData.concat(String.format("%s 0",userName));
			
			try (Formatter output = new Formatter("src/files/leaderboardInfo.txt")) {
				output.format("%s%n", leaderboardData);
				
			} catch (SecurityException | FileNotFoundException | FormatterClosedException ex) {
		         ex.printStackTrace();
			}
			
		
		} catch (IOException ex) {
        	ex.printStackTrace();
	      
		}
		
	}

}
