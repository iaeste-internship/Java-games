package authenticationMenager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import exceptions.CharacterException;
import exceptions.LengthException;
import exceptions.SimilarityException;
import exceptions.WhitespaceException;

/**
 * Implements a class to check validity of written username on register screen.
 * 
 * @author Aykut Bir
 * @since 30/04/2024
 * 
 */

public class RegisterUsernameChecker {
	
	/**
	 * Checks user's username input from username text field of register screen.
	 * 
	 * Valid username must be between 8 to 15 charaters long.
	 * Valid username must NOT contain any whitespace character.
	 * Valid username must NOT exist in the files, in other words every username must be unique.
	 * (File: src/files/userLoginInfo.txt)
	 * 
	 * Method throws following InputException(s) depending on user's invalid input, to later caught to display corresponding error screen.
	 * 
	 *  @param userInput :User's username input from register screen.
	 *  @return void
	 *  
	 * 	@throws LengthException :if the username is not between 8 to 15 characters.
	 * 	@throws WhitespaceException :if the username contains whitespace.
	 * 	@throws SimilarityException :if the username exists in the files.
	 *  
	 *  @see RegisterScreen :for detailed utilization.
	 *  
	 */

	public static void check (String userInput) throws LengthException, WhitespaceException, SimilarityException {
			
		if (userInput.length() < 8) {
			throw new LengthException();
		} else if (userInput.length() > 15) {
			throw new LengthException();
		} else if (userInput.contains(" ")){
			throw new WhitespaceException();
		} else {
			try (Scanner input = new Scanner(Paths.get("src/files/userLoginInfo.txt"))){
				while (input.hasNextLine()) {
					String line = input.nextLine();
					String[] userInfo = line.split(" ");
					if (userInfo[0].equals(userInput)) {
						throw new SimilarityException();
					}
					
				}
				
	        } catch (IOException ex) {
	        	ex.printStackTrace();
	        } 
		}
	}
	
}
