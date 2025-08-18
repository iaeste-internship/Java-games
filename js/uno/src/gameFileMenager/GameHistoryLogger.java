package gameFileMenager;

/**
 * Class that logs the actions history of entire game
 * 
 * @author Aykut Bir
 * @since 13/05/2024
 */

public class GameHistoryLogger {
	
	private String gameLog = "";
	
	/**
	 * Constructor
	 */
	
	public GameHistoryLogger() {
		
	}
	
	/**
	 * Logs every event that happens during the game to gameLog field 
	 * @param event :String, event that is going to be logged by method
	 * 
	 * @see RoundLogger : updateRoundLog(String)
	 */
	
	public void updateGameLog(String event) {
		gameLog = String.format("%s%n", gameLog.concat(event));
	}
	
	/**
	 * Used to fetch written game log
	 * @return :String, gamelog
	 * 
	 */
	
	public String getGameLog() {
		return gameLog;
	}
}
