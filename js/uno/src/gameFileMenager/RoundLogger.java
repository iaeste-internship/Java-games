package gameFileMenager;

/**
 * Class that logs each round actions for user display via RoundLogScreen.
 * Also utilizes GameHistoryLogger object to write game log file.
 * 
 * @author Aykut Bir
 * @since 13/05/2024
 * 
 */
public class RoundLogger {
	
	private String roundActions = "";
	private GameHistoryLogger gameLogger= new GameHistoryLogger();
	
	/**
	 * Constructor.
	 */
	public RoundLogger() {
		
	}
	
	
	/**
	 * Writes each log entry to round logger and game logger objects.
	 * Invoked by methods that constant log updates
	 * 
	 * @param event :String, game action that is going to be logged
	 * 
	 * @see GameSession, GameScreen :to see logged events by main loop of the game
	 * 
	 */
	
	public void updateRoundLog(String event) {
		roundActions = String.format("%s%n", roundActions.concat(event));
		gameLogger.updateGameLog(event);
	}
	
	/**
	 * Resets round logger to prepare new stream of entries.
	 * 
	 * @see GameScreen viewRoundLog() :method to see exact times where round log is flushed.
	 * 
	 */
	public void flush() {
		roundActions = "";
	}
	
	/**
	 * Returns written round logs
	 * @return roundActions :String, log of actions taken by player and bots 
	 * 
	 * @see GameScreen initiatePlayerMove(Card)
	 */
	public String getRoundActions() {
		return roundActions;
	}
	
	/**
	 * Getter for GameHistoryLogger object
	 * @return GameHistoryLogger
	 */
	
	public GameHistoryLogger getGameHistoryLogger() {
		return this.gameLogger;
	}
}
