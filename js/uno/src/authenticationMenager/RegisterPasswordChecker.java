package authenticationMenager;

import exceptions.CharacterException;
import exceptions.LengthException;
import exceptions.WhitespaceException;

/**
 * Implements a class to check validity of written password on register screen.
 * 
 * @author Aykut Bir
 * @since 30/04/2024
 * 
 */
public class RegisterPasswordChecker {

	/**
	 * Checks user's password input from password text field of register screen.
	 * 
	 * Valid password must contain at least one type of every given characters: digit, uppercase and lowercase character.
	 * Valid password must be longer than 5 characters.
	 * Valid password must NOT contain any whitespace character.
	 * 
	 * Method throws following InputException(s) depending on user's invalid input, to later caught to display corresponding error screen.
	 * 
	 *  @param userInput :User's password input from register screen.
	 *  @return void
	 *  
	 * 	@throws LengthException :if the password is not longer than 5 characters.
	 * 	@throws WhitespaceException :if the password contains whitespace.
	 * 	@throws CharacterException :if the password does not contain the required types of characters.
	 *  
	 *  @see RegisterScreen :for detailed utilization.
	 *  
	 */
	public static void check(String userInput) throws LengthException, WhitespaceException, CharacterException {
		
			
		if (userInput.length() <= 5) {
				throw new LengthException();
		} else if (userInput.contains(" ")){
				throw new WhitespaceException();
		} else if (hasSpecials(userInput) == false){
				throw new CharacterException();
		   }
	}
	
	/**
	 * Determines if the user's password input contains at least one digit, one uppercase letter, and one lowercase letter.
	 * 
	 * This method checks for the presence of three specific types of characters required for the password:
	 * - At least one digit.
	 * - At least one uppercase letter.
	 * - At least one lowercase letter.
	 * 
	 * @param input User's password input.
	 * @return true if all three character types are present in the password, false otherwise.
	 */
	private static boolean hasSpecials(String input) {
		boolean Dflag = false;
		boolean Uflag = false;
		boolean Lflag = false;
		
		for (char c : input.toCharArray()) {
			Dflag = (Character.isDigit(c) ? true : Dflag);
			Lflag = (Character.isLowerCase(c) ? true : Lflag);
			Uflag = (Character.isUpperCase(c) ? true : Uflag);
		}	
		
		return (Dflag && Uflag && Lflag);
	}	
	
}


