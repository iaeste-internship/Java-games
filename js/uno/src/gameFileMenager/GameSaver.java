package gameFileMenager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

import gameSessionMenager.Card;
import gameSessionMenager.GameSession;
import gameSessionMenager.Player;

/**
 * Class that menages files upon new game creation and game save.
 * 
 * @author Aykut Bir
 * @since 13/05/2024
 * 
 */

public class GameSaver {
		
	/**
	 * Constructor
	 */
	
	public GameSaver() {
		
	}
	
	/**
	 * Writes the new playername - session name pair to files
	 * Invoked upon new session creation
	 * 
	 * (File: "src/filesGameSaves/allGames.txt")
	 * 
	 * @param playerName :String
	 * @param gameName	:String
	 * 
	 * @see NewGameScreen
	 */
	
	public static void newGameEntry(String playerName, String gameName) {
		String data = "";
		
		try	(Scanner fileScanner = new Scanner(Paths.get("src/filesGameSaves/allGames.txt"))) {
			while (fileScanner.hasNextLine()) {
				data = data.concat(String.format("%s%n", fileScanner.nextLine()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		data = data.concat(String.format("%s %s%n", playerName, gameName));
		
		try (Formatter output = new Formatter("src/filesGameSaves/allGames.txt")) {
			output.format("%s", data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Writes saved playername - session name pair to files.
	 * Invoked upon saving a session that was not saved before.
	 * 
	 * (File: "src/filesGameSaves/allSavedGames.txt")
	 * 
	 * @param playername :String
	 * @param gameName :String
	 * 
	 * @see ExitGameScreen
	 * 
	 */
	public static void newGameLoadEntry(String playername, String gameName) {
		
		String data = "";

		try	(Scanner fileScanner = new Scanner(Paths.get("src/filesGameSaves/allSavedGames.txt"))) {
			while (fileScanner.hasNextLine()) {
				data = data.concat(String.format("%s%n", fileScanner.nextLine()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (!data.contains(gameName)) {
			data = data.concat(String.format("%s %s%n", playername, gameName));
			try (Formatter output = new Formatter("src/filesGameSaves/allSavedGames.txt")) {
				output.format("%s", data);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 
	 * Writes session spesific save file using GameSession object
	 * Iterates through deck,draw piles as well as player hands to write each card to the files
	 * 
	 * 
	 * @param gameSession :GameSession object that was played and is being saved.
	 * 
	 * @see ExitGameScreen
	 */
	public static void writeLoadFile(GameSession gameSession) {
		String data = "";
		String file = "src/filesGameSaves/" + gameSession.getSessionName() + ".txt";
		data = data.concat(String.format("NumPlayer %d%n", gameSession.getNumberOfPlayers()));
		for (int i = 0 ; i < gameSession.getNumberOfPlayers(); i++) {
			data = data.concat(String.format("%d ", i));
			for (Card card : gameSession.getAllPlayers()[i].getHand()) {
				data = data.concat(String.format("%s ",card.getName()));
			}
			data = data.concat(String.format("%n"));
			
		}
		data = data.concat("DiscardPile ");
		for (Card card : gameSession.getDiscardPile()) {
			data = data.concat(String.format("%s ",card.getName()));
		}
		data = data.concat(String.format("%n"));
		data = data.concat("DrawPile ");
		for (Card card : gameSession.getDrawPile()) {
			data = data.concat(String.format("%s ",card.getName()));
		}
		
		data = data.concat(String.format("%n"));
		data = data.concat(String.format("Direction %s",gameSession.getDirection()));
		
		try (Formatter output = new Formatter(file)){
			output.format("%s",data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
