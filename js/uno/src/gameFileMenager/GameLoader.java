package gameFileMenager;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import gameSessionMenager.Card;
import gameSessionMenager.Deck;
import gameSessionMenager.GameSession;
import gameSessionMenager.WildCard;
import guiGameSession.GameScreen;

/**
 * Class that re-initializes saved game using game-spesific game file.
 * 
 * @author Aykut Bir
 * @since 13/05/2024
 * 
 */

public class GameLoader {
	
	private static Map<String, Card> cardMap = new Deck().getCardMap();
	
	/**
	 * Initializes saved game using helper initializeGame method
	 * 
	 * 
	 * @param sessionName :String, saved session's name
	 * @param userName :String, user that saved that session
	 * 
	 * @see LoadGameScreen
	 * 
	 */
	
	public static void LoadGame(String sessionName, String userName) {
		
		String filename = "src/filesGameSaves/" + sessionName + ".txt";
		int numberOfPlayers = 0;
		
		try (Scanner file = new Scanner(Paths.get(filename))){
			numberOfPlayers = Integer.parseInt(file.nextLine().split(" ")[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initializeGame(numberOfPlayers, sessionName, userName, filename);
		
		
	}
	
	/**
	 * Initializes saved game using its save file
	 * First sets default GameSession and GameScreen objects
	 * Later sets corresponding GameSession fields using their setters.
	 * Lastly updates the GameScreen
	 * 
	 * @param numberOfPlayers :int, Used to set default GameScreen and GameSession.
	 * @param sessionName :String, Used to set default GameScreen and GameSession.
	 * @param userName :String, Used to set default GameScreen and GameSession.
	 * @param filename :String, filename of spesific save file to be used to set corresponding fields.
	 * 
	 * @see GameSaver
	 * @see GameSession
	 */
	
	private static void initializeGame(int numberOfPlayers, String sessionName, String userName, String filename) {
		
		try {
			GameSession gameSession = new GameSession(numberOfPlayers, sessionName, userName);
			GameScreen frame = new GameScreen(gameSession, sessionName, userName);
			gameSession.setGameScreen(frame);
			
			try (Scanner file = new Scanner(Paths.get(filename))){
				file.nextLine();
				for (int i = 0 ; i < numberOfPlayers ; i++) {
					String[] cardNames = file.nextLine().split(" ");
					
					ArrayList<Card> playerHand = new ArrayList<>();
					for (int j = 1 ; j < cardNames.length ; j++) {
						playerHand.add(Deck.copyCard(cardMap.get(cardNames[j])));
					}
					
					gameSession.getAllPlayers()[i].setHand(playerHand);
					frame.listenCardCounts(gameSession.getAllPlayers()[i]);
				}
				while (file.hasNext()) {
					String line = file.nextLine();
					if (line.contains("DrawPile")) {
						String[] cardNames = line.split(" ");
						ArrayList<Card> drawPile = new ArrayList<>();
						for (int j = 1 ; j < cardNames.length ; j++) {
							drawPile.add(Deck.copyCard(cardMap.get(cardNames[j])));
						}
						gameSession.setDrawPile(drawPile);
						frame.listenDrawDeck();
						
					} else if (line.contains("DiscardPile")){
						String[] cardNames = line.split(" ");
						ArrayList<Card> discardPile = new ArrayList<>();
						for (int j = 1 ; j < cardNames.length ; j++) {
							discardPile.add(Deck.copyCard(cardMap.get(cardNames[j])));
						}
						gameSession.setDiscardPile(discardPile);
						
					} else if (line.contains("Direction Reverse")){
						frame.listenDirection();
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			frame.listenDiscardPile(gameSession.getLastDiscardedCard());
			frame.initiatePlayerMove(gameSession.getLastDiscardedCard());
			
			if (gameSession.getLastDiscardedCard().getValue().contains("Wild")) {
				((WildCard) gameSession.getLastDiscardedCard()).setSelectedColor(null);
			}
			
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
