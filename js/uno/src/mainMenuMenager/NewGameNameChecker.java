package mainMenuMenager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import exceptions.LengthException;
import exceptions.SimilarityException;
import exceptions.WhitespaceException;

/**
 * 
 * Class that checks validity of new game session's name.
 * 
 * @author Aykut Bir
 * @since 12/05/2024
 * 
 */

public class NewGameNameChecker {
	
	/**
	 * 
	 * Checks user's input for new game sessions name.
	 * Since sessions are written to a text file in format "[username] [session name]" 
	 * this method can throw 3 InputException later to be caught.
	 * 
	 * @param userInput :String, input that is written on NewGameScreen panel
	 * @throws LengthException :If user input is not between 5 to 25 characters
	 * @throws WhitespaceException :If user input contains whitespace character
	 * @throws SimilarityException :If user input exists in files
	 * 
	 * @see NewGameScreen :to see utilization
	 * 
	 */
	
	public static void check(String userInput) throws LengthException, WhitespaceException, SimilarityException {
		
		if (userInput.length() < 5) {
			throw new LengthException();
		} else if (userInput.length() > 25) {
			throw new LengthException();
		} else if (userInput.contains(" ")){
			throw new WhitespaceException();
		} else if (!similarityCheck(userInput)) {
			throw new SimilarityException();
		}
		
	}
	
	/**
	 * Helper method to check files to if game session name is already used.
	 * (File: "src/filesGameSaves/allGames.txt")
	 * 
	 * @param userInput :User's input to new game session's name
	 * @return boolean :true if session name is unique, false otherwise.
	 */
	
	private static boolean similarityCheck(String userInput) {
		boolean isValid = true;
		
		try (Scanner allGames = new Scanner(Paths.get("src/filesGameSaves/allGames.txt"))){
			
			while (allGames.hasNext()) {
				if (userInput.equals((allGames.nextLine().split(" "))[1])) {
					isValid = false;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isValid;
	}

}
