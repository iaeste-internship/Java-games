package authenticationMenager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *  Implements a class to check validity of user authentication inputs from login screen
 *  
 *	@author Aykut Bir
 *	@since 30/04/2024
 */

public class UserLogin {
	
	/**
	 * Method that checks if username-password pair exists in the files.
	 * (File: src/files/userLoginInfo.txt)
	 * 
	 * @param usernameInput :User's username input from login screen
	 * @param passwordInput :User's password input from login screen
	 * @return boolean :true if username and password pair exist in the files, false otherwise 
	 * 
	 * @see LoginScreen :see login screen frame to see detailed implementation.
	 */
	
	public static boolean userLogger(String usernameInput, String passwordInput) {
		boolean valid = false;
		
		try (Scanner input = new Scanner(Paths.get("src/files/userLoginInfo.txt"))){
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] userInfo = line.split(" ");
				if ((userInfo[0].equals(usernameInput)) && (userInfo[1].equals(passwordInput))) {
					valid = true;
				}
				
			}
			
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
		
		return valid;

	}
}
