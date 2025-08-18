package gameSessionMenager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import gameFileMenager.RoundLogger;
import guiGameSession.GameScreen;

/**
 * Backend of each game session, where main loop is executed, game status is calculated and updated via calling 
 * corresponding listeners.
 * 
 *  @author Aykut Bir
 *  @since 10/05/2024
 */
public class GameSession {
	
	private String sessionName;
	private GameScreen gameScreen;
	private int numberOfPlayers;
	
	private Deck deck;
	private ArrayList<Card> discardPile = new ArrayList<>();
	private ArrayList<Card> drawPile;
	
	private Bot[] bots;
	private HumanPlayer player;
	private Player[] allPlayers;
	
	
	private String direction = "Normal";
	
	private HashSet<Player> pointOut; 
	
	private RoundLogger logger = new RoundLogger();
	
	/**
	 * Constructor.
	 * 
	 * @param numberOfPlayers :int
	 * @param sessionName :String
	 * @param username :String
	 */
	public GameSession(int numberOfPlayers, String sessionName, String username) {
		
		this.numberOfPlayers = numberOfPlayers;
		this.sessionName = sessionName;
		this.player = new HumanPlayer(username);
		this.deck = new Deck();
		bots = new Bot[numberOfPlayers - 1];
		allPlayers = new Player[numberOfPlayers];
		allPlayers[0] = player;
		initializeGameSession(numberOfPlayers);
		this.pointOut = new HashSet<>();
	}
	
	/**
	 * Initializes game session and updates the GameScreen accordingly:
	 * 
	 * 		- Initialized different bots
	 *      - Using Deck object distrubutes card, sets the draw pile
	 * 		- Opens a card onto discard pile
	 * 		- Logs the game has begun
	 * 
	 * @param numberOfPlayers :int
	 */
	private void initializeGameSession(int numberOfPlayers) {
		for (int i = 0; i < numberOfPlayers - 1; i++) {
			if (i % 3 == 0) {
				bots[i] = new AggresiveBot(i);
				allPlayers[i + 1] = bots[i];
			} else if (i % 2 == 0) {
				bots[i] = new PassiveBot(i);
				allPlayers[i + 1] = bots[i];
			} else {
				bots[i] = new RandomBot(i);
				allPlayers[i + 1] = bots[i];
			}
		}
		for (Player player : allPlayers) {
			deck.distrubuteCards(player);
		}
		
		drawPile = deck.initializeDrawPile();
		discardPile.add(drawPile.remove(drawPile.size() - 1));
		
		logger.updateRoundLog("--Game Session Begins--");
	}
	
	/**
	 * Runs bot turn:
	 * 
	 * 		-If it is after HumanPlayer first it menages the game state then runs corresponding bots turn
	 * 		-If it is after bots turn It will get bots move 
	 * 
	 * (Note: see both methods to get detailed information)
	 * 
	 * @param playerIndex :int
	 * @param isAfterHumanPlayerMove :boolean
	 * 
	 */
	
	public void runBotTurn (int playerIndex, boolean isAfterHumanPlayerMove) {
		
		int nextPlayerIndex = determineNextPlayerIndex(playerIndex);
  		
  		if (isAfterHumanPlayerMove) {
  			if (!menageBotAfterHumanPlayer(playerIndex)) {
  				getBotMove(playerIndex, nextPlayerIndex);
  			}
  		} else {
  			getBotMove(playerIndex, nextPlayerIndex);
  		}
		
	}
	
	/**
	 * After GameScreen gets HumanPlayer's input for the users move for that raund, GameSession menages the game 
	 * status regarding the card played.
	 * 
	 * @param playerIndex :int, next bots index after HumanPayer, which is affected by card HumanPlayer chooses.
	 * @return isSkipped :boolean, returns if next bot after HumanPlayer is skipped or not.
	 * 
	 */
	private boolean menageBotAfterHumanPlayer(int playerIndex) {
		boolean isSkipped = false;
		String playedCardValue = getLastDiscardedCard().getValue();
		Player currentBot = allPlayers[playerIndex];
		
		if(playedCardValue.equals("Skip")) {
			skipTurn(playerIndex);
			isSkipped = true;
		}
			
		else if(playedCardValue.equals("Draw2")) {
			drawTwo(currentBot);
			skipTurn(playerIndex);
			isSkipped = true;
		}
			
		else if(playedCardValue.equals("WildDraw4")) {
			drawFour(currentBot);
			skipTurn(playerIndex);
			isSkipped = true;
		}
		return isSkipped;
	}
	
	/**
	 * Menages the game status after bot play, updates the game logs accordingly:
	 * 
	 * 		-If next player after that move is human player it initiates HumanPlayer's move
	 * 		-If not, loop continues until human player plays.
	 * 		-Additionaly checks if the bot won the game or not, if so calls the correct listener from GameScreen to conclude the game.
	 * 
	 * 
	 * @param discardedCard :Card
	 * @param currentBotIndex :int
	 * @param nextPlayerIndex :int
	 */
	private void menageStatusAfterBotPlay(Card discardedCard,int currentBotIndex, int nextPlayerIndex) {
		if (allPlayers[currentBotIndex].getHand().size() == 0) {
			gameScreen.listenGameEnded(false, allPlayers[currentBotIndex].getName());
		} else {
			String playedCardValue = discardedCard.getValue();
			logger.updateRoundLog(String.format("%s plays %s. %s has %d cards left.", allPlayers[currentBotIndex].getName(), discardedCard.getName(), 
																						allPlayers[currentBotIndex].getName(), allPlayers[currentBotIndex].getHand().size()));
			
			if (playedCardValue.equals("Skip")) {
				skipTurn(nextPlayerIndex);
			}
				
			else if (playedCardValue.equals("Draw2")) {
				drawTwo(allPlayers[nextPlayerIndex]);
				skipTurn(nextPlayerIndex);
			}
				
			else if (playedCardValue.equals("WildDraw4")) {
				selectColor(discardedCard);
				drawFour(allPlayers[nextPlayerIndex]);
				skipTurn(nextPlayerIndex);
			}
			else if (playedCardValue.equals("WildColor")){
				selectColor(discardedCard);
				if (nextPlayerIndex != 0) {
					runBotTurn(nextPlayerIndex, false);
				} else {
					gameScreen.initiatePlayerMove(getLastDiscardedCard());
				}
				
			}
			else if (playedCardValue.equals("Reverse")) {
				reverseDirection();
				nextPlayerIndex = determineNextPlayerIndex(currentBotIndex);
				if (nextPlayerIndex != 0) {
					runBotTurn(nextPlayerIndex, false);
				} else {
					gameScreen.initiatePlayerMove(getLastDiscardedCard());
				}
				
			}
			else {
				if (nextPlayerIndex != 0) {
					runBotTurn(nextPlayerIndex, false);
				} else {
					gameScreen.initiatePlayerMove(getLastDiscardedCard());
				}
			}
		}
		
	}
	
	/**
	 * Gets bot move, reminding that bots do not skip their own turn.
	 * 
	 * 		-If bot has no discardable cards it will draw until it has one
	 * 		-Method will ask bot askUNO method to get bots response
	 * 		-Method will discard the card and log it accordingly
	 * 		-Lastly method updates game screen and menages the state of the game after bot play.
	 * 
	 * @param currentBotIndex :int
	 * @param nextPlayerIndex :int
	 */
	private void getBotMove(int currentBotIndex, int nextPlayerIndex) {
		Player currentBot = allPlayers[currentBotIndex];
		
		while (!currentBot.hasDiscardableCard(getLastDiscardedCard())) {
			botDrawCard(currentBot);
		}
		
		if ((currentBot.getHand().size() == 2) && (!((Bot)currentBot).askUNO())){
			pointOut.add(currentBot);
		} else if ((currentBot.getHand().size() == 2) && (((Bot)currentBot).askUNO())){
			logger.updateRoundLog(String.format("%s declares UNO!", currentBot.getName()));
		}
		
		Card botMove = ((Bot) currentBot).playCard(getLastDiscardedCard());
		discardCard(botMove);
		gameScreen.listenCardCounts(currentBot);
		menageStatusAfterBotPlay(botMove, currentBotIndex, nextPlayerIndex);
	}
	
	/**
	 * Makes given player to draw 2 card and update the game status and logs
	 * 
	 * @param player :Player
	 */
	private void drawTwo(Player player) {
		logger.updateRoundLog(String.format("%s draws Two", player.getName()));
		if (drawPile.size() < 2) {
			discardToDrawPile();
			player.drawCard(drawPile);
			player.drawCard(drawPile);
		} else {
			player.drawCard(drawPile);
			player.drawCard(drawPile);
		}
		gameScreen.listenDrawDeck();
		gameScreen.listenCardCounts(player);
	}
	/**
	 * Makes given player to draw 4 card and update the game status and logs
	 * 
	 * @param player :Player
	 */
	private void drawFour(Player player) {
		logger.updateRoundLog(String.format("%s draws Four", player.getName()));
		if (drawPile.size() < 4) {
			discardToDrawPile();
			player.drawCard(drawPile);
			player.drawCard(drawPile);
			player.drawCard(drawPile);
			player.drawCard(drawPile);
		} else {
			player.drawCard(drawPile);
			player.drawCard(drawPile);
			player.drawCard(drawPile);
			player.drawCard(drawPile);
		}
		gameScreen.listenDrawDeck();
		gameScreen.listenCardCounts(player);
	}
	
	/**
	 * Makes given player to skip their turn and update the game status and logs
	 * 
	 * @param playerIndex :int, determines next player and runs its turn accordingly.
	 */
	private void skipTurn(int playerIndex) {
		logger.updateRoundLog(String.format("%s's turn is skipped", allPlayers[playerIndex].getName()));
		int nextPlayerIndex = determineNextPlayerIndex(playerIndex);
		
		if (nextPlayerIndex != 0) {
			runBotTurn(nextPlayerIndex, false);
		} else {
			gameScreen.initiatePlayerMove(getLastDiscardedCard());
		}
		
	}
	
	/**
	 * Reverses game direction and updates it.
	 */
	private void reverseDirection() {
		gameScreen.listenDirection();
	}
	
	/**
	 * Determines the next players index by given player index and direction.
	 * 
	 * @param playerIndex :int
	 * @return nextPlayerIndex :int
	 */
	public int determineNextPlayerIndex(int playerIndex) {
		
	    int nextPlayerIndex;
	    if (direction.equals("Reverse")) {
	        nextPlayerIndex = (playerIndex == 0 ? allPlayers.length - 1 : playerIndex - 1);
	    } else {
	        nextPlayerIndex = (playerIndex == allPlayers.length - 1 ? 0 : playerIndex + 1);
	    }
	    return nextPlayerIndex;
	}
	
	/**
	 * When draw pile empties out, it transforms discard pile to the draw pile and opens a new card on top of discard pile.
	 * Updates the GameScreen accordingly afterwards.
	 * 
	 * If opened card is a wild card, game allows any color to be discarded on top of that card.
	 */
	public void discardToDrawPile() {
		for (Card card : discardPile) {
			drawPile.add(card);
		}
		discardPile.clear();
		Collections.shuffle(drawPile);
		discardPile.add(drawPile.remove(drawPile.size() - 1));
		
		if (getLastDiscardedCard().getValue().contains("Wild")) {
			((WildCard) getLastDiscardedCard()).setSelectedColor(null);
		}
		
		gameScreen.listenDiscardPile(getLastDiscardedCard());
		gameScreen.listenDrawDeck();
		
	}
	/**
	 * Takes given card and puts it on top of discard pile and updates GameScreen
	 * @param card: Card
	 */
	public void discardCard(Card card) {
		this.getDiscardPile().add(card);
		gameScreen.listenDiscardPile(getLastDiscardedCard());
	}
	
	/**
	 * 
	 * @return Card :returns last played card on top of discard pile. 
	 */
	public Card getLastDiscardedCard() {
		return this.getDiscardPile().get(discardPile.size() - 1);
	}
	
	/**
	 * Makes bot to draw card and logs it to game logs.
	 * Updates GameScreen at each draw.
	 * If draw pile reaches 0 card it calls discardToDrawPile method to refill draw pile.
	 * @param player
	 */
	public void botDrawCard (Player player) {
		logger.updateRoundLog(String.format("%s draws.", player.getName()));
		if (drawPile.size() == 0) {
			discardToDrawPile();
			player.drawCard(drawPile);
			gameScreen.listenCardCounts(player);
			gameScreen.listenDrawDeck();
			gameScreen.listenDiscardPile(getLastDiscardedCard());
		} else {
			player.drawCard(drawPile);
			gameScreen.listenDrawDeck();
			gameScreen.listenCardCounts(player);
		}
	}
	
	/** 
	 * Randomly selects color for bots, if bots play any WildCard.
	 * Logs the choice on the game logger.
	 * 
	 * @param discardedCard :Card
	 */
	private void selectColor (Card discardedCard) {
		
		Random random = new Random();
        int randomNumber = random.nextInt(4);
        
        switch (randomNumber) {
        case 0: ((WildCard) discardedCard).setSelectedColor("Red"); break;
        case 1: ((WildCard) discardedCard).setSelectedColor("Green"); break;
        case 2: ((WildCard) discardedCard).setSelectedColor("Blue"); break;
        default: ((WildCard) discardedCard).setSelectedColor("Yellow"); break;
        }
        
        logger.updateRoundLog(String.format("Selects color %s", ((WildCard) discardedCard).getSelectedColor()));
	}
	
	/** 
	 * Calculates points for HumanPlayer from every bots, according to card points.
	 * @return totalPoints :int
	 */
	public int calculatePoints() {
		int totalPoints = 0;
		for (Bot bot : bots) {
			for (Card card : bot.getHand()) {
				totalPoints += card.getPoint();
			}
		}
		return totalPoints;
	}
	
	/**
	 * Empties point out ArrayList.
	 */
	public void emptyPointOut() {
		this.pointOut.clear();
	}
	
	// Needed getters and setters for respective fields.
	
	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}

	public void setDiscardPile(ArrayList<Card> discardPile) {
		this.discardPile = discardPile;
	}

	public ArrayList<Card> getDrawPile() {
		return drawPile;
	}

	public void setDrawPile(ArrayList<Card> drawPile) {
		this.drawPile = drawPile;
	}

	public String getSessionName() {
		return sessionName;
	}

	public Bot[] getBots() {
		return bots;
	}

	public HumanPlayer getPlayer() {
		return player;
	}

	public Player[] getAllPlayers() {
		return allPlayers;
	}

	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public HashSet<Player> getPointOut() {
		return pointOut;
	}
	
	
	public RoundLogger getGameLogger() {
		return this.logger;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	
}
